package edu.school21.numbers;

public class NumberWorker {

    public static boolean isPrime(int number) {
        if (number <= 1)
            throw new IllegalNumberException();
        for (int i = 2; i * i <= number; i++) {
            if ((number % i) == 0)
                return (false);
        }
        return (true);
    }

    public int digitsSum(int number) {
        int sum = 0;
        do {
            sum += number % 10;
            number /= 10;
        } while (number != 0);
        if (sum < 0)
			sum *= -1;
		return (sum);
    }
}