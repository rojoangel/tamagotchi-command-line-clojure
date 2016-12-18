(ns tamagotchi-kata-clojure.core)

(def tamagotchi (atom {}))

(defn- decrease [keyword tamagotchi]
  (update tamagotchi keyword dec))

(defn- increase [keyword tamagotchi]
  (update tamagotchi keyword inc))

(defn feed []
  (swap! tamagotchi
         (comp #(decrease :hungriness %)
               #(increase :fullness %))))

(defn play []
  (swap! tamagotchi
         (comp #(increase :happiness %)
               #(increase :tiredness %))))

(defn put-to-bed []
  (swap! tamagotchi #(decrease :tiredness %)))

(defn make-poop []
  (swap! tamagotchi #(decrease :fullness %)))

(defn tic []
  (swap! tamagotchi
         (comp #(increase :tiredness %)
               #(increase :hungriness %)
               #(decrease :happiness %))))

(defn create
  [& {:keys [name hungriness fullness happiness tiredness]
      :or   {name       "Miyagi"
             hungriness 3
             fullness   3
             happiness  3
             tiredness  3}}]
  (swap! tamagotchi
         assoc
         :name name
         :hungriness hungriness
         :fullness fullness
         :happiness happiness
         :tiredness tiredness))
