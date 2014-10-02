(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :repositories [["central-proxy" "http://repository.sonatype.org/content/repositories/central/"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 {{#datomic?}}[com.datomic/datomic-free "0.9.4899"]{{/datomic?}}]
  :jvm-opts ["-Xmx1G"]

  :plugins [[cider/cider-nrepl "0.8.0-SNAPSHOT"]])
