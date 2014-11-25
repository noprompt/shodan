(defproject shodan "0.3.1-SNAPSHOT"
  :description "ClojureScript wrapper for the JavaScript console API."
  :url "https://github.com/noprompt/shodan"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies
  [[org.clojure/clojure "1.6.0" :scope "provided"]
   [org.clojure/clojurescript "0.0-2371" :scope "provided"]
   [com.andrewmcveigh/cljs-time "0.1.6"]]

  :profiles
  {:dev
   {:dependencies
    [[com.cemerick/piggieback "0.1.3"]
     [figwheel "0.1.4-SNAPSHOT"]
     [weasel "0.4.2"]]

    :plugins
    [[lein-figwheel "0.1.4-SNAPSHOT"]
     [lein-cljsbuild "1.0.3"]]

    :source-paths
    ["src" "dev"]

    :repl-options
    {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

  :cljsbuild
  {:builds [{:id "shodan"
             :source-paths ["src" "dev"]
             :compiler {:output-to "resources/public/shodan.js"
                        :output-dir "resources/public/out"
                        :optimizations :none
                        :source-map true}}]}
  :figwheel
  {:port 9000
   :server-port 3448})
