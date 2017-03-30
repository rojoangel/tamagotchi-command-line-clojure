(ns tamagotchi-command-line.state-test
  (:use midje.sweet)
  (:use [tamagotchi-command-line.state :as tamagotchi])
  (:require [tamagotchi.core :as config]))

(facts
  "about tamagotchi"
  (facts
    "about feeding"
    (fact
      "When I feed it, it's hungriness is decreased"
      (do
        (tamagotchi/create :hungriness 6)
        (:hungriness (tamagotchi/feed))) => 5)
    (fact
      "When I feed it, it's fullness is increased"
      (do
        (tamagotchi/create :fullness 6)
        (:fullness (tamagotchi/feed))) => 7))
  (facts
    "about playing"
    (fact
      "When I play with it, it's happiness is increased"
      (do
        (tamagotchi/create :happiness 6)
        (:happiness (tamagotchi/play))) => 7)
    (fact
      "When I play with it, it's tiredness is increased"
      (do
        (tamagotchi/create :tiredness 6)
        (:tiredness (tamagotchi/play))) => 7))
  (facts
    "about putting to bed"
    (fact
      "When i put it to bed, it's tiredness is decreased"
      (do
        (tamagotchi/create :tiredness 6)
        (:tiredness (tamagotchi/put-to-bed))) => 5))
  (facts
    "about making poop"
    (fact
      "When i make it poop, it's fullness is decreased"
      (do
        (tamagotchi/create :fullness 6)
        (:fullness (tamagotchi/make-poop))) => 5))
  (facts
    "about changing needs over time"
    (fact
      "When time passes, its tiredness is increased"
      (do
        (tamagotchi/create :tiredness 6)
        (:tiredness (tamagotchi/tic))) => 7)
    (fact
      "When time passes, its hungriness is increased"
      (do
        (tamagotchi/create :hungriness 6)
        (:hungriness (tamagotchi/tic))) => 7)
    (fact
      "When time passes, its happiness is decreased"
      (do
        (tamagotchi/create :happiness 6)
        (:happiness (tamagotchi/tic))) => 5))
  (facts
    "about dying when max/min levels are reached"
    (fact
      "When fullness reaches maximum tamagotchi dies"
      (do
        (tamagotchi/create :fullness 99)
        (tamagotchi/feed)
        (is-dead?) => true))
    (fact
      "When hungriness reaches maximum tamagotchi dies"
      (do
        (tamagotchi/create :hungriness 99)
        (tamagotchi/tic)
        (is-dead?) => true))
    (fact
      "When tiredness reaches maximum tamagotchi dies"
      (do
        (tamagotchi/create :tiredness 99)
        (tamagotchi/play)
        (is-dead?) => true))
    (fact
      "When happiness reaches minimum tamagotchi dies"
      (do
        (tamagotchi/create :happiness 1)
        (tamagotchi/tic)
        (is-dead?) => true)))
  (facts
    "about not going beyond min & max attribute values"
    (fact
      "When fullness reaches minimum it is not further decreased"
      (do
        (tamagotchi/create :fullness config/min-attribute-value)
        (:fullness (tamagotchi/make-poop))) => config/min-attribute-value)
    (fact
      "When hungriness reaches minimum it is not further decreased"
      (do
        (tamagotchi/create :hungriness config/min-attribute-value)
        (:hungriness (tamagotchi/feed))) => config/min-attribute-value)
    (fact
      "When tiredness reaches minimum it is not further decreased"
      (do
        (tamagotchi/create :tiredness config/min-attribute-value)
        (:tiredness (tamagotchi/put-to-bed))) => config/min-attribute-value)
    (fact
      "When happiness reaches maximum it is not further increased"
      (do
        (tamagotchi/create :happiness config/max-attribute-value)
        (:happiness (tamagotchi/play))) => config/max-attribute-value)))
