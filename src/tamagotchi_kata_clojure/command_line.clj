(ns tamagotchi-kata-clojure.command-line
  (:gen-class))

(defn prompt-menu []
  (println "create <name> : creates a new tamagotchi")
  (println "show : shows tamagotchi")
  (println "feed : feeds tamagotchi")
  (println "quit : exit"))

(defn -main [& args]
  (prompt-menu))