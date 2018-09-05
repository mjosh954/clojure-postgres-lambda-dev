(ns command.core
  (:gen-class
    :methods [^:static [handler [String] String]])
    (:use ring.middleware.params
      ring.util.response
      ring.adapter.jetty))
(require '[command.dbent :as db])


(defn find-command
  [command]
  (str "Searching for " command " command"))

; (defn -handler 
;   [s]
;   (str "Hello " s "!"))

(defn build-response 
  [status-code body]
  {:status status-code
    :headers {"Content-Type" "text/plain"}
    :body body})

(defn handler [{{command "command"} :params}]
  (if (nil? command)
    (build-response 404 "Provide a command query param. (ex ?command=doc)")
    (build-response 200 (db/query-command-by-name command))))

(def app
  (-> handler wrap-params))

(run-jetty app {:port 8080})