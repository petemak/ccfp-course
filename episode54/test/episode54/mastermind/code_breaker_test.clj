(ns episode54.mastermind.code-breaker-test
  (:require [midje.sweet :refer :all]
            [episode54.mastermind.code-maker :as cm]
            [episode54.mastermind.code-breaker :refer :all]))


(facts "code breaker"
       (fact "Guess to 4-digit base 6 number"
             (guess-to-number [0 0 0 0]) => 0
             (guess-to-number [0 0 0 1]) => 1
             (guess-to-number [0 0 1 0]) => 6
             (guess-to-number [0 0 1 1]) => 7
             (guess-to-number [0 1 1 1]) => 43
             (guess-to-number [1 1 1 1]) => 259
             (guess-to-number [5 5 5 5]) => (dec (* 6 6 6 6)))

       (fact "4-bit base 6 number to code"
             (number-to-guess 0)   => [0 0 0 0]
             (number-to-guess 1)   => [0 0 0 1]
             (number-to-guess 6)   => [0 0 1 0]
             (number-to-guess 7)   => [0 0 1 1]
             (number-to-guess 43)  => [0 1 1 1]
             (number-to-guess 259) => [1 1 1 1]
             (number-to-guess (dec (* 6 6 6 6))) => [5 5 5 5])

       (fact "Increment guess"
             (increment-guess [0 0 0 0]) => [0 0 0 1]
             (increment-guess [0 0 0 5]) => [0 0 1 0]
             (increment-guess [0 0 5 5]) => [0 1 0 0]
             (increment-guess [0 5 5 5]) => [1 0 0 0]
             (increment-guess [5 5 5 5]) => [0 0 0 0])

       (fact "Initial guess"
             (break-code []) => [0 0 0 0])


       (future-fact "Walk through the solution of code [1 2 3 4]"
                    (break-code [[[0 0 0 0]
                                  (cm/score [1 2 3 4] [0 0 0 0])]]) => [0 0 0 1]))



