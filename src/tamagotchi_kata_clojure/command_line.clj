(ns tamagotchi-kata-clojure.command-line
  (:gen-class)
  (:require [clojure.string :as str])
  (:use [tamagotchi-kata-clojure.core :as tamagotchi]))

(defn prompt-menu []
  (println "show          : shows your tamagotchi status")
  (println "feed          : feeds your tamagotchi")
  (println "play          : play with your tamagotchi")
  (println "bed           : puts your tamagotchi to bed")
  (println "poo           : makes your tamagotchi poo")
  (println "quit          : quits - and your tamagotchi dies"))

(defn- show-status [tamagotchi]
  (println "name:" (:name @tamagotchi)
           " | hungriness:" (:hungriness @tamagotchi)
           " | fullness:" (:fullness @tamagotchi)
           " | happiness:" (:happiness @tamagotchi)
           " | tiredness:" (:tiredness @tamagotchi)
           ))

(declare ui-loop)

(defn- dispatch [tamagotchi command]
  (case command

    :show
    (show-status tamagotchi)

    :feed
    (do (tamagotchi/feed tamagotchi)
        (dispatch tamagotchi :show))

    :play
    (do (tamagotchi/play tamagotchi)
        (dispatch tamagotchi :show))

    :bed
    (do (tamagotchi/put-to-bed tamagotchi)
        (dispatch tamagotchi :show))

    :poo
    (do (tamagotchi/make-poop tamagotchi)
        (dispatch tamagotchi :show))

    :quit
    (System/exit 0)

    ;; otherwise
    (println "Valid commands are: show | feed | play | bed | poo | quit"))

  (ui-loop tamagotchi))


(defn- ui-loop [tamagotchi]
  (let [[command-str & _] (str/split (read-line) #" ")
        command (keyword command-str)]
    (dispatch tamagotchi command)))

(defn- init-tamagotchi []
  (do
    (println "Name your tamagotchi [Miyagi]")
    (let [[name & _] (str/split (read-line) #" ")]
      (dispatch
        (if (str/blank? name)
          (tamagotchi/create)
          (tamagotchi/create :name name)) :show))))

(defn -main [& args]
  (prompt-menu)
  (ui-loop (init-tamagotchi)))