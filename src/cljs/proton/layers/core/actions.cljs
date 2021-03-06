(ns proton.layers.core.actions
  (:require [proton.lib.package_manager :as package]))

(def state (atom {}))

(defn get-active-editor [atom]
  (.getView (.-views atom) (.getActiveTextEditor (.-workspace atom))))

(defn get-active-pane [atom]
  (.getView (.-views atom) (.getActivePane (.-workspace atom))))

(defn toggle-tabs [visible]
  (if visible
    (package/enable-package "tabs")
    (package/disable-package "tabs")))

(defn toggle-relative-lines [visible]
  (if visible
    (package/enable-package "relative-numbers")
    (package/disable-package "relative-numbers")))

(defn- unmaximize-window []
  (let [ipc (.require js/window "ipc")]
    (.send ipc "call-window-method" "unmaximize")))

(defn toggle-maximize []
  (if (.isMaximized js/atom)
    (unmaximize-window)
    (.maximize js/atom)))
