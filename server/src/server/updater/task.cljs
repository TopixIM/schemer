
(ns server.updater.task (:require [server.schema :as schema]))

(defn create [db op-data sid op-id op-time]
  (let [router-name (get-in db [:sessions sid :router :name])]
    (assoc-in db [router-name op-id] (merge schema/task {:id op-id, :text op-data}))))
