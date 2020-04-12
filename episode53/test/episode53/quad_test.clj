(ns episode53.quad-test
  (:require [midje.sweet :refer :all]
            [episode53.quad :refer :all]))

(facts "about quad"
       (fact "degenarate"
             (quad  0 0 1)  => [])
       (fact "linear"
             (quad 0 1 1) => -1)
       (fact "simple case 1 0 -1"
             (quad 1 0 -1) => [1.0 -1.0]))
