
(ns app.comp.task-operator
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div input]]
            [app.comp.icon :refer [comp-icon]]
            [respo.comp.space :refer [=<]]
            [keycode.core :as keycode]))

(defcomp
 comp-task-operator
 (states task)
 (let [state (or (:data states) {:draft nil})]
   (div
    {:style {:width "100%", :height 32, :padding 8, :text-align :right}}
    (div
     {}
     (span
      {:style {:cursor :pointer},
       :on {:click (fn [e d! m!] (d! :task/swap-kind {:id (:id task), :kind :doing}))}}
      (comp-icon "arrow-swap" nil nil))
     (=< 16 nil)
     (span
      {:style {:cursor :pointer},
       :on {:click (fn [e d! m!] (d! :task/top {:id (:id task), :kind :doing}))}}
      (comp-icon "android-arrow-up" nil nil))
     (=< 16 nil)
     (if (some? (:draft state))
       (input
        {:value (:draft state),
         :placeholder "new task",
         :style ui/input,
         :autofocus true,
         :on {:input (fn [e d! m!] (m! (assoc state :draft (:value e)))),
              :keydown (fn [e d! m!]
                (if (= (:keycode e) keycode/return)
                  (do
                   (d! :task/edit {:kind :doing, :id (:id task), :text (:draft state)})
                   (m! (assoc state :draft nil)))))}})
       (span
        {:style {:cursor :pointer},
         :on {:click (fn [e d! m!] (m! (assoc state :draft (:text task))))}}
        (comp-icon "edit" nil nil)))))))
