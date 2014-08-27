(ns shodan.trace
  (:require [shodan.console :as console]))

(defprotocol ITraceEnter
  (-trace-enter [this trace-data]))

(defprotocol ITraceError
  (-trace-error [this trace-data]))

(defprotocol ITraceExit
  (-trace-exit [this trace-data]))

(def default-tracer
  (reify
    ITraceEnter
    (-trace-enter [_ {:keys [description args arglist anonymous?]}]
      (.groupCollapsed js/console
        (str description " " arglist (when anonymous? " (anonymous)")))
      (let [arglist (remove '#{&} arglist)]
        (doseq [[sym val] (map vector arglist args)]
          (.log js/console (str sym) "=" val))))

    ITraceExit
    (-trace-exit [_ {:keys [exit]}]
      (.log js/console exit)
      (.groupEnd js/console))

    ITraceError
    (-trace-error [_ {:keys [error]}]
      (.error js/console (.-stack error))
      (.groupEnd js/console)
      (throw error))))

(defn trace-enter
  [tracer trace-data]
  (-trace-enter tracer trace-data))

(defn trace-error
  [tracer trace-data]
  (-trace-error tracer trace-data))

(defn trace-exit
  [tracer trace-data]
  (-trace-exit tracer trace-data))
