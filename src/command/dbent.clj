(ns command.dbent
  (:use [korma.db]))

(use 'korma.core)

(def db-conf {:db "cldb"
    :user ""
    :password ""
    :host ""
    :port ""})

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