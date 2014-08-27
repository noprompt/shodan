(ns shodan.trace
  (:refer-clojure :exclude [defn fn let])
  (:require
   [clojure.core :as core]
   [cljs.env :as env]
   [cljs.analyzer :refer [*cljs-ns*]]))

(core/defn condition-map? [x]
  (and (map? x)
       (or (vector? (:pre x))
           (vector? (:post x)))))

(core/defn condition-map-and-body [fn-body]
  (core/let [[x & body] fn-body]
    (if (and (seq body) (condition-map? x))
      [x body]
      [nil fn-body])))

(core/defn do-trace-specs [sym specs {:keys [tracer anonymous?]}]
  (for [[arglist & body] specs
        :let [[condition-map body] (condition-map-and-body body)
              args (remove '#{&} arglist)
              trace-data `{:description '~sym
                           :timestamp (js/Date.)
                           :args [~@args]
                           :arglist '~arglist
                           :anonymous? ~anonymous?}
              fn-form `(core/fn ~arglist ~condition-map ~@body)
              return-sym (gensym "return_")]]
    `(~arglist
       (shodan.trace/trace-enter ~tracer ~trace-data)
       (core/let [return# (try
                            (~fn-form ~@args)
                            (catch js/Object e#
                              (shodan.trace/trace-error ~tracer
                                (assoc ~trace-data :error e#))))]
         (shodan.trace/trace-exit ~tracer (assoc ~trace-data :exit return#))
         return#))))

(defmacro fn
  "Like clojure.core/fn but adds tracing at compile time when log-level
  is set to :trace."
  [& body]
  (core/let [[_ & sym+specs] (macroexpand-1 `(core/fn ~@body))
             [sym fn-specs] (if (symbol? (first sym+specs))
                              [(first sym+specs) (rest sym+specs)]
                              [(gensym "fn_") sym+specs])
             new-specs (do-trace-specs
                        (symbol (str *ns*) (str sym))
                        fn-specs
                        {:anonymous? true
                         :tracer 'shodan.trace/default-tracer})]
    `(core/fn ~sym ~@new-specs)))

(defmacro defn
  "Like clojure.core/defn but adds tracing at compile time when 
  log-level is set to :trace."
  [sym & body]
  (core/let [[_ sym [_ & fn-specs]] (macroexpand-1 `(core/defn ~sym ~@body))
             tracer (or (:tracer (meta sym))
                        (:tracer (meta (.-name *ns*)))) 
             tracer (cond
                     (symbol? tracer)
                     tracer
                     (= (first tracer) 'quote)
                     (second tracer)
                     :else
                     tracer)
             specs (if tracer
                     (do-trace-specs
                      (symbol (str *ns*) (str sym))
                      fn-specs
                      {:anonymous? false
                       :tracer tracer})
                     fn-specs)]
    `(def ~sym (core/fn ~@specs))))
