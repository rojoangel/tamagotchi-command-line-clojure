(ns tamagotchi-kata-clojure.tic
  (:require [overtone.at-at :as at]))

(def pool (at/mk-pool))

(def delay 10000)

(defn configure [delay fn]
  (at/every delay
            fn
            pool
            :initial-delay delay))
