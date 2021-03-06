
(ns app.main
  (:require [respo.core :refer [render! clear-cache! realize-ssr!]]
            [respo.cursor :refer [mutate]]
            [app.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]
            [app.network :refer [send! setup-socket!]]
            [app.schema :as schema]))

(declare dispatch!)

(declare connect!)

(declare simulate-login!)

(def ssr? (some? (.querySelector js/document "meta.respo-ssr")))

(defonce *store (atom nil))

(defonce *states (atom {}))

(defn simulate-login! []
  (let [raw (.getItem js/localStorage (:storage-key schema/configs))]
    (if (some? raw)
      (do (println "Found storage.") (dispatch! :user/log-in (read-string raw)))
      (do (println "Found no storage.")))))

(defn connect! []
  (setup-socket!
   *store
   {:url (str "ws://" (.-hostname js/location) ":" (:port schema/configs)),
    :on-close! (fn [event] (reset! *store nil) (.error js/console "Lost connection!")),
    :on-open! (fn [event] (simulate-login!))}))

(defn dispatch! [op op-data]
  (if (not= op :states) (println "Dispatch" op op-data))
  (case op
    :states (reset! *states ((mutate op-data) @*states))
    :effect/connect (connect!)
    (send! op op-data)))

(def mount-target (.querySelector js/document ".app"))

(defn render-app! [renderer]
  (renderer mount-target (comp-container @*states @*store) #(dispatch! %1 %2)))

(defn main! []
  (if ssr? (render-app! realize-ssr!))
  (render-app! render!)
  (connect!)
  (add-watch *store :changes #(render-app! render!))
  (add-watch *states :changes #(render-app! render!))
  (println "App started!"))

(defn reload! [] (clear-cache!) (render-app! render!) (println "Code updated."))

(set! js/window.onload main!)
