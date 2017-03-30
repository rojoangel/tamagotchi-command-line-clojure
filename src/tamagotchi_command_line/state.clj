(ns tamagotchi-command-line.state
  (:require [tamagotchi.core :as tamagotchi]))

(def tamagotchi (atom {}))

(defn feed []
  (swap! tamagotchi tamagotchi/feed))

(defn play []
  (swap! tamagotchi tamagotchi/play))

(defn put-to-bed []
  (swap! tamagotchi tamagotchi/put-to-bed))

(defn make-poop []
  (swap! tamagotchi tamagotchi/make-poop))

(defn tic []
  (swap! tamagotchi tamagotchi/tic))

(defn kill []
  (reset! tamagotchi {}))

(defn is-dead? []
  (empty? @tamagotchi))

(defn breed [name hungriness fullness happiness tiredness]
  (add-watch tamagotchi
             :is-death?
             (fn [key atom old-state new-state]
               (when (or (= (:fullness new-state) tamagotchi/max-attribute-value)
                         (= (:hungriness new-state) tamagotchi/max-attribute-value)
                         (= (:tiredness new-state) tamagotchi/max-attribute-value)
                         (= (:happiness new-state) tamagotchi/min-attribute-value))
                 (kill))))
  (reset! tamagotchi (tamagotchi/breed name hungriness fullness happiness tiredness)))

(defn create
  [& {:keys [name hungriness fullness happiness tiredness]
      :or   {name       "Miyagi"
             hungriness tamagotchi/default-initial-attribute-value
             fullness   tamagotchi/default-initial-attribute-value
             happiness  tamagotchi/default-initial-attribute-value
             tiredness  tamagotchi/default-initial-attribute-value}}]
  (breed name hungriness fullness happiness tiredness))
