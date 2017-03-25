(ns tamagotchi-kata-clojure.command-line
  (:gen-class)
  (:require [clojure.string :as str]
            [clansi])
  (:use [tamagotchi-kata-clojure.core :as tamagotchi]
        [tamagotchi-kata-clojure.tic :as tic]))

(def commands
  [{:name "show" :desc "shows your tamagotchi status"}
   {:name "feed" :desc "feeds your tamagotchi"}
   {:name "play" :desc "play with your tamagotchi"}
   {:name "bed" :desc "puts your tamagotchi to bed"}
   {:name "poo" :desc "makes your tamagotchi poo"}
   {:name "quit" :desc "quits - and your tamagotchi dies"}])

(def attribute-defs
  {:hungriness {:label "hungriness" :type :increasing}
   :fullness   {:label "fullness" :type :increasing}
   :happiness  {:label "happiness" :type :decreasing}
   :tiredness  {:label "tiredness" :type :increasing}})

(defn format-command-name [name]
  (clansi/style name :green))

(defmulti format-attribute-value :type)

(defmethod format-attribute-value :decreasing [{type :type value :val}]
  (if (< value (* tamagotchi/max-attribute-value 0.1))
    (clansi/style value :red)
    (if (< value (* tamagotchi/max-attribute-value 0.25))
      (clansi/style value :yellow)
      (clansi/style value :green))))

(defmethod format-attribute-value :increasing [{type :type value :val}]
  (if (> value (* tamagotchi/max-attribute-value 0.9))
    (clansi/style value :red)
    (if (> value (* tamagotchi/max-attribute-value 0.75))
      (clansi/style value :yellow)
      (clansi/style value :green))))

(defn format-attribute [attribute-def]
  (let [attribute-keyword (key attribute-def)
        attribute-label (:label (val attribute-def))
        attribute-type (:type (val attribute-def))]
    (str attribute-label ": " (format-attribute-value {:type attribute-type :val (get @tamagotchi attribute-keyword)}))))

(defn describe-command [{:keys [name desc]}]
  (println (format-command-name (format "%-5s" name)) desc))

(defn show-valid-commands []
  (println "Valid commands are: " (str/join " | " (map #(format-command-name (:name %)) commands))))

(defn prompt-menu []
  (doall (map describe-command commands)))

(defn- show-status [tamagotchi]
  (println "name:" (:name @tamagotchi) "*" (str/join " | " (map format-attribute attribute-defs))))

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
    (show-valid-commands))

  (ui-loop))

(defn- ui-loop []
  (let [[command-str & _] (str/split (read-line) #" ")
        command (keyword command-str)]
    (dispatch command)))

(defn- ui-tic []
  (do
    (tamagotchi/tic)
    (show-status tamagotchi)))

(defn- init-tamagotchi []
  (do
    (println "Name your tamagotchi [Miyagi]")
    (let [[name & _] (str/split (read-line) #" ")]
      (if (str/blank? name)
        (tamagotchi/create)
        (tamagotchi/create :name name))
      (tic/configure tic/delay ui-tic)
      (add-watch tamagotchi
                 :quit-when
                 (fn [key atom old-state new-state]
                   (when (empty? new-state)
                     (dispatch :quit))))
      (dispatch :show))))

(defn -main [& args]
  (prompt-menu)
  (init-tamagotchi)
  (ui-loop))