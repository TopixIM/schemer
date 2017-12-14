
(ns app.comp.list-queued
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp list-> <> span div]]
            [app.comp.task-queued :refer [comp-task-queued]]
            [app.theme :as theme]
            [app.style :as style]
            [app.comp.header :refer [comp-header]]))

(defcomp
 comp-list-queued
 (task-map)
 (div
  {}
  (comp-header "Queued")
  (if (empty? task-map)
    (div
     {:style {:padding "8px 16px", :color (hsl 0 0 79), :font-family style/font-fancy}}
     (<> "No tasks" nil))
    (list->
     :div
     {:style (merge ui/flex {:overflow :auto})}
     (->> task-map
          (sort (fn [pa pb] (- (:time (val pb)) (:time (val pa)))))
          (map (fn [entry] (let [[task-id task] entry] [task-id (comp-task-queued task)]))))))))
