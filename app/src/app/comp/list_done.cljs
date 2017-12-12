
(ns app.comp.list-done
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp list-> <> span div]]
            [app.comp.task-done :refer [comp-task-done]]))

(defcomp
 comp-list-done
 (task-map)
 (list->
  :div
  {:style {:overflow :auto, :padding "16px 0 40px 0"}}
  (->> task-map
       (sort (fn [pa pb] (- (:time (val pb)) (:time (val pa)))))
       (map (fn [entry] (let [[task-id task] entry] [task-id (comp-task-done task)]))))))
