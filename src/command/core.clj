(ns command.core
  (:gen-class
    :methods [^:static [handler [String] String]]
    :refer [command.dbent]))


(defn find-command
  [command]
  (str "Searching for " command " command"))

(defn -handler 
  [s]
  (str "Hello " s "!"))