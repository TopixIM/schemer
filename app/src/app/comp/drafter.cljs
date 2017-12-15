
(ns app.comp.drafter
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div input button]]
            [respo.comp.space :refer [=<]]
            [app.comp.icon :refer [comp-icon]]
            [app.style :as style]
            [app.theme :as theme]
            [clojure.string :as string]))

(defcomp
 comp-drafter
 (states)
 (let [state (or (:data states) "")]
   (div
    {:style (merge
             ui/fullscreen
             ui/center
             {:position :fixed, :background-color (hsl 0 0 0 0.4)})}
    (div
     {:style {:padding 16, :background-color :white, :width "88%"}}
     (div
      {:style (merge ui/row-center {:justify-content :space-between})}
      (<> "Create Task" nil)
      (span
       {:on {:click (fn [e d! m!] (d! :session/dialog nil))}}
       (comp-icon "close" nil nil)))
     (=< nil 8)
     (div
      {}
      (input
       {:value state,
        :style (merge ui/input {:width "100%"}),
        :placeholder "new task content",
        :on {:input (fn [e d! m!] (m! (:value e)))}}))
     (=< nil 8)
     (div
      {:style (merge ui/row {:justify-content :flex-end})}
      (button
       {:style style/button,
        :on {:click (fn [e d! m!]
               (if (not (string/blank? state))
                 (do (d! :task/create state) (d! :session/dialog nil) (m! nil))))}}
       (<> "Create" nil)))))))
