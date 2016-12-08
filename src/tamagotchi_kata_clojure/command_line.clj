(ns tamagotchi-kata-clojure.command-line
  (:gen-class)
  (:require [clojure.string :as str])
  (:use [tamagotchi-kata-clojure.core :as tamagotchi]))

(defn prompt-menu []
  (println "create [name] : creates a new tamagotchi")
  (println "show          : shows your tamagotchi status")
  (println "feed          : feeds your tamagotchi")
  (println "play          : play with your tamagotchi")
  (println "bed           : puts your tamagotchi to bed")
  (println "quit          : quits - and your tamagotchi dies"))

(defn show-status [tamagotchi]
  (println "name:" (:name @tamagotchi)
           " | hungriness:" (:hungriness @tamagotchi)
           " | fullness:" (:fullness @tamagotchi)
           " | happiness:" (:happiness @tamagotchi)
           " | tiredness:" (:tiredness @tamagotchi)
           ))

(declare ui-loop)

(defn dispatch
  ([tamagotchi command]
   (dispatch tamagotchi command nil))
  ([tamagotchi command arguments]
    (case command
    :create
    (let [name (first arguments)
          tamagotchi (if (nil? name)
                       (tamagotchi/create)
                       (tamagotchi/create :name name))]
      (dispatch tamagotchi :show))

    :show
    (if (nil? tamagotchi)
      (println "You don't have a tamagotchi. Please create one with 'create'")
      (show-status tamagotchi))

    :feed
    (if (nil? tamagotchi)
      (println "You don't have a tamagotchi. Please create one with 'create'")
      (do (tamagotchi/feed tamagotchi)
          (dispatch tamagotchi :show)))

    :play
    (if (nil? tamagotchi)
      (println "You don't have a tamagotchi. Please create one with 'create'")
      (do (tamagotchi/play tamagotchi)
          (dispatch tamagotchi :show)))

    :bed
    (if (nil? tamagotchi)
      (println "You don't have a tamagotchi. Please create one with 'create'")
      (do (tamagotchi/put-to-bed tamagotchi)
          (dispatch tamagotchi :show)))

    :quit
    (System/exit 0)

    (do
      (println "Valid commands are: create [name] | show | feed | play | bed | quit")))

  (ui-loop tamagotchi)))

(defn ui-loop [tamagotchi]
  (let [[command-str & arguments] (str/split (read-line) #" ")
        command (keyword command-str)]
    (dispatch tamagotchi command arguments)))

(defn -main [& args]
  (prompt-menu)
  (ui-loop nil))