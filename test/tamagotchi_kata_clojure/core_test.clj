(ns tamagotchi-kata-clojure.core-test
  (:use midje.sweet)
  (:use [tamagotchi-kata-clojure.core :as tamagotchi]))

(facts
  "about tamagotchi"
  (facts
    "about feeding"
    (fact
      "When I feed it, it's hungriness is decreased"
      (let [pepito (tamagotchi/create)]
        (tamagotchi/eat pepito) => {:hungriness 5}
         @pepito => {:hungriness 5}))))
