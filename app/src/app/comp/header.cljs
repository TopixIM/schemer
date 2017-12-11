
(ns app.comp.header
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]))

(defn on-profile [e dispatch!] (dispatch! :router/change {:name :profile, :data nil}))

(def style-pointer {:cursor "pointer", :padding-right 8})

(def style-header
  {:height 48,
   :justify-content :space-between,
   :padding "0px",
   :font-size 16,
   :font-weight 100,
   :font-family "Josefin Sans, Helvetica, sans-serif"})

(defn render-section [key text router-name]
  (div
   {:style (merge
            {:font-family "Josefin Sans, Helvetica, sans-serif",
             :font-size 20,
             :font-weight "300",
             :text-align :right,
             :cursor :pointer,
             :display :inline-block,
             :margin "0 8px",
             :color (hsl 0 0 60)}
            (if (= key router-name) {:color (hsl 0 0 20)})),
    :on {:click (fn [e d! m!] (d! :router/change {:name key}))}}
   (<> text)))

(defcomp
 comp-header
 (router logged-in?)
 (div
  {:style (merge ui/row-center style-header)}
  (div
   {}
   (render-section :doing "Schemer" (:name router))
   (render-section :queued "Queued" (:name router))
   (render-section :done "Done" (:name router)))
  (div
   {:style style-pointer, :on {:click on-profile}}
   (<> span (if logged-in? "Me" "Guest") nil))))
