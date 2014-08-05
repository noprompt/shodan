(ns shodan.console
  (:refer-clojure :exclude [assert])
  (:require [shodan.util :refer [js-apply]]))

;; ---------------------------------------------------------------------
;; Polyfill

(let [methods ["assert"
               "clear"
               "count"
               "debug"
               "dir"
               "dirxml"
               "error"
               "exception"
               "group"
               "groupCollapsed"
               "groupEnd"
               "info"
               "log"
               "markTimeline"
               "profile"
               "profileEnd"
               "table"
               "time"
               "timeEnd"
               "timeStamp"
               "trace"
               "warn"]
      console (if (exists? js/console)
                js/console
                ;; IE <= 8
                (let [polyfill #js {:memory nil}]
                  (aset js/window "console" polyfill)
                  polyfill))
      noop (constantly nil)]
  (doseq [m methods]
    (or (aget console m)
        (aset console m noop))))

;; ---------------------------------------------------------------------
;; Logging

(defn log
  "Display messages to the console."
  [& args]
  (js-apply (.-log js/console) js/console args))

(defn debug
  "Like `log` but marks the output as debugging information."
  [& args]
  (js-apply (.-debug js/console) js/console args))

(defn info
  "Like `log` but marks the output as an informative message."
  [& args]
  (js-apply (.-info js/console) js/console args))

(defn warn
  "Like `log` but marks the output as a warning."
  [& args]
  (js-apply (.-warn js/console) js/console args))

(defn error
  "Like `log` but marks the output as an error."
  [& args]
  (js-apply (.-error js/console) js/console args))

;; ---------------------------------------------------------------------
;; Message grouping (browser only)

(defn group-start
  "Begin a console message group."
  [& args]
  (js-apply (.-group js/console) js/console args))

(defn group-start-collapsed
  "Begin a console message group in a collapsed state."
  [& args]
  (js-apply (.-groupCollapsed js/console) js/console args))

(defn group-end
  "End a console message group."
  []
  (.groupEnd js/console))

;; ---------------------------------------------------------------------
;; Assertion

(defn assert [& args]
  (js-apply (.-assert js/console) js/console args))

;; ---------------------------------------------------------------------
;; Inspection

(defn dir [obj]
  (.dir js/console obj))

(defn dirxml [node]
  (.dirxml js/console node))

(defn trace []
  (.trace js/console))

;; ---------------------------------------------------------------------
;; Profiling (browser only)

(defn profile-start
  "Begin a profile named title."
  [title]
  (.profile js/console title))

(defn profile-end
  "End a profile."
  []
  (.profileEnd js/console))

;; ---------------------------------------------------------------------
;; Timing

(defn time-start [id]
  (.time js/console id))

(defn time-end [id]
  (.timeEnd js/console id))
