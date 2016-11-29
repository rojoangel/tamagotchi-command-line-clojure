(ns tamagotchi-kata-clojure.core)

(defn eat [tamagotchi]
  (swap! tamagotchi update-in [:hungriness] dec))

(defn create []
  (atom {:hungriness 6}))
