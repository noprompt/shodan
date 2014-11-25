(ns user
  (:require
   [figwheel.client :as fw :include-macros true]
   [weasel.repl :as ws-repl]
   [shodan.console :as console :include-macros true]))

(enable-console-print!)

(ws-repl/connect "ws://localhost:9010" :verbose true)

(fw/watch-and-reload
  :websocket-url "ws:localhost:3448/figwheel-ws"
  :jsload-callback
  (fn []
    (js/console.log (str (gensym "RELOAD_"))))) 

(ns foo
  (:require [shodan.console :as console :include-macros true]))

(defn foo [x]
  (console/with-group "foo"
    (console/log x)))

(defn style [s1 s2]
  (fn [log-v x]
    (conj log-v "%c%s%c" s1 (pr-str x) s2)))

(def purple
  (style "color:purple" "color:inherit"))

(def green
  (style ))

(defn do-keyword [log-v k]
  (purple log-v k))

(defn do-string [log-v s]
  (green log-v s))

(def fg-green "color:green")
(def fg-purple "color:purple")
(def fg-inherit "color:inherit")

(def empty-formatter
  {:format [] :values []})

(defn add-style [formatter style]
  (if (= (peek (:format formatter))
         "%c")
    (let [old-values (:values formatter)
          last-value (peek old-values)
          new-values (conj (pop old-values) (str last-value ";" style))]
      (assoc formatter :values new-values))
    (-> formatter
        (update-in [:format] conj "%c")
        (update-in [:values] conj style))))

(defn add-value [formatter value]
  (-> formatter
      (update-in [:format] conj "%s")
      (update-in [:values] conj value)))

(-> empty-formatter
    (add-style "font-weight:bold")
    (add-style "color:purple")
    (add-value (pr-str :foo))
    (add-style "font-weight:inherit;")
    (add-value " ")
    (add-value (pr-str 1))
    (run-formatter))

{#{:form/field-a} {:required? true}

 #{:form/field-a
   :form/field-b} {:max-length 10}}





{:rules [(> (+ (count (?- [:form/field-a]))
               (count (?- [:form/field-b])))
            42)
         (-! [:form.validation/field-a :valid?] false)
         (-! [:form.validation/field-b :valid?] false)]}

{:tag :span
 :attrs {:when (not (?- [:form.validation/field-a :valid?]))} 
 :content [(?- [:form.validation/field-a :message])]}

(console/log* ["%c\"foo\"%c" "font-weight:bold; color:green;" "font-weight:inherit;"])

(defn run-formatter [{:keys [format values]}]
  (console/log* (into [(clojure.string/join format)] values)))



(let [xs [{:style [fg-purple fg-inherit]
           :value :bar}
          {:style [fg-green fg-inherit]
           :value "foo"}]
      {:keys [format values]}
      (reduce (fn [m {:keys [style value]}]
                (let [[s1 s2] style]
                  (-> m
                      (update-in [:format] conj "%c%s%c")
                      (update-in [:values] conj s1 (pr-str value) s2))))
              {:format []
               :values []}
              xs)]
  (console/log* (into [(clojure.string/join " " format)] values)))

(console/log*
 (-> []
     (do-keyword :foo)
     (do-string "bar")))

(console/log )
