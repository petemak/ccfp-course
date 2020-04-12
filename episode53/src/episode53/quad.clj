(ns episode53.quad)

(defn quad
  [a b c]
  (cond (and (zero? a) (zero? b))
        []

        (zero? a)
        (/ (- c ) b)

        :else        
        (let [disc (- (* b b) (* 4 a c))]
          [(/ (+ (- b) (Math/sqrt disc))
              (* 2 a))
           (/ (- (- b) (Math/sqrt disc))
              (* 2 a))])))

(defn bad-fac
  "Bad implementation will overflow n = 5
   (* 5 (* 4 (* 3 (* 2 1))))"
  [n]
  (if (zero? n)
    1
    (* n (bad-fac (dec n)))))

(defn fac-it
  "(fac-it 1 5) => (fac-it 5 4) => (fac-it 20 3)
                => (fac-it 60 2) => (fac-it 120 1)
                => (fac-it 120 0)
                => 120"
  [f n]
  (if (zero? n)
    f
    (fac-it (* f n) (dec n))))

(defn fac-it1  
  [n]
  (fac-it 1 n))


(defn fact
  [n]
  (loop [acc 1
         idx n]
    (if (= idx 1)
      acc
      (recur (* acc idx) (dec idx)))))
