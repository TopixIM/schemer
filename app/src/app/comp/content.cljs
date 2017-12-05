
(ns app.comp.content
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div input button]]
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
 (router store)
 (case (:name router)
   :profile (comp-profile (:user store))
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
      (input {:value "Content", :style (merge ui/input {:width 320})})
      (=< 16 nil)
      (button {:style ui/button} (<> "Add")))
     (div {} (<> "list"))))))
