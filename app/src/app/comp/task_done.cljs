
(ns app.comp.task-done
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]
            [app.comp.icon :refer [comp-icon]]
            [respo.comp.space :refer [=<]]))

(defcomp
 comp-task-done
 (task)
 (div
  {:style {:padding "0 16px"}}
  (<> (:text task))
  (comp-icon
   "ios-trash-outline"
   {:margin-left 8}
   (fn [e d! m!] (d! :task/remove-done (:id task))))))
