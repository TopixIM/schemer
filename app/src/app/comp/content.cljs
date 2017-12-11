
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

(defcomp
 comp-content
 (states router user)
 (let [state (or (:data states) {:draft ""})]
   (case (:name router)
     :profile (comp-profile user)
     (div
      {:style (merge ui/flex ui/column)}
      (if (contains? #{:doing :queued} (:name router))
        (div
         {}
         (input
          {:value (:draft state),
           :placeholder "write task here",
           :style (merge
                   ui/input
                   {:width "100%", :display :block, :font-size 16, :padding "0 16px"}),
           :on {:input (fn [e d! m!] (m! (assoc state :draft (:value e)))),
                :keydown (fn [e d! m!]
                  (if (= (:keycode e) keycode/return)
                    (do (d! :task/create (:draft state)) (m! (assoc state :draft "")))))}})))
      (=< nil 16)
      (case (:name router)
        :doing (cursor-> :doing comp-list-doing states (:data router))
        :queued (comp-list-queued (:data router))
        :done (comp-list-done (:data router))
        (<> router))))))
