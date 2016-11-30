(ns tamagotchi-kata-clojure.core)

(defn feed [tamagotchi]
  (swap! tamagotchi
         (comp #(update-in % [:hungriness] dec)
               #(update-in % [:fullness] inc))))

(defn play [tamagotchi]
  (swap! tamagotchi
         #(update-in % [:happiness] inc)))

(defn create [& {:keys [hungriness fullness happiness]
                 :or   {hungriness 3 fullness 3 happiness 3}}]
  (atom {:hungriness hungriness :fullness fullness :happiness happiness}))
