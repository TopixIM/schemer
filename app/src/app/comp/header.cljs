
(ns app.comp.header
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp list-> <> span div]]
            [app.theme :as theme]
            [app.style :as style]
            [app.comp.icon :refer [comp-icon]]))

(defcomp
 comp-header
 (title)
 (div
  {:style (merge
           ui/row-center
           {:line-height "48px",
            :justify-content :space-between,
            :padding "0 16px",
            :background-color theme/wet,
            :color :white,
            :font-family style/font-fancy,
            :font-size 20})}
  (<> title)
  (comp-icon "plus" {:font-size 16} (fn [e d! m!] (d! :session/dialog {:name :create})))))
