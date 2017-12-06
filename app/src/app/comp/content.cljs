
(ns app.comp.content
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp cursor-> list-> <> span div input button]]
            [respo.comp.space :refer [=<]]
            [app.comp.profile :refer [comp-profile]]
            [app.comp.icon :refer [comp-icon]]
            [app.comp.list-doing :refer [comp-list-doing]]
            [app.comp.list-queued :refer [comp-list-queued]]
            [app.comp.list-done :refer [comp-list-done]]
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
 (let [state (or (:data states) {:draft ""})]
   (case (:name router)
     :profile (comp-profile user)
     (div
      {:style (merge ui/flex ui/row)}
      (div
       {:style {:width 240, :padding-top 64}}
       (render-section :doing "Doing" (:name router))
       (render-section :queued "Queued" (:name router))
       (render-section :done "Done" (:name router)))
      (div
       {:style (merge ui/flex {:padding-top 32, :margin-left 32})}
       (if (contains? #{:doing :queued} (:name router))
         (div
          {}
          (input
           {:value (:draft state),
            :placeholder "write task here",
            :style (merge ui/input {:width 320}),
            :on {:input (fn [e d! m!] (m! (assoc state :draft (:value e)))),
                 :keydown (fn [e d! m!]
                   (if (= (:keycode e) keycode/return)
                     (do (d! :task/create (:draft state)) (m! (assoc state :draft "")))))}})))
       (=< nil 16)
       (case (:name router)
         :doing (cursor-> :doing comp-list-doing states (:data router))
         :queued (comp-list-queued (:data router))
         :done (comp-list-done (:data router))
         (<> router)))))))
