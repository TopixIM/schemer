
(ns app.comp.list-doing
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp cursor-> list-> <> span div]]
            [app.comp.task-doing :refer [comp-task-doing]]
            [respo.comp.space :refer [=<]]
            [app.comp.icon :refer [comp-icon]]
            [app.comp.task-operator :refer [comp-task-operator]]))

(defcomp
 comp-list-doing
 (states task-map)
 (let [state (or (:data states) {:focused-id nil})
       focused-task (if (some? (:focused-id state)) (get task-map (:focused-id state)) nil)]
   (div
    {:style ui/row}
    (list->
     :div
     {}
     (->> task-map
          (sort (fn [pa pb] (- (:time (val pb)) (:time (val pa)))))
          (map
           (fn [entry]
             (let [[task-id task] entry
                   on-focus! (fn [m!] (m! (assoc state :focused-id task-id)))]
               [task-id (comp-task-doing task (:focused-id state) on-focus!)])))))
    (=< 16 nil)
    (if (some? focused-task) (cursor-> :operator comp-task-operator states focused-task)))))
