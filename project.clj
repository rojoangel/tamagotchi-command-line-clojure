(defproject tamagotchi-command-line "0.0.1-SNAPSHOT"
  :description "tamagotchi game using the command line"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [tamagotchi "0.0.1"]
                 [clansi "1.0.0"]
                 [overtone/at-at "1.2.0"]]
  :profiles {:dev {:dependencies [[midje "1.8.3"]]}}
  :main "tamagotchi-command-line.command-line")
  
