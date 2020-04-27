(ns episode54.mastermind.auto-play-test
  (:require [midje.sweet :refer :all]
            [episode54.mastermind.auto-play :refer :all]
            [episode54.mastermind.code-maker :as cm]
            [episode54.mastermind.code-breaker :as cb]))


(facts "about auto-play"
       (fact "Spy. If initial guess "
             (auto-play cb/break-code) => 1
             (provided (random-code) => [0 0 0 0])
             (provided (cm/score [0 0 0 0] [0 0 0 0]) => [4 0])
             (provided (cb/break-code nil []) => [0 0 0 0]))

       (fact "if code is [0 0 0 1] then should take two tries"
             (auto-play cb/break-code) => 2
             (provided (random-code) => [0 0 0 1])))
