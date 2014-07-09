(ns shodan.inspection
  (:require
    [shodan.console :as console :include-macros true]))

(defn- find-type [obj]
  (cond
    (map? obj) :map
    (vector? obj) :vector
    (seq? obj) :seq
    :else :default))

(defmulti inspect
  "Inspects a ClojureScript data structure by displaying it in the
  browser's console. Collections are shown using log groups to
  represent their structure."
  (fn [x & args] (find-type x)))

(defmethod inspect :map [a-map prefix]
  (console/with-group "{"
    (doseq [[k v] a-map]
      (inspect v (pr-str k)))
    (console/log "}")))

(defmethod inspect :vector [a-vec prefix]
  (console/with-group "["
    (doseq [obj a-vec]
      (inspect obj))
    (console/log "]")))

(defmethod inspect :seq [a-vec prefix]
  (console/with-group "("
    (doseq [obj a-vec]
      (inspect obj))
    (console/log ")")))

(defmethod inspect :default
  ([obj]
   (console/log (pr-str obj)))
  ([obj prefix]
   (console/log (or prefix "") (pr-str obj))))
