(ns commnand.entry
    (:gen-class
        :methods [^:static [handler [String] String]])
        (:use ring.middleware.params
          ring.util.response
          ring.adapter.jetty))

(require '[command.core :as core])
(use '[ring.middleware.json :only [wrap-json-response]])

(def app
(-> core/handler 
    wrap-params
    wrap-json-response))


(defonce server (run-jetty app {:port 8080}))