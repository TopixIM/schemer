
(ns app.comp.task-queued
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]
            [app.comp.icon :refer [comp-icon]]
            [respo.comp.space :refer [=<]]))

(defcomp
 comp-task-queued
 (task)
 (div
  {:style (merge
           ui/row-center
           {:line-height "40px",
            :width "100%",
            :justify-content :space-between,
            :padding "0 16px",
            :font-size 16,
            :border-bottom (str "1px solid " (hsl 0 0 90))})}
  (<> (:text task))
  (div
   {}
   (span
    {:style {:cursor :pointer},
     :on {:click (fn [e d! m!] (d! :task/swap-kind {:kind :queued, :id (:id task)}))}}
    (comp-icon "arrow-swap" nil))
   (=< 8 nil)
   (span
    {:style {:cursor :pointer},
     :on {:click (fn [e d! m!] (d! :task/mark-as-done (:id task)))}}
    (comp-icon "android-done" nil)))))
