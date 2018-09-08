(ns command.dbent
  (:use [korma.db])
  (:require [environ.core :refer [env]]))

(use 'korma.core)

(println (env :port))

(def db-conf {:db (env :dbname)
    :user (env :dbuser)
    :password (env :dbpassword)
    :host (env :database-url)
    :port (env :port)})

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