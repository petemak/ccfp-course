(ns episode54.mastermind.auto-play
  (:require [episode54.mastermind.code-maker :as cm]
            [episode54.mastermind.code-breaker :as cb]))


(defn random-code
  "Generate a random code [a b c d]"
  []
  (cb/number-to-guess (rand-int (dec (* 6 6 6 6)))))


(defn auto-play
  "Play game by generating a random code
  "
  [strategy]
  (let [code (random-code)]
    (loop [n 1
           last-guess nil
           past-scores []]
      (let [guess (strategy last-guess past-scores)
            score (cm/score code guess)]
        (if (= score [4 0])
          n
          (recur (inc n) guess (conj past-scores [guess score])))))))


(defn square
  "Square a number"
  [x]
  (* x x))


(defn mean
  "Avarage value of a set of numbers"
  [col]
  (/ (reduce + col) (count col)))

(defn sigma
  "σ(n) is the sum of the factors of n."
  [col]
  (let [mn (mean col)]
    (Math/sqrt (/ (reduce #(+ %1 (square (- %2 mn))) 0 col)
                  (dec (count col))))))


(defn analyse-strategy
  "Run the straegy n times"
  [strategy n]
  (let [scores (sort (repeatedly n #(auto-play strategy)))]
    {:mean (double (mean scores))
     :sigma (sigma scores)
     :min (first scores)
     :max (last scores)
     :median (nth scores (int (/ (count scores) 2)))
     :hist (map count (partition-by identity scores))}))


(defn analyse-strategies
  [n]
  {:seq (analyse-strategy cb/break-code-seq n)
   :3x2 (analyse-strategy cb/break-code-3x2 n)
   :double-rainbow (analyse-strategy cb/break-code-double-rainbow n)})
