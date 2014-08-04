(ns shodan.console
  (:refer-clojure :exclude [time]))

(defmacro with-group
  "Evaluate body within a new console group with title. Returns the
  value of body."
  [title & body]
  `(do
     (.group js/console ~title)
     (let [result# (do ~@body)]
       (.groupEnd js/console)
       result#)))
 
(defmacro with-group-collapsed
  "Evaluate body within a new console group (collapsed) with
  title. Returns the value of body."
  [title & body]
  `(do
     (.groupCollapsed js/console ~title)
     (let [result# ~@body]
       (.groupEnd js/console)
       result#)))
 
(defmacro with-profile
  "Evaluate body within a new console profile with title. Returns the
  value of body."
  [title & body]
  `(do
     (.profile js/console ~title)
     (let [result# (do ~@body)]
       (.profileEnd js/console)
       result#)))
 
(defmacro time
  "Evaluate body and time its execution. Returns the value of body."
  [& body]
  `(do
     (.time js/console "Elapsed time")
     (let [result# (do ~@body)]
       (.timeEnd js/console "Elapsed time") 
       result#)))

(defmacro with-time
  "Evaluate body and time its execution with title. Returns the value of body."
  [title & body]
  `(do
     (.time js/console ~title)
     (let [result# (do ~@body)]
       (.timeEnd js/console ~title)
       result#)))
