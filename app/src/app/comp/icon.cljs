
(ns app.comp.icon
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]
            [respo.core :refer [create-element]]))

(defcomp
 comp-icon
 (icon-name style on-click)
 (span
  {:class-name (str "icon ion-" icon-name),
   :style (merge {:font-size 16} style),
   :on (if (fn? on-click) {:click (fn [e d! m!] (on-click e d! m!))})}))
