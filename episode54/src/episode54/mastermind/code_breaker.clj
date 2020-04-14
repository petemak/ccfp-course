(ns episode54.mastermind.code-breaker)


(defn break-code
  [passt-guesses]
  [0 0 0 0])


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
  (-> guess
      (guess-to-number)
      (inc)
      (number-to-guess)))
