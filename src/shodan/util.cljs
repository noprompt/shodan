(ns shodan.util)

(def node-js?
  (and (not= (js* "typeof module") "undefined")
       (.-exports js/module)))

(defn js-apply [f target args]
  (.apply f target (to-array args)))
