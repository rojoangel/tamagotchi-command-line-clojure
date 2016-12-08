(ns tamagotchi-kata-clojure.command-line
  (:gen-class)
  (:require [clojure.string :as str])
  (:use [tamagotchi-kata-clojure.core :as tamagotchi]))

(defn prompt-menu []
  (println "create [name] : creates a new tamagotchi"))

(defn show-status [tamagotchi]
  (println "name:" (:name @tamagotchi)
           " | hungriness:" (:hungriness @tamagotchi)
           " | fullness:" (:fullness @tamagotchi)
           " | happiness:" (:happiness @tamagotchi)
           " | tiredness:" (:tiredness @tamagotchi)
           ))

(defn dispatch [command arguments]
  (case command
    :create
    (let [name (first arguments)
          tamagotchi (if (nil? name)
                       (tamagotchi/create)
                       (tamagotchi/create :name name))]
      (show-status tamagotchi))
    (do
      (println "Valid commands are: create [name]"))))

(defn -main [& args]
  (prompt-menu)
  (let [[command-str & arguments] (str/split (read-line) #" ")
        command (keyword command-str)]
    (dispatch command arguments)))