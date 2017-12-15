
(ns app.comp.footer
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]
            [app.comp.icon :refer [comp-icon]]
            [app.style :as style]))

(def style-pointer {:cursor "pointer", :padding-right 16})

(defn render-section [key icon-name router-name]
  (div
   {:style (merge
            ui/flex
            {:margin "0 16px", :color (hsl 0 0 60), :text-align :center}
            (if (= key router-name) {:color (hsl 0 0 0)})),
    :on {:click (fn [e d! m!] (d! :router/change {:name key}))}}
   (comp-icon icon-name {:font-size 32} nil)))

(def style-footer
  {:height 48,
   :padding "0px",
   :font-size 16,
   :font-weight 100,
   :font-family style/font-fancy,
   :border-top (str "1px solid " (hsl 0 0 90))})

(defcomp
 comp-footer
 (router logged-in?)
 (div
  {:style (merge ui/row-center style-footer)}
  (render-section :doing "android-home" (:name router))
  (render-section :queued "android-list" (:name router))
  (render-section :done "android-cloud-done" (:name router))
  (render-section :profile "android-person" (:name router))))
