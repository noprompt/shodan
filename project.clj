(defproject shodan "0.3.1-SNAPSHOT"
  :description "ClojureScript wrapper for the JavaScript console API."
  :url "https://github.com/noprompt/shodan"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [org.clojure/clojurescript "0.0-2202" :scope "provided"]]

  :profiles
  {:dev
   {:dependencies
    [[com.cemerick/piggieback "0.1.3"]
     [figwheel "0.1.3-SNAPSHOT"]
     [weasel "0.2.0"]]

    :plugins
    [[lein-figwheel "0.1.3-SNAPSHOT"]
     [lein-cljsbuild "1.0.3"]]

    :source-paths
    ["dev"]

    :repl-options
    {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

  :cljsbuild
  {:builds [{:id "shodan"
             :source-paths ["src" "dev"]
             :compiler {:output-to "resources/public/shodan.js"
                        :output-dir "resources/public/out"
                        :optimizations :none
                        :source-map true}}]})
