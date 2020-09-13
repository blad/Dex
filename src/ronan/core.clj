(ns ronan.core
  (:require [clojure.java.shell :refer [sh]])
  (:gen-class))

(defn -main
  "Ronan Main Function"
  [& args]
  (println "Hello, World")
  (shutdown-agents))
