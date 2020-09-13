(ns dex.core
  (:require [clojure.java.shell :refer [sh]])
  (:gen-class))

(defn -main
  "Dex Main Function"
  [& args]
  (println "Hello, World")
  (shutdown-agents))
