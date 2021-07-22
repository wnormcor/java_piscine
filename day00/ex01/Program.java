import java.util.Scanner; 

public class Program {

    public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		if (false ==  input.hasNextInt()) {
			System.err.println("IllegalArgument");
			System.exit(-1);		
		}
		int num = input.nextInt();

		if (num < 2) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}

		int iter = 1;
		for (int i = 2; i * i <= num; i++, iter++) {
			if (0 == num % i) {
				System.out.println("false " + iter);
				System.exit(0);
			}
		}
		System.out.println("true " + iter);

		input.close();
    }
}