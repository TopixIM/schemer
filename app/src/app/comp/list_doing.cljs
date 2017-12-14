
(ns app.comp.list-doing
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp cursor-> list-> <> span div]]
            [app.comp.task-doing :refer [comp-task-doing]]
            [respo.comp.space :refer [=<]]
            [app.comp.icon :refer [comp-icon]]
            [app.theme :as theme]
            [app.style :as style]
            [app.comp.header :refer [comp-header]]))

(defcomp
 comp-list-doing
 (states task-map)
 (let [state (or (:data states) {:focused-id nil})]
   (div
    {}
    (comp-header "Schemer")
    (if (empty? task-map)
      (div
       {:style {:padding "8px 16px", :color (hsl 0 0 79), :font-family style/font-fancy}}
       (<> "No tasks" nil))
      (list->
       :div
       {:style {}}
       (->> task-map
            (sort (fn [pa pb] (- (:time (val pb)) (:time (val pa)))))
            (map
             (fn [entry]
               (let [[task-id task] entry
                     on-focus! (fn [m!] (m! *cursor* (assoc state :focused-id task-id)))]
                 [task-id
                  (cursor->
                   task-id
                   comp-task-doing
                   states
                   task
                   (:focused-id state)
                   (= task-id (:focused-id state))
                   on-focus!)])))))))))
