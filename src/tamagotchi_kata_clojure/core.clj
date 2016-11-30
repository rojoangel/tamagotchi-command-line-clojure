(ns tamagotchi-kata-clojure.core)

(defn feed [tamagotchi]
  (swap! tamagotchi
         (comp #(update-in % [:hungriness] dec)
               #(update-in % [:fullness] inc))))

(defn create [& {:keys [hungriness fullness]
                 :or   {hungriness 3 fullness 3}}]
  (atom {:hungriness hungriness :fullness fullness}))
