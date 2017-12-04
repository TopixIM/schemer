
(ns app.comp.header
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]))

(defn on-profile [e dispatch!] (dispatch! :router/nav-top {:name :profile, :data nil}))

(def style-logo {:cursor :pointer})

(def style-pointer {:cursor "pointer"})

(def style-header
  {:height 48,
   :background-color colors/motif,
   :justify-content :space-between,
   :padding "0 16px",
   :font-size 16,
   :color :white})

(defn on-home [e dispatch!] (dispatch! :router/nav-top {:name :home, :data nil}))

(defcomp
 comp-header
 (logged-in?)
 (div
  {:style (merge ui/row-center style-header)}
  (div {:on {:click on-home}, :style style-logo} (<> span "Cumulo" nil))
  (div
   {:style style-pointer, :on {:click on-profile}}
   (<> span (if logged-in? "Me" "Guest") nil))))
