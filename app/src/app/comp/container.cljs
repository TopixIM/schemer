
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp cursor-> <> div span button]]
            [respo.comp.inspect :refer [comp-inspect]]
            [respo.comp.space :refer [=<]]
            [app.comp.header :refer [comp-header]]
            [app.comp.login :refer [comp-login]]
            [respo-message.comp.msg-list :refer [comp-msg-list]]
            [app.comp.reel :refer [comp-reel]]
            [app.comp.content :refer [comp-content]]))

(def style-alert {:font-family "Josefin Sans", :font-weight 100, :font-size 40})

(def style-debugger {:bottom 0, :left 0, :max-width "100%"})

(defcomp
 comp-container
 (states store)
 (let [state (:data states), session (:session store), router (:router store)]
   (if (nil? store)
     (div
      {:style (merge ui/global ui/fullscreen ui/center)}
      (span
       {:style {:cursor :pointer}, :on {:click (fn [e d! m!] (d! :effect/connect nil))}}
       (<> "No connection!" style-alert)))
     (div
      {:style (merge ui/global ui/fullscreen ui/column)}
      (comp-header router (:logged-in? store))
      (if (:logged-in? store)
        (cursor-> :content comp-content states router (:user store))
        (comp-login states))
      (comp-inspect "Store" store style-debugger)
      (comp-msg-list (get-in store [:session :notifications]) :session/remove-notification)
      (comp-reel (:reel-length store) {})))))

(def style-body {:padding "8px 16px"})
