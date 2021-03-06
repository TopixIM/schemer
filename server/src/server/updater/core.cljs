
(ns server.updater.core
  (:require [server.updater.session :as session]
            [server.updater.user :as user]
            [server.updater.router :as router]
            [server.updater.task :as task]))

(defn updater [db op op-data session-id op-id op-time]
  (case op
    :session/connect (session/connect db op-data session-id op-id op-time)
    :session/disconnect (session/disconnect db op-data session-id op-id op-time)
    :session/remove-notification
      (session/remove-notification db op-data session-id op-id op-time)
    :session/dialog (session/dialog db op-data session-id op-id op-time)
    :user/log-in (user/log-in db op-data session-id op-id op-time)
    :user/sign-up (user/sign-up db op-data session-id op-id op-time)
    :user/log-out (user/log-out db op-data session-id op-id op-time)
    :router/change (router/change db op-data session-id op-id op-time)
    :task/create (task/create db op-data session-id op-id op-time)
    :task/mark-as-done (task/mark-as-done db op-data session-id op-id op-time)
    :task/swap-kind (task/swap-kind db op-data session-id op-id op-time)
    :task/top (task/top db op-data session-id op-id op-time)
    :task/edit (task/edit db op-data session-id op-id op-time)
    :task/remove-done (task/remove-done db op-data session-id op-id op-time)
    db))
