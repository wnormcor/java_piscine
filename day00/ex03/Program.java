import java.util.Scanner;

public class Program {

    public static void FatalError(Scanner scan) {
        scan.close();
        System.err.println("Illegal Argument");
        System.exit(-1);
    }

    public static void OutResult(long results) {

        long countOfWeek = 1;
		
        for (; results / countOfWeek > 10; countOfWeek *= 10)
            ;
	
        for (int week = 1; results != 0; week++, countOfWeek /= 10) {
            long i = results / countOfWeek;
            System.out.print("Week " + week + " ");
            for (int equal = 1; equal <= i; equal++) {
                System.out.print("=");
			}
            System.out.println(">");
            results = results - countOfWeek * i;
        }
    }

    public static int getNextWeekOrMark(Scanner input) {

        if (!input.hasNextInt()) {
            FatalError(input);
		}

        return input.nextInt();
    }

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        long resultsArray = 0;

        for (int numOfWeek = 1; ; numOfWeek++) {

            long maxMark = 10;

            String str = input.next();

            if (str.equals("42")) {
                OutResult(resultsArray);
                break;
            } else if (!str.equals("Week")) {
                FatalError(input);
			}

            if (numOfWeek != getNextWeekOrMark(input)) {
                FatalError(input);
			}

            for (int i = 0; i < 5; i++) {

                long newMark = getNextWeekOrMark(input);

                if (newMark < 1 || newMark > 9) {
                    FatalError(input);
				}

                if (maxMark > newMark) {
                    maxMark = newMark;
				}
            }

            resultsArray = resultsArray * 10 + maxMark;

            if (numOfWeek == 18) {
                OutResult(resultsArray);
                break;
            }
        }
        input.close();
    }
}