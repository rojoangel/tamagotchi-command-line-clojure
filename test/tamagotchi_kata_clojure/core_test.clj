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
        (:hungriness (tamagotchi/feed tamagotchi))) => 5)
    (fact
      "When I feed it, it's fullness is increased"
      (let [tamagotchi (tamagotchi/create :fullness 6)]
        (:fullness (tamagotchi/feed tamagotchi))) => 7))
  (facts
    "about playing"
    (fact
      "When I play with it, it's happiness is increased"
      (let [tamagotchi (tamagotchi/create :happiness 6)]
        (:happiness (tamagotchi/play tamagotchi))) => 7)
    (fact
      "When I play with it, it's tiredness is increased"
      (let [tamagotchi (tamagotchi/create :tiredness 6)]
        (:tiredness (tamagotchi/play tamagotchi)) => 7)))
  (facts
    "about putting to bed"
    (fact
      "When i put it to bed, it's tiredness is decreased"
      (let [tamagotchi (tamagotchi/create :tiredness 6)]
        (:tiredness (tamagotchi/put-to-bed tamagotchi))) => 5))
  (facts
    "about making poop"
    (fact
      "When i make it poop, it's fullness is decreased"
      (let [tamagotchi (tamagotchi/create :fullness 6)]
        (:fullness (tamagotchi/make-poop tamagotchi))) => 5))
  (facts
    "about changing needs over time"
    (fact
      "When time passes, its tiredness is increased"
      (let [tamagotchi (tamagotchi/create :tiredness 6)]
        (:tiredness (tamagotchi/tic tamagotchi))) => 7)
    (fact
      "When time passes, its hungriness is increased"
      (let [tamagotchi (tamagotchi/create :hungriness 6)]
        (:hungriness (tamagotchi/tic tamagotchi))) => 7)
    (fact
      "When time passes, its happiness is decreased"
      (let [tamagotchi (tamagotchi/create :happiness 6)]
        (:happiness (tamagotchi/tic tamagotchi))) => 5))
  (facts
    "about not going beyond min & max attribute values"
    (fact
      "When fullness reaches minimum it is not further decreased"
      (let [tamagotchi (tamagotchi/create :fullness tamagotchi/min-attribute-value)]
        (:fullness (tamagotchi/make-poop tamagotchi))) => tamagotchi/min-attribute-value)
    (fact
      "When hungriness reaches minimum it is not further decreased"
      (let [tamagotchi (tamagotchi/create :hungriness tamagotchi/min-attribute-value)]
        (:hungriness (tamagotchi/feed tamagotchi))) => tamagotchi/min-attribute-value)
    (fact
      "When tiredness reaches minimum it is not further decreased"
      (let [tamagotchi (tamagotchi/create :tiredness tamagotchi/min-attribute-value)]
        (:tiredness (tamagotchi/put-to-bed tamagotchi))) => tamagotchi/min-attribute-value)
    (fact
      "When happiness reaches maximum it is not further increased"
      (let [tamagotchi (tamagotchi/create :happiness tamagotchi/max-attribute-value)]
        (:happiness (tamagotchi/play tamagotchi))) => tamagotchi/max-attribute-value)))
