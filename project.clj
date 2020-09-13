(defproject ronan "0.0.1-SNAPSHOT"
  :description "Git Ticket Tracker for Commits"
  :url "http://github.com/blad/Dex"
  :license {:name "Apache 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :main ^:skip-aot dex.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
