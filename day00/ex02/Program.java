import java.util.Scanner;

public class Program {

    public static int sumOfDigits(int input) {
        int res = 0;
        while (input != 0) {
            res += input % 10;
            input = input / 10;
        }
        return (res);
    }

    public static boolean isItPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (0 == num % i) {
                return (false);
            }
        }
        return (true);
    }

    public static void main(String[] args) {

        int res = 0;
        int sumDigits = 0;
        int inputParam = 0;

        Scanner input = new Scanner(System.in);

        while (true) {

			if (false ==  input.hasNextInt()) {
				System.exit(-1);
			}

            inputParam = input.nextInt();

            if (42 == inputParam) {
                break;
			}

            sumDigits = sumOfDigits(inputParam);

            if (true == isItPrime(sumDigits)) {
                res++;
			}
        }

        System.out.println("Count of coffee-request - " + res);

		input.close();
	}
}