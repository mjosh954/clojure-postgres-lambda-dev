(defproject lambda-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [ring/ring-core "1.6.3"]
    [ring/ring-jetty-adapter "1.6.3"]
    [ring/ring-json "0.4.0"]
    [korma "0.4.0"]
    [org.postgresql/postgresql "42.2.5.jre7"]]
  :main ^:skip-aot command.entry)
