(ns shodan.console
  (:require-macros [shodan.console]))

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
