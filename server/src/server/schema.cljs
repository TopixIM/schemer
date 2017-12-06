
(ns server.schema )

(def user {:name nil, :id nil, :nickname nil, :avatar nil, :password nil})

(def configs {:storage-key "/data/cumulo/schemer.edn", :port 5021})

(def database {:sessions {}, :users {}, :doing {}, :queued {}, :done {}})

(def session
  {:user-id nil, :id nil, :nickname nil, :router {:name :doing, :data nil}, :notifications []})

(def notification {:id nil, :kind nil, :text nil})

(def router {:name nil, :data {}})

(def task {:id nil, :text "", :time nil})
