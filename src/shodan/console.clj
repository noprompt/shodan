(ns shodan.console
  "Wrappers for the JavaScript console API."
  (:refer-clojure :exclude [assert time])
  (:require
   [cljs.env :as env]))

;; Many of the macros in this file were originally functions in
;; console.cljs. Wrapping the console API with functions is turns out
;; to be problematic for debugging since the line numbers reported by
;; the log will be linked back to Shodan and not to the original
;; source of the call.

(defn node-js?
  "Returns true if the ClojureScript compiler :target option is
  :node-js."
  []
  (= :nodejs (:target @env/*compiler*)))

(defmacro js-apply [f target args]
  `(.apply ~f ~target (to-array ~args)))

;; ---------------------------------------------------------------------
;; Logging

(defmacro log
  "Display messages to the console."
  [& args]
  `(.log js/console ~@args))

(defmacro log*
  "Like `log` but takes a collection and uses apply."
  [coll]
  `(js-apply (.-log js/console) js/console ~coll))

(defmacro debug
  "Like `log` but marks the output as debugging information."
  [& args]
  `(.debug js/console ~@args))

(defmacro debug*
  "Like `debug` but takes a collection and uses apply."
  [coll]
  `(js-apply (.-debug js/console) js/console ~coll))

(defmacro info
  "Like `log` but marks the output as an informative message."
  [& args]
  `(.info js/console ~@args))

(defmacro info*
  "Like `info` but takes a collection and uses apply."
  [coll]
  `(js-apply (.-info js/console) js/console ~coll))

(defmacro warn
  "Like `log` but marks the output as a warning."
  [& args]
  `(.warn js/console ~@args))

(defmacro warn*
  "Like `warn` but takes a collection and uses apply."
  [coll]
  `(js-apply (.-warn js/console) js/console ~coll))

(defmacro error
  "Like `log` but marks the output as an error."
  [& args]
  `(.error js/console ~@args))

(defmacro error*
  "Like `error` but takes a collection and uses apply."
  [coll]
  `(js-apply (.-error js/console) js/console ~coll))


;; ---------------------------------------------------------------------
;; Message grouping (browser only)

(defmacro group-start
  "Begin a console message group."
  [& args]
  (when-not (node-js?)
    `(.group js/console ~@args)))

(defmacro group-start-collapsed
  "Begin a console message group in a collapsed state."
  [& args]
  (when-not (node-js?)
    `(.groupCollapsed js/console ~@args)))

(defmacro group-end
  "End a console message group."
  []
  (when-not (node-js?)
    `(.groupEnd js/console)))

(defmacro with-group
  "Evaluate body within a new console group with title. Returns the
  value of body."
  [title & body]
  (when-not (node-js?)
    `(do
       (.group js/console ~title)
       (let [result# (do ~@body)]
         (.groupEnd js/console)
         result#))))

(defmacro with-group-collapsed
  "Evaluate body within a new console group (collapsed) with
  title. Returns the value of body."
  [title & body]
  (when-not (node-js?)
    `(do
       (.groupCollapsed js/console ~title)
       (let [result# (do ~@body)]
         (.groupEnd js/console)
         result#))))


;; ---------------------------------------------------------------------
;; Assertion

(defmacro assert [& args]
  `(.assert js/console ~@args))

;; ---------------------------------------------------------------------
;; Inspection

(defmacro dir [obj]
  `(.dir js/console ~obj))

(defmacro dirxml [node]
  (when-not (node-js?)
    `(.dirxml js/console ~node)))

(defmacro trace []
  `(.trace js/console))

;; ---------------------------------------------------------------------
;; Profiling (browser only)

(defmacro profile-start
  "Begin a profile named title. Browser only."
  [title]
  (when-not (node-js?)
    `(.profile js/console ~title)))

(defmacro profile-end
  "End a profile. Browser only."
  []
  (when-not (node-js?)
    `(.profileEnd js/console)))

(defmacro with-profile
  "Evaluate body within a new console profile with title. Returns the
  value of body. Browser only."
  [title & body]
  (when-not (node-js?)
    `(do
       (.profile js/console ~title)
       (let [result# (do ~@body)]
         (.profileEnd js/console)
         result#))))


;; ---------------------------------------------------------------------
;; Timing

(defmacro time-start [id]
  `(.time js/console ~id))

(defmacro time-end [id]
  `(.timeEnd js/console ~id))

(defmacro time
  "Evaluate body and time its execution. Returns the value of body."
  [& body]
  `(do
     (.time js/console "Elapsed time")
     (let [result# (do ~@body)]
       (.timeEnd js/console "Elapsed time")
       result#)))

(defmacro with-time
  "Evaluate body and time its execution with title. Returns the value
  of body."
  [title & body]
  `(do
     (.time js/console ~title)
     (let [result# (do ~@body)]
       (.timeEnd js/console ~title)
       result#)))

;; ---------------------------------------------------------------------
;; Extra

(defmacro spy
  "Log and return a value."
  [x]
  `(do
     (.log js/console ~x)
     ~x))
