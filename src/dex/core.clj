(ns dex.core
  (:require [clojure.java.shell :refer [sh]])
  (:require [clojure.string :as string])
  (:gen-class))

(def db-name ".dex-tracker.db")
(def re-new-line #"\n")
(def re-separator #"\|")
(def new-line "\n")
(def separator "|")

(defn git-sh 
  "Execute command, returning sh reponse"
  [& command-list]
  (apply sh (cons "git" command-list)))


(defn git 
  "Excute the proviced git command"
  [command] 
  (string/trim (:out (apply git-sh (string/split command #" ")))))


(defn get-branch-name
  "Get the name of the current branch"
  [] 
  (git "rev-parse --abbrev-ref HEAD"))


(defn get-root-path
  "Get the path to the root of the repository."
  [] 
  (git "rev-parse --show-toplevel"))


(defn get-db-contents 
  "Get contents of the Dex DB"
  [] 
  (let [db-string  (slurp db-name)
        db-lines   (string/split db-string re-new-line)
        db-entries (map #(string/split % re-separator) db-lines)]
    (into {} db-entries)))


(defn get-associated-value 
  "Lookup the associated value with the given branch name"
  [branch-name]
  (let [branches (get-db-contents)]
    (get branches branch-name)))


(defn format-value 
  "Format the value of the string for inclusion in a commit message"
  [value]
  (str "[" value "]"))


(defn has-formatted-value? [value message] 
  (let [tag-index (string/index-of 
                    message 
                    (format-value value))]
    (= 0 tag-index)))


(defn read-commit-message [path]
  (slurp path))


(defn write-commit-message [path message]
  (spit path message))


(defn tag-commit-message [commit-path]
  (let [branch-name    (get-branch-name)
        value          (get-associated-value branch-name)
        commit-message (read-commit-message commit-path)]
    (when-not (or (nil? value) (has-formatted-value? value commit-message))
      (write-commit-message 
        commit-path 
        (str (format-value value) " " commit-message)))))


(defn save-value [associated-value]
  (let [branch-name (get-branch-name)]
    (spit 
      db-name 
      (str branch-name separator associated-value new-line) 
      :append true)))

(defn init []
  (let [target-root    (get-root-path)
        target-db-path (str target-root "/" db-name)
        target-file    (clojure.java.io/file target-db-path)]
    (when-not (.exists target-file)
      (spit db-name ";; Dex DB File\n"))))


(defn install-git-alias []
  (println "Installing git alias: track")
  (git-sh "config" "--global" "alias.track"  "'!dex-tracker track'"))


(defn -main
  "Dex Main Function"
  [& args]
  (init)
  (case (first args)
    "install" (install-git-alias)
    "tag"     (tag-commit-message (second args))
    "track"   (save-value (second args))
    (println "Unknown command: install, tag, track are only valid commands"))
  (shutdown-agents))
