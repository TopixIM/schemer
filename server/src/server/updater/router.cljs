
(ns server.updater.router )

(defn nav-top [db op-data session-id op-id op-time]
  (assoc-in db [:sessions session-id :routes] [op-data]))
