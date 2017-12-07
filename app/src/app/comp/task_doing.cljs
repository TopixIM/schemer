
(ns app.comp.task-doing
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]
            [app.comp.icon :refer [comp-icon]]
            [respo.comp.space :refer [=<]]))

(def style-task
  {:line-height "32px",
   :justify-content :flex-start,
   :width "100%",
   :background-color (hsl 0 0 96),
   :padding "0 8px",
   :margin-bottom 8,
   :cursor :pointer})

(defcomp
 comp-task-doing
 (task focused-id on-focus!)
 (div
  {:style (merge
           ui/row-center
           style-task
           (if (= (:id task) focused-id) {:background-color (hsl 200 40 90)})),
   :on {:click (fn [e d! m!] (on-focus! m!))}}
  (div
   {}
   (span
    {:style {:cursor :pointer},
     :on {:click (fn [e d! m!] (d! :task/mark-as-done (:id task)))}}
    (comp-icon "android-done" nil)))
  (=< 8 nil)
  (<> (:text task))))
