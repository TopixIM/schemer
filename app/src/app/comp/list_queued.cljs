
(ns app.comp.list-queued
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp list-> <> span div]]
            [app.comp.task-queued :refer [comp-task-queued]]))

(defcomp
 comp-list-queued
 (task-map)
 (list->
  :div
  {}
  (->> task-map
       (sort (fn [pa pb] (- (:time (val pb)) (:time (val pa)))))
       (map (fn [entry] (let [[task-id task] entry] [task-id (comp-task-queued task)]))))))
