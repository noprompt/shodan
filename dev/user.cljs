(ns user
  (:require
   [weasel.repl :as ws-repl]))

(enable-console-print!)
(ws-repl/connect "ws://localhost:9010" :verbose true)
