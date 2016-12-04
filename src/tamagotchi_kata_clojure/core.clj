(ns tamagotchi-kata-clojure.core)

(defn- decrease [keyword tamagotchi]
  (update-in tamagotchi [keyword] dec))

(defn- increase [keyword tamagotchi]
  (update-in tamagotchi [keyword] inc))

(defn feed [tamagotchi]
  (swap! tamagotchi
         (comp #(decrease :hungriness %)
               #(increase :fullness %))))

(defn play [tamagotchi]
  (swap! tamagotchi
         (comp #(increase :happiness %)
               #(increase :tiredness %))))

(defn put-to-bed [tamagotchi]
  (swap! tamagotchi #(decrease :tiredness %)))

(defn make-poop [tamagotchi]
  (swap! tamagotchi #( decrease :fullness %)))

(defn create [& {:keys [hungriness fullness happiness tiredness]
                       :or   {hungriness 3 fullness 3 happiness 3 tiredness 3}}]
  (atom {:hungriness hungriness :fullness fullness :happiness happiness :tiredness tiredness}))
