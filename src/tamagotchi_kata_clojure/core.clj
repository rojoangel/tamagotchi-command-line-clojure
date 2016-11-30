(ns tamagotchi-kata-clojure.core)

(defn feed [tamagotchi]
  (swap! tamagotchi
         (comp #(update-in % [:hungriness] dec)
               #(update-in % [:fullness] inc))))

(defn play [tamagotchi]
  (swap! tamagotchi
         (comp #(update-in % [:happiness] inc)
               #(update-in % [:tiredness] inc))))

(defn put-to-bed [tamagotchi]
  (swap! tamagotchi #(update-in % [:tiredness] dec)))

(defn create [& {:keys [hungriness fullness happiness tiredness]
                       :or   {hungriness 3 fullness 3 happiness 3 tiredness 3}}]
  (atom {:hungriness hungriness :fullness fullness :happiness happiness :tiredness tiredness}))
