(ns tamagotchi-command-line.color
  (:require [tamagotchi-command-line.core :as tamagotchi]))

(defmulti value->color :type)

(defmethod value->color :decreasing [{type :type value :val} kw->color-fn]
  (if (< value (* tamagotchi/max-attribute-value 0.1))
    (kw->color-fn :red)
    (if (< value (* tamagotchi/max-attribute-value 0.25))
      (kw->color-fn :yellow)
      (kw->color-fn :green))))

(defmethod value->color :increasing [{type :type value :val} kw->color-fn]
  (if (> value (* tamagotchi/max-attribute-value 0.9))
    (kw->color-fn :red)
    (if (> value (* tamagotchi/max-attribute-value 0.75))
      (kw->color-fn :yellow)
      (kw->color-fn :green))))