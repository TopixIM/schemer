
(ns app.comp.profile
  (:require [hsl.core :refer [hsl]]
            [app.schema :as schema]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div a]]
            [respo.comp.space :refer [=<]]
            [app.comp.header :refer [comp-header]]
            [app.theme :as theme]))

(defn on-log-out [e dispatch!]
  (dispatch! :user/log-out nil)
  (.removeItem js/localStorage (:storage-key schema/configs)))

(def style-trigger
  {:font-size 14,
   :cursor :pointer,
   :background-color theme/wet,
   :color :white,
   :padding "0 8px"})

(defcomp
 comp-profile
 (user)
 (div
  {:style ui/flex}
  (comp-header "Profile")
  (div
   {:style {:padding 16}}
   (<> span (str "Hello! " (:name user)) nil)
   (=< 8 nil)
   (a {:style style-trigger, :on {:click on-log-out}} (<> span "Log out" nil)))))
