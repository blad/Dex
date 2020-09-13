(ns ronan.core-test
  (:require [clojure.test :refer :all]
            [ronan:refer :all]))

(deftest a-test
  (testing "tautology"
    (is (= 1 1))))
