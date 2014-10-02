(ns leiningen.new.vagrant
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "vagrant"))

(defn fill [data & files]
  (apply ->files data
         (keep (fn [[dst src]]
                 (when dst [dst (render src data)]))
               files)))

(defn vagrant
  "Creates a new Vagrant-powered project."
  [name & opts]
  (let [opts (set (map keyword opts))
        datomic? (:+datomic opts)
        data {:name name
              :sanitized (name-to-path name)
              :datomic? datomic?}]
    (main/info (format "Generating fresh 'lein new' Vagrant-powered Clojure%s project."
                       (if datomic? "/Datomic" "")))
    (fill data
          [".gitignore" "gitignore"]
          ["project.clj" "project.clj"]
          ["Vagrantfile" "Vagrantfile"]
          ["src/{{sanitized}}/core.clj" "core.clj"]
          ["ansible/provision.yml" "provision.yml"]
          ["ansible/files/datomic-transactor.properties" "datomic-transactor.properties"]
          ["ansible/files/datomic-upstart.conf" "datomic-upstart.conf"]
          ["ansible/files/nrepl-upstart.conf" "nrepl-upstart.conf"]
          ["ansible/vars/app" "app"]
          (when datomic? ["ansible/vars/database" "database"])
          ["ansible/dev" "dev"])))
