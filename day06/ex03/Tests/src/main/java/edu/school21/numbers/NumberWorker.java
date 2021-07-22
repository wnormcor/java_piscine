package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;

public class NumberWorker {
    public boolean isPrime(int number) {
        if (number <= 1) {
            throw new IllegalNumberException();
        }
        if (number == 2) {
            return true;
        } else if (number % 2 == 0) {
            return false;
        }
        int i = 3;
        while (i <= number / i) {
            if (number % i == 0) {
                return false;
            }
            i += 2;
        }
        return true;
    }

    public int digitsSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }
        return sum;
    }
}
