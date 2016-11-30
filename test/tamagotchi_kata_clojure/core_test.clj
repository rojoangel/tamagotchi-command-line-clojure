(ns tamagotchi-kata-clojure.core-test
  (:use midje.sweet)
  (:use [tamagotchi-kata-clojure.core :as tamagotchi]))

(facts
  "about tamagotchi"
  (facts
    "about feeding"
    (fact
      "When I feed it, it's hungriness is decreased"
      (let [tamagotchi (tamagotchi/create :hungriness 6)]
        (:hungriness (tamagotchi/feed tamagotchi)) => 5
        (:hungriness @tamagotchi) => 5))
    (fact
      "When I feed it, it's fullness is increased"
      (let [tamagotchi (tamagotchi/create :fullness 6)]
        (:fullness (tamagotchi/feed tamagotchi)) => 7
        (:fullness @tamagotchi) => 7))))
