
(ns app.style (:require [app.theme :as theme] [respo-ui.style :as ui]))

(def font-fancy "Josefin Sans, Helvetica, Arial, sans-serif")

(def button (merge ui/button {:background-color theme/wet}))
