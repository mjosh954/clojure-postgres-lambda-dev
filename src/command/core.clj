(ns command.core
  (:gen-class
    :methods [^:static [handler [String] String]])
    (:use ring.middleware.params
      ring.util.response
      ring.adapter.jetty))
(require '[command.dbent :as db])
(use '[ring.middleware.json :only [wrap-json-response]])

(defn find-command [command]
  (str "Searching for " command " command"))

(defn build-response 
  [status-code body]
  {:status status-code
    :headers {"Content-Type" "text/plain"}
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

(def app
  (-> handler 
    wrap-params
    wrap-json-response))

(run-jetty app {:port 8080})