(ns vagrant-sample.core
  (:require [datomic.api :as d]))

(def uri "datomic:free://localhost:4334/test")

(d/create-database uri)

(def conn (d/connect uri))

;; If conn gets bound to some #<Connection ..> value then Datomic works well.

(def tx-result
  (d/transact
   conn
   [[:db/add (d/tempid :db.part/user)
     :db/doc "Hello world"]]))

(def q-result
  (d/q '[:find ?e
         :where [?e :db/doc "Hello world"]]
       (d/db conn)))
