(ns shodan.console
  (:refer-clojure :exclude [assert])
  (:require [shodan.util :refer [node-js? js-apply]]))

;;----------------------------------------------------------------------
;; Logging

(defn log
  "Display messages to the console."
  [& args]
  (js-apply (.-log js/console) js/console args))

(when-not node-js?
  (defn debug
    "Like `log` but marks the output as debugging information."
    [& args]
    (js-apply (.-debug js/console) js/console args)))

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

;;----------------------------------------------------------------------
;; Message grouping (browser only)

(when-not node-js?
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
    (.groupEnd js/console)))

;;----------------------------------------------------------------------
;; Assertion

(defn assert [& args]
  (js-apply (.-assert js/console) js/console args))

;;----------------------------------------------------------------------
;; Inspection

(defn dir [obj]
  (.dir js/console obj))

(when-not node-js?
  (defn dirxml [node]
    (.dirxml js/console node)))

(defn trace []
  (.trace js/console))

;;----------------------------------------------------------------------
;; Profiling (browser only)

(when-not node-js?
  (defn profile-start
    "Begin a profile named title."
    [title]
    (.profile js/console title))

  (defn profile-end
    "End a profile."
    []
    (.profileEnd js/console)))

;;----------------------------------------------------------------------
;; Timing

(defn time-start [id]
  (.time js/console id))

(defn time-end [id]
  (.timeEnd js/console id))
