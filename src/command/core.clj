(ns command.core
  (:gen-class))
(require '[command.dbent :as db])

(defn build-response 
  [status-code body]
  {:status status-code
    :headers {"Content-Type" "application/json"}
    :body body})

(defn build-error-payload [error-message]
  { :status "Error",
    :error_message error-message })

(defn build-success-payload [results]
  { :status "Success"
    :results results })

(defn handler [{{command "command"} :params}]
  (if (nil? command)
    (build-response 404 (build-error-payload "Provide a command query param. (ex ?command=doc)"))
    (build-response 200 (build-success-payload (db/query-command-by-name command)))))
