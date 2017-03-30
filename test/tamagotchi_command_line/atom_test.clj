(ns tamagotchi-command-line.atom-test
  (:use midje.sweet)
  (:use [tamagotchi-command-line.atom :as atom])
  (:require [tamagotchi.core :as tamagotchi]))

(facts
  "about tamagotchi"
  (facts
    "about feeding"
    (fact
      "When I feed it, it's hungriness is decreased"
      (do
        (atom/create :hungriness 6)
        (:hungriness (atom/feed))) => 5)
    (fact
      "When I feed it, it's fullness is increased"
      (do
        (atom/create :fullness 6)
        (:fullness (atom/feed))) => 7))
  (facts
    "about playing"
    (fact
      "When I play with it, it's happiness is increased"
      (do
        (atom/create :happiness 6)
        (:happiness (atom/play))) => 7)
    (fact
      "When I play with it, it's tiredness is increased"
      (do
        (atom/create :tiredness 6)
        (:tiredness (atom/play))) => 7))
  (facts
    "about putting to bed"
    (fact
      "When i put it to bed, it's tiredness is decreased"
      (do
        (atom/create :tiredness 6)
        (:tiredness (atom/put-to-bed))) => 5))
  (facts
    "about making poop"
    (fact
      "When i make it poop, it's fullness is decreased"
      (do
        (atom/create :fullness 6)
        (:fullness (atom/make-poop))) => 5))
  (facts
    "about changing needs over time"
    (fact
      "When time passes, its tiredness is increased"
      (do
        (atom/create :tiredness 6)
        (:tiredness (atom/tic))) => 7)
    (fact
      "When time passes, its hungriness is increased"
      (do
        (atom/create :hungriness 6)
        (:hungriness (atom/tic))) => 7)
    (fact
      "When time passes, its happiness is decreased"
      (do
        (atom/create :happiness 6)
        (:happiness (atom/tic))) => 5))
  (facts
    "about dying when max/min levels are reached"
    (fact
      "When fullness reaches maximum tamagotchi dies"
      (do
        (atom/create :fullness 99)
        (atom/feed)
        (is-dead?) => true))
    (fact
      "When hungriness reaches maximum tamagotchi dies"
      (do
        (atom/create :hungriness 99)
        (atom/tic)
        (is-dead?) => true))
    (fact
      "When tiredness reaches maximum tamagotchi dies"
      (do
        (atom/create :tiredness 99)
        (atom/play)
        (is-dead?) => true))
    (fact
      "When happiness reaches minimum tamagotchi dies"
      (do
        (atom/create :happiness 1)
        (atom/tic)
        (is-dead?) => true)))
  (facts
    "about not going beyond min & max attribute values"
    (fact
      "When fullness reaches minimum it is not further decreased"
      (do
        (atom/create :fullness tamagotchi/min-attribute-value)
        (:fullness (atom/make-poop))) => tamagotchi/min-attribute-value)
    (fact
      "When hungriness reaches minimum it is not further decreased"
      (do
        (atom/create :hungriness tamagotchi/min-attribute-value)
        (:hungriness (atom/feed))) => tamagotchi/min-attribute-value)
    (fact
      "When tiredness reaches minimum it is not further decreased"
      (do
        (atom/create :tiredness tamagotchi/min-attribute-value)
        (:tiredness (atom/put-to-bed))) => tamagotchi/min-attribute-value)
    (fact
      "When happiness reaches maximum it is not further increased"
      (do
        (atom/create :happiness tamagotchi/max-attribute-value)
        (:happiness (atom/play))) => tamagotchi/max-attribute-value)))
