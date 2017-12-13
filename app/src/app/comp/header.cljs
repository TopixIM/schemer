
(ns app.comp.header
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp list-> <> span div]]
            [app.theme :as theme]
            [app.style :as style]))

(defcomp
 comp-header
 (title)
 (div
  {:style {:line-height "48px",
           :padding "0 16px",
           :background-color theme/wet,
           :color :white,
           :font-family style/font-fancy,
           :font-size 20}}
  (<> title)))
