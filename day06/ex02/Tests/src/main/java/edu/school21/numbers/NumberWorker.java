package edu.school21.numbers;

class IllegalNumberException extends RuntimeException {
}

public class NumberWorker {

    public static boolean isPrime(int num) {
        if (num <= 1)
            throw new IllegalNumberException();
        for (int i = 2; i * i <= num; i++) {
            if (0 == num % i) {
                return (false);
            }
        }
        return (true);
    }

    public int digitsSum(int num) {
        int sum = 0;
        do {
            sum += num % 10;
            num /= 10;
        } while (num != 0);
        return Math.abs(sum);
    }
}