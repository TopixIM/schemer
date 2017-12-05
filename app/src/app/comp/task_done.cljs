
(ns app.comp.task-done
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo-ui.style.colors :as colors]
            [respo.macros :refer [defcomp <> span div]]))

(defcomp comp-task-done (task) (div {} (<> (:text task))))
