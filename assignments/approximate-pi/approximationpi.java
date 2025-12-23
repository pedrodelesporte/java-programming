import java.util.Scanner;

public class approximationpi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int loopMax;

        System.out.print("Enter maximum loop count:");
        loopMax = sc.nextInt();
        while (loopMax <= 0) { //input validation
            System.out.println("Please enter a positive integer");
            System.out.print("Enter maximum loop count:");
            loopMax = sc.nextInt();
        }

        System.out.println("Maximum loop count=" + loopMax);
        System.out.println();
        System.out.printf("%6s %15s %15s %15s %15s%n",
                "Count", "Approximation", "Error", "Variance (%)", "termI");

        double piOver4 = 0.0;

        for (int i = 1; i <= loopMax; i++) {
            int sign = (i % 2 == 0) ? -1 : 1;         // alternate + and -
            double term = (double) sign / (2 * i - 1);
            piOver4 += term;

            double approx = piOver4 * 4;
            double error = approx - Math.PI;
            double variance = (error / Math.PI) * 100;

            if (i <= 50 || i == loopMax || i % 100 == 0 || i % 100 == 1) {
                System.out.printf("%6d: %15.10f %+15.10f %+15.10f %% %+15.10f%n",
                        i, approx, error, variance, term);
            }
        }

        System.out.println();
        System.out.printf("Math.PI = %15.10f%n", Math.PI);

        sc.close();
    }
}
