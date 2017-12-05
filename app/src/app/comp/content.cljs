
(ns app.comp.content
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp cursor-> list-> <> span div input button]]
            [respo.comp.space :refer [=<]]
            [app.comp.profile :refer [comp-profile]]))

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
       (render-section :do "Do" (:name router))
       (render-section :queued "Queued" (:name router))
       (render-section :done "Done" (:name router)))
      (div
       {:style (merge ui/flex {:padding-top 32, :margin-left 32})}
       (div
        {}
        (input
         {:value state,
          :style (merge ui/input {:width 320}),
          :on {:input (fn [e d! m!] (m! (:value e)))}})
        (=< 16 nil)
        (button
         {:style ui/button, :on {:click (fn [e d! m!] (d! :task/create state) (m! ""))}}
         (<> "Add")))
       (list->
        :div
        {}
        (->> (:data router)
             (map
              (fn [entry] (let [[task-id task] entry] [task-id (div {} (<> (:text task)))]))))))))))
