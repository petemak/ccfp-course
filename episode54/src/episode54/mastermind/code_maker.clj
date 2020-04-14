(ns episode54.mastermind.code-maker)

(defn position-matches
  "Find matching positons beteen the guess and actual code
   Example code:  [1 0 2 3]
   Example guess: [6 2 4 3]"
  [code guess]
  ;;(filter some? (map #(if (= %1 %2) :pos) code guess))
  ;; [(reduce + (map #(if (= (first %) (second %)) 1 0)
  ;;                  (partition 2 (interleave code guess))))]
  (count (filter identity (map #(= %1 %2) code guess))))


(defn value-matches
  "Find matching values of guesses in the code"
  [code guess]
  (count (filter true? (map #(contains? (set code) %) guess))))


(defn count-of
  "Returns the number of time val appears
   in the list of vals"
  [val vals]
  (count (filter true? (map #(= val %) vals))))


(defn over-count
  "If a guess for a code appears multiple times then calulate
   the difference form the a actual occurrance in the code
   NOTE:
    1) The difference per code will only be positive if "
  [code guess]
  (let [code-vals (set code)]
    (->> code-vals
         (map #(- (count-of % guess) (count-of % code)))
         (filter pos?)
         (reduce +))))


(defn score
  "Score a guess against a code returning "
  [code guess]
  (let [p (position-matches code guess)
        v (value-matches code guess)
        o (over-count code guess)]
    [p (- v p o)]))


