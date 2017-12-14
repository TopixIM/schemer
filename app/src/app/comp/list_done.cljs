
(ns app.comp.list-done
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp list-> <> span div]]
            [app.comp.task-done :refer [comp-task-done]]
            [app.theme :as theme]
            [app.style :as style]
            [app.comp.header :refer [comp-header]]))

(defcomp
 comp-list-done
 (task-map)
 (div
  {:style (merge ui/flex ui/column)}
  (comp-header "Done")
  (if (empty? task-map)
    (div
     {:style {:padding "8px 16px", :color (hsl 0 0 79), :font-family style/font-fancy}}
     (<> "No tasks" nil))
    (list->
     :div
     {:style (merge ui/flex {:overflow :auto, :padding "16px 0 40px 0"})}
     (->> task-map
          (sort (fn [pa pb] (- (:time (val pb)) (:time (val pa)))))
          (map (fn [entry] (let [[task-id task] entry] [task-id (comp-task-done task)]))))))))
