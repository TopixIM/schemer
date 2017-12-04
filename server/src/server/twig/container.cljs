
(ns server.twig.container
  (:require [recollect.macros :refer [deftwig]] [server.twig.user :refer [twig-user]]))

(deftwig
 twig-container
 (db session records)
 (let [logged-in? (some? (:user-id session))
       routes (:routes session)
       base-data {:logged-in? logged-in?,
                  :session session,
                  :count (:count db),
                  :reel-length (count records)}]
   (merge
    base-data
    (if logged-in?
      {:user (twig-user (get-in db [:users (:user-id session)])), :routes routes}
      nil))))
