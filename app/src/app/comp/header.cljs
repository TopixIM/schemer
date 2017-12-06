
(ns app.comp.header
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]))

(defn on-profile [e dispatch!] (dispatch! :router/change {:name :profile, :data nil}))

(def style-logo {:cursor :pointer, :font-size 24, :font-weight :lighter})

(def style-pointer {:cursor "pointer"})

(def style-header
  {:height 48,
   :justify-content :space-between,
   :padding "0 16px",
   :font-size 16,
   :font-weight 100,
   :font-family "Josefin Sans, Helvetica, sans-serif"})

(defn on-home [e dispatch!] (dispatch! :router/change {:name :doing, :data nil}))

(defcomp
 comp-header
 (logged-in?)
 (div
  {:style (merge ui/row-center style-header)}
  (div {:on {:click on-home}, :style style-logo} (<> span "Schemer" nil))
  (div
   {:style style-pointer, :on {:click on-profile}}
   (<> span (if logged-in? "Me" "Guest") nil))))
