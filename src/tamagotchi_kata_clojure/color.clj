(ns tamagotchi-kata-clojure.color
  (:require [tamagotchi-kata-clojure.core :as tamagotchi]))

(defmulti value->color :type)

(defmethod value->color :decreasing [{type :type value :val}]
  (if (< value (* tamagotchi/max-attribute-value 0.1))
    :red
    (if (< value (* tamagotchi/max-attribute-value 0.25))
      :yellow
      :green)))

(defmethod value->color :increasing [{type :type value :val}]
  (if (> value (* tamagotchi/max-attribute-value 0.9))
    :red
    (if (> value (* tamagotchi/max-attribute-value 0.75))
      :yellow
      :green)))