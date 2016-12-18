(ns tamagotchi-kata-clojure.core-test
  (:use midje.sweet)
  (:use [tamagotchi-kata-clojure.core :as tamagotchi]))

(facts
  "about tamagotchi"
  (facts
    "about feeding"
    (fact
      "When I feed it, it's hungriness is decreased"
      (do
        (tamagotchi/create :hungriness 6)
        (:hungriness (tamagotchi/feed)) => 5))
    (fact
      "When I feed it, it's fullness is increased"
      (do
        (tamagotchi/create :fullness 6)
        (:fullness (tamagotchi/feed)) => 7)))
  (facts
    "about playing"
    (fact
      "When I play with it, it's happiness is increased"
      (do
        (tamagotchi/create :happiness 6)
        (:happiness (tamagotchi/play)) => 7))
    (fact
      "When I play with it, it's tiredness is increased"
      (let [tamagotchi (tamagotchi/create :tiredness 6)]
        (:tiredness (tamagotchi/play)) => 7)))
  (facts
    "about putting to bed"
    (fact
      "When i put it to bed, it's tiredness is decreased"
      (do
        (tamagotchi/create :tiredness 6)
        (:tiredness (tamagotchi/put-to-bed)) => 5)))
  (facts
    "about making poop"
    (fact
      "When i make it poop, it's fullness is decreased"
      (do
        (tamagotchi/create :fullness 6)
        (:fullness (tamagotchi/make-poop)) => 5)))
  (facts
    "about changing needs over time"
    (fact
      "When time passes, its tiredness is increased"
      (do
        (tamagotchi/create :tiredness 6)
        (:tiredness (tamagotchi/tic)) => 7))
    (fact
      "When time passes, its hungriness is increased"
      (do
        (tamagotchi/create :hungriness 6)
        (:hungriness (tamagotchi/tic)) => 7))
    (fact
      "When time passes, its happiness is decreased"
      (do
        (tamagotchi/create :happiness 6)
        (:happiness (tamagotchi/tic)) => 5))))
