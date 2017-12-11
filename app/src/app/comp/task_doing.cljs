
(ns app.comp.task-doing
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp cursor-> <> span div]]
            [app.comp.icon :refer [comp-icon]]
            [respo.comp.space :refer [=<]]
            [app.comp.task-operator :refer [comp-task-operator]]))

(def style-task
  {:line-height "40px",
   :justify-content :flex-start,
   :width "100%",
   :cursor :pointer,
   :padding "0 16px",
   :font-size 16})

(defcomp
 comp-task-doing
 (states task focused-id focused? on-focus!)
 (div
  {:style (merge
           {:width "100%", :border-bottom (str "1px solid " (hsl 0 0 94))}
           (if (= (:id task) focused-id) {:background-color (hsl 200 0 97)}))}
  (div
   {:style (merge ui/row-center style-task), :on {:click (fn [e d! m!] (on-focus! m!))}}
   (div
    {}
    (span
     {:style {:cursor :pointer},
      :on {:click (fn [e d! m!] (d! :task/mark-as-done (:id task)))}}
     (comp-icon "android-done" nil)))
   (=< 8 nil)
   (<> (:text task)))
  (if focused? (cursor-> :operator comp-task-operator states task))))
