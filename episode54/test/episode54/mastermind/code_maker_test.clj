(ns episode54.mastermind.code-maker-test
  (:require [midje.sweet :refer :all]
            [episode54.mastermind.code-maker :refer :all]))


(facts "Scoring position matches"
       (fact "score guess with no matche"
             (score [0 0 0 0] [1 1 1 1]) => [0 0])

       (fact "score geuss with one :pos match"
             (score [0 0 0 0] [0 1 1 1]) => [1 0])

       (fact "score guess with two :pos macthes"
             (score [0 0 0 0] [0 0 1 1]) => [2 0]
             (score [0 1 0 0] [0 2 3 0]) => [2 0])

       (fact "score guess with three mathes"
             (score [1 1 1 1] [1 1 5 1]) => [3 0]
             (score [0 0 0 1] [0 3 0 1]) => [3 0]
             (score [0 0 0 1] [0 3 0 1]) => [3 0]))


(facts "Scoring value matches"
       (fact "No position but one value match"
             (score [0 1 2 3] [5 4 6 0]) => [0 1])
       
       (fact "0 position and 2 value matches"
             (score [6 0 2 3] [1 2 5 0]) => [0 2])

       (fact "0 position and 3 value matches"
             (score [5 0 2 1] [1 2 4 0]) => [0 3])
       
       (fact "1 position and 1 value matches"
             (score [1 3 2 3] [1 2 5 0]) => [1 1])

       (fact "1 position and 3 value matches"
             (score [1 0 2 3] [1 2 3 0]) => [1 3])

       (fact "1 position and 2 duplicated value matches
              If there is are duplicates in the guess
              they should not be counted unless there 
              is the same number of duplicates in the code!"
             (score [1 0 2 3] [1 2 3 2]) => [1 2])
       )


(defn fac
  [v n]
  (if (zero? n) v (fac (* v n) (dec n))))


(defn factorial
  [n]
  (fac 1 n))
