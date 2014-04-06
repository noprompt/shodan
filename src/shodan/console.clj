(ns shodan.console
  (:refer-clojure :exclude [time]))

(defmacro with-group [title & body]
  `(do
     (.group js/console ~title)
     ~@body
     (.groupEnd js/console)))
 
(defmacro with-group-collapsed [title & body]
  `(do
     (.groupCollapsed js/console ~title)
     ~@body
     (.groupEnd js/console)))
 
(defmacro with-profile [title & body]
  `(do
     (.profile js/console ~title)
     ~@body
     (.profileEnd js/console)))
 
(defmacro time [& body]
  `(do
     (.time js/console "Elapsed time")
     ~@body
     (.timeEnd js/console "Elapsed time")))
