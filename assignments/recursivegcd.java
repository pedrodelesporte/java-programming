import java.util.Scanner;

public class RecursiveGCD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.print("Enter two integers (or 'q' to exit): ");

            if (scanner.hasNext("q")) {
                break;
            }

            int m = scanner.nextInt();
            int n = scanner.nextInt();

            // call the recursive method
            int gcdResult = gcd(m, n);

            // print the final result
            System.out.println("The GCD of " + m + " and " + n + " is " + gcdResult + ".");
            System.out.println();
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    // recursive method for Euclid's Algorithm
    public static int gcd(int m, int n) {

        System.out.println("Entering 'gcd' method:");
        System.out.println("m=" + m + ", n=" + n);

        // if m % n is 0, the GCD is n
        if (m % n == 0) {

            System.out.println("Returning 'gcd' value = " + n + " (BASE CASE:");
            System.out.println("m=" + m + ", n=" + n + ")");
            return n;
        }

        // GCD is gcd(n, m % n)
        // we call gcd again but we swap the numbers, remainder (m % n) becomes the new m
        else {
            int result = gcd(n, m % n);

            System.out.println("Returning 'gcd' value = " + result + " (recursive case:");
            System.out.println("m=" + m + ", n=" + n + ")");
            return result;
        }
    }
}
