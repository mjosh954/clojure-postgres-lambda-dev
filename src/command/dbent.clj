(ns command.dbent
  (:use [korma.db]))

(use 'korma.core)

(def db-conf {:db "cldb"
    :user "jmixer920"
    :password "RT5JYz9cHMJ2PQaS"
    :host "clojuredb.cywjxqkfebb1.us-west-2.rds.amazonaws.com"
    :port "5432"})

(defdb dev (postgres db-conf))

(defentity clojurecommands
  (pk :command_id)
  (table :clojurecommand)
  (database dev)
  (entity-fields :name :command :description :example))

(defn query-command-by-name
  [command-name]
  (select clojurecommands
    (where {:command [like command-name]})))