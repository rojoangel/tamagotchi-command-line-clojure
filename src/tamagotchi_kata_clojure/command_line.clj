(ns tamagotchi-kata-clojure.command-line
  (:gen-class)
  (:require [clojure.string :as str]
            [clansi])
  (:use [tamagotchi-kata-clojure.core :as tamagotchi]))

(def commands
  {"show" "shows your tamagotchi status"
   "feed" "feeds your tamagotchi"
   "play" "play with your tamagotchi"
   "bed"  "puts your tamagotchi to bed"
   "poo"  "makes your tamagotchi poo"
   "quit" "quits - and your tamagotchi dies"})

(defn describe-command [[command description]]
  (println (clansi/style command :green) description))

(defn prompt-menu []
  (doall (map describe-command commands)))

(defn- show-status [tamagotchi]
  (println "name:" (:name @tamagotchi)
           " | hungriness:" (:hungriness @tamagotchi)
           " | fullness:" (:fullness @tamagotchi)
           " | happiness:" (:happiness @tamagotchi)
           " | tiredness:" (:tiredness @tamagotchi)
           ))

(declare ui-loop)

(defn- dispatch [command]
  (case command

    :show
    (show-status tamagotchi)

    :feed
    (do (tamagotchi/feed)
        (dispatch :show))

    :play
    (do (tamagotchi/play)
        (dispatch :show))

    :bed
    (do (tamagotchi/put-to-bed)
        (dispatch :show))

    :poo
    (do (tamagotchi/make-poop)
        (dispatch :show))

    :quit
    (System/exit 0)

    ;; otherwise
    (println "Valid commands are: show | feed | play | bed | poo | quit"))

  (ui-loop))


(defn- ui-loop []
  (let [[command-str & _] (str/split (read-line) #" ")
        command (keyword command-str)]
    (dispatch command)))

(defn- init-tamagotchi []
  (do
    (println "Name your tamagotchi [Miyagi]")
    (let [[name & _] (str/split (read-line) #" ")]
      (if (str/blank? name)
        (tamagotchi/create)
        (tamagotchi/create :name name))
      (dispatch :show))))

(defn -main [& args]
  (prompt-menu)
  (init-tamagotchi)
  (ui-loop))