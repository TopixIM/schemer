
(ns server.schema )

(def user {:name nil, :id nil, :nickname nil, :avatar nil, :password nil})

(def configs {:storage-key "/data/cumulo/schemer.edn", :port 5021})

(def database {:sessions {}, :users {}, :topics {}, :count 0})

(def session
  {:user-id nil, :id nil, :nickname nil, :router {:name :home, :data nil}, :notifications []})

(def notification {:id nil, :kind nil, :text nil})

(def router {:name nil, :data {}})
