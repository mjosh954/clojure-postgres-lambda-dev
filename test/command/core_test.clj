(ns command.core-test
  (:require [clojure.test :refer :all]
            [command.core :refer :all]
            [clojure.pprint :as pp]))

(deftest handler-404-test
  (testing "Handler returns 404 on missing param request"
  (let [exp 404
        res (:status (handler ""))]
        (is (= exp res)))))
  ; (is (= 404 (:status (handler ""))))))

(deftest handler-error-message
  (testing "Handler returns correct error message on missing param"
    (let [exp "Provide a command query param. (ex ?command=doc)"
        {{res :error_message } :body} (handler "")]
        (is (= exp res)))))

(deftest build-success-payload-response
  (testing "Builds payload for success responses"
    (let [exp {:status "Success", :results []},
          res (build-success-payload [])]
          (is (= exp res)))))

(deftest build-success-payload-response-with-results
  (testing "Builds payload for success responses with results"
    (let [exp {:status "Success", :results [{:a "a"}]},
          res (build-success-payload [{:a "a"}])]
          (is (= exp res)))))

(deftest build-error-payload-response
  (testing "Builds payload for error responses"
    (let [exp {:status "Error", :error_message "error"},
          res (build-error-payload "error")]
          (is (= exp res)))))

(deftest build-response-map
  (testing "Builds the http response map"
    (let [exp {:status 200, :headers {"Content-Type" "application/json"}, :body "body"}
          res (build-response 200 "body")]
      (is (= exp res)))))