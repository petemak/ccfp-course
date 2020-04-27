(ns episode54.mastermind.code-breaker
  (:require [episode54.mastermind.code-maker :as cm]))



(defn guess-to-number
  "Convert guess to 4-digit base 6 number"
  [guess]
  (reduce #(+ (* 6 %1) %2) guess))


(defn number-to-guess
  "Convert 4-bit base 6 number to code
   Simple implementation is explicit"
  [n]
  [(rem (quot n 216) 6)  
   (rem (quot n 36) 6) 
   (rem (quot n 6) 6) 
   (rem n 6)])

(defn number-to-guess2
  "Convert 4-bit base 6 number to code
  Clever implementation but not easy to understand"
  [n]
  (loop [guess (list)
         numbr n]
    (if (= 4 (count guess))
      guess
      (recur (cons (rem numbr 6) guess) (quot numbr 6) ))))


(defn increment-guess
  "Increment guess"
  [guess]
  (if (= guess [5 5 5 5])
    :overflow
    (-> guess
        (guess-to-number)
        (inc)
        (number-to-guess))))


(defn guess-consistent-with-past-scores
  ""
  [guess past-scores]
  (every? identity
          (for [past-guess past-scores]
            (= (cm/score guess (first past-guess))
               (second past-guess)))))

(defn next-guess
  "Computes next guess by taking the starting-guess
  increasing it and checking if the score is equal
  to that of the past guess"
  [starting-guess past-scores]
  (loop [guess starting-guess]
    (if (= guess :overflow)
      :error      
      (if (guess-consistent-with-past-scores guess past-scores)
        guess
        (recur (increment-guess guess))))) )

(defn break-code
  "Break code by comparing a guess with past guesses"
  [starting-guess past-guesses]
  (if (nil? starting-guess) 
    [0 0 0 0]
    (next-guess starting-guess past-guesses)))


(defn break-code-seq
  "Sequential strategy"
  [starting-guess passt-scores]
  (if (nil? starting-guess)
    [0 0 0 0]
    (next-guess starting-guess passt-scores)))


(defn break-code-3x2
  "The 3 x 2 strategy"
  [starting-guess passt-scores]
  (case (count passt-scores)
    0 [0 0 1 1]
    1 [2 2 3 3]
    2 [4 4 5 5]
    3 (next-guess [0 0 0 0] passt-scores)
    (next-guess starting-guess passt-scores)))

(defn break-code-double-rainbow
  "All the way strategy"
  [starting-guess passt-scores]
  (case (count passt-scores)
    0 [0 1 2 3]
    1 [2 3 4 5]
    2 [4 5 0 1]
    3 (next-guess [0 0 0 0] passt-scores)
    (next-guess starting-guess passt-scores)))
