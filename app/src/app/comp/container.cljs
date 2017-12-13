
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp cursor-> <> div span button]]
            [respo.comp.inspect :refer [comp-inspect]]
            [respo.comp.space :refer [=<]]
            [app.comp.footer :refer [comp-footer]]
            [app.comp.login :refer [comp-login]]
            [respo-message.comp.msg-list :refer [comp-msg-list]]
            [app.comp.reel :refer [comp-reel]]
            [app.comp.content :refer [comp-content]]
            [app.style :as style]
            [app.theme :as theme]))

(def style-alert {:font-family style/font-fancy, :font-size 40, :font-weight 100})

(def style-debugger {:bottom 0, :left 0, :max-width "100%"})

(defcomp
 comp-container
 (states store)
 (let [state (:data states), session (:session store), router (:router store)]
   (if (nil? store)
     (div
      {:style (merge
               ui/global
               ui/fullscreen
               ui/center
               {:background-color theme/wet, :color :white})}
      (span
       {:style {:cursor :pointer}, :on {:click (fn [e d! m!] (d! :effect/connect nil))}}
       (<> "No connection!" style-alert)))
     (div
      {}
      (if (:logged-in? store)
        (div
         {:style (merge ui/global ui/fullscreen ui/column)}
         (cursor-> :content comp-content states router (:user store))
         (comp-footer router (:logged-in? store)))
        (div {} (comp-login states)))
      (comp-msg-list (get-in store [:session :notifications]) :session/remove-notification)
      (comment comp-reel (:reel-length store) {})
      (comment comp-inspect "Store" store style-debugger)))))

(def style-body {:padding "8px 16px"})
