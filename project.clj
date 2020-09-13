(defproject ronan "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/blad/ronan"
  :license {:name "Apache 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :main ^:skip-aot shel.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
