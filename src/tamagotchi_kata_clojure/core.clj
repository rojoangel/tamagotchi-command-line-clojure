(ns tamagotchi-kata-clojure.core)

(def default-initial-attribute-value 50)
(def min-attribute-value 0)
(def max-attribute-value 100)

(defn- inc-up-to-max [n]
  (if (< n max-attribute-value)
    (inc n)
    n))

(defn- dec-down-to-min [n]
  (if (> n min-attribute-value)
    (dec n)
    n))

(defn- decrease [keyword tamagotchi]
  (update tamagotchi keyword dec-down-to-min))

(defn- increase [keyword tamagotchi]
  (update tamagotchi keyword inc-up-to-max))

(defn feed [tamagotchi]
  (->> tamagotchi
       (increase :fullness)
       (decrease :hungriness)))

(defn play [tamagotchi]
  (->> tamagotchi
       (increase :happiness)
       (increase :tiredness)))

(defn put-to-bed [tamagotchi]
  (decrease :tiredness tamagotchi))

(defn make-poop [tamagotchi]
  (decrease :fullness tamagotchi))

(defn tic [tamagotchi]
  (->> tamagotchi
       (increase :tiredness)
       (increase :hungriness)
       (decrease :happiness)))

(defn breed [name hungriness fullness happiness tiredness]
  (assoc
    {}
    :name name
    :hungriness hungriness
    :fullness fullness
    :happiness happiness
    :tiredness tiredness))

(defn create
  [& {:keys [name hungriness fullness happiness tiredness]
      :or   {name       "Miyagi"
             hungriness default-initial-attribute-value
             fullness   default-initial-attribute-value
             happiness  default-initial-attribute-value
             tiredness  default-initial-attribute-value}}]
  (breed name hungriness fullness happiness tiredness))
