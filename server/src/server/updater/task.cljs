
(ns server.updater.task (:require [server.schema :as schema]))

(defn create [db op-data sid op-id op-time]
  (let [router-name (get-in db [:sessions sid :router :name])]
    (if (contains? #{:doing :queued} router-name)
      (assoc-in
       db
       [router-name op-id]
       (merge schema/task {:id op-id, :text op-data, :time op-time}))
      db)))

(defn mark-as-done [db op-data sid op-id op-time]
  (let [router (get-in db [:sessions sid :router]), task (get-in db [(:name router) op-data])]
    (-> db
        (update (:name router) (fn [tasks] (dissoc tasks op-data)))
        (assoc-in [:done op-data] task))))

(defn swap-kind [db op-data session-id op-id op-time]
  (let [kind (:kind op-data)
        task-id (:id op-data)
        task (get-in db [kind task-id])
        another-kind (if (= kind :doing) :queued :doing)]
    (case kind
      :doing
        (-> db
            (update kind (fn [tasks] (dissoc tasks task-id)))
            (assoc-in [another-kind task-id] task))
      :queued
        (-> db
            (update kind (fn [tasks] (dissoc tasks task-id)))
            (assoc-in [another-kind task-id] task))
      db)))

(defn top [db op-data sid op-id op-time]
  (let [kind (:kind op-data), id (:id op-data)] (assoc-in db [kind id :time] op-time)))

(defn edit [db op-data session-id op-id op-time]
  (let [id (:id op-data), kind (:kind op-data), text (:text op-data)]
    (println "write")
    (assoc-in db [kind id :text] text)))
