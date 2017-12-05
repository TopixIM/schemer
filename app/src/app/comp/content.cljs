
(ns app.comp.content
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp cursor-> list-> <> span div input button]]
            [respo.comp.space :refer [=<]]
            [app.comp.profile :refer [comp-profile]]
            [app.comp.icon :refer [comp-icon]]
            [app.comp.task-doing :refer [comp-task-doing]]
            [app.comp.task-queued :refer [comp-task-queued]]
            [app.comp.task-done :refer [comp-task-done]]
            [keycode.core :as keycode]))

(defn render-section [key text router-name]
  (div
   {:style (merge
            {:font-family "Josefin Sans, Helvetica, sans-serif",
             :font-size 20,
             :font-weight "100",
             :text-align :right,
             :padding-right 32,
             :cursor :pointer}
            (if (= key router-name) {:font-weight 300})),
    :on {:click (fn [e d! m!] (d! :router/change {:name key}))}}
   (<> text)))

(defcomp
 comp-content
 (states router user)
 (let [state (or (:data states) "")]
   (case (:name router)
     :profile (comp-profile user)
     (div
      {:style (merge ui/flex ui/row)}
      (div
       {:style {:width 320, :padding-top 64}}
       (render-section :doing "Doing" (:name router))
       (render-section :queued "Queued" (:name router))
       (render-section :done "Done" (:name router)))
      (div
       {:style (merge ui/flex {:padding-top 32, :margin-left 32})}
       (div
        {}
        (input
         {:value state,
          :placeholder "write task here",
          :style (merge ui/input {:width 320}),
          :on {:input (fn [e d! m!] (m! (:value e))),
               :keydown (fn [e d! m!]
                 (if (= (:keycode e) keycode/return) (do (d! :task/create state) (m! ""))))}})
        (=< 16 nil)
        (button
         {:style ui/button, :on {:click (fn [e d! m!] (d! :task/create state) (m! ""))}}
         (<> "Add")))
       (=< nil 16)
       (list->
        :div
        {}
        (->> (:data router)
             (sort (fn [pa pb] (- (:created-time (val pb)) (:created-time (val pa)))))
             (map
              (fn [entry]
                (let [[task-id task] entry]
                  [task-id
                   (case (:name router)
                     :doing (comp-task-doing task)
                     :queued (comp-task-queued task)
                     :done (comp-task-done task)
                     (<> task))]))))))))))
