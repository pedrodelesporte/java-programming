/*
 * This program compares two methods of finding prime numbers
 * by counting how many divisor values need to be tested
 * in order to determine whether or not a number is a prime.
 */

public class PrimesComparison {
    static final int NUM_OF_PRIMES = 50;


    // Two-dimensional arrays for holding primes and divisor counts:
    static int[][] sequentialDivisors = new int[NUM_OF_PRIMES][2];
    static int[][] primeDivisors = new int[NUM_OF_PRIMES][2];
    static final int PRIME_INDEX = 0;  // (used to access first column)
    static final int COUNT_INDEX = 1;  // (used to access second column)

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Find Prime Numbers using two algorithms:  "sequential divisors"
        // and "prime divisors".  Output results side-by-side.
        primesSequentialDivisors(sequentialDivisors);
        primesPrimeDivisors(primeDivisors);
        outputResults(sequentialDivisors, primeDivisors);
    }  // (end 'main')

    /**
     * Calculate the first NUM_OF_PRIMES prime numbers, using all integers
     * between 2 and half of the candidate prime number as divisors. Store the
     * prime numbers in the array referenced by the primeArray parameter. Also
     * store the number of divisors which were checked to get this result.
     *
     * @param primesArray = output array for prime numbers and counts.
     */
    static void primesSequentialDivisors(int[][] primesArray) {
        int count = 0; // Count the number of prime numbers
        int number = 2; // A number to be tested for primeness
        boolean isPrime; // Flag to remember if the current number id prime
        int divisorCount;

        // Repeatedly find prime numbers
        while (count < NUM_OF_PRIMES) {
            // Assume the number is prime
            isPrime = true;
            divisorCount = 0;
            // Test whether number is prime
            for (int divisor = 2; divisor <= number / 2; divisor++) {
                divisorCount++;
                //If the remainder is zero, the number is NOT prime
                if (number % divisor == 0) {
                    // Set isPrime to false, if the number is not prime
                    isPrime = false;
                    break; // Exit the for loop
                }
            }

            // Store the prime number and division count in the array
            if (isPrime) {
                primesArray[count][PRIME_INDEX] = number;
                primesArray[count][COUNT_INDEX] = divisorCount;
                count++; // Increase the count
            }

            // Check if the next number is prime
            number++;
        }
    }  // (end 'primesSequentialDivisors')

    /**
     * Calculate the first NUM_OF_PRIMES prime numbers, using all prime numbers
     * less that the square root of the candidate prime number as divisors.
     * Store the prime numbers in the array referenced by the primeArray
     * parameter. Also store the number of divisors which were checked to get
     * this result.
     *
     * @param primesArray = output array for prime numbers and counts.
     */
    static void primesPrimeDivisors(int[][] primesArray) {

        int count = 0;
        int number = 2;
        boolean isPrime;
        int divisorCount;

        //  find prime numbers
        while (count < NUM_OF_PRIMES) {
            // assume the number is prime
            isPrime = true;
            divisorCount = 0;

            // test whether number is prime using prime divisors
            for (int i = 0; i < count; i++) {
                int primeDivisor = primesArray[i][PRIME_INDEX];

                // only check divisors up to sqrt(number)
                if (primeDivisor * primeDivisor > number) {
                    break;
                }

                divisorCount++;

                // if the remainder is zero, number not prime
                if (number % primeDivisor == 0) {
                    isPrime = false;
                    break;
                }
            }

            // store the prime number and division count
            if (isPrime) {
                primesArray[count][PRIME_INDEX] = number;
                primesArray[count][COUNT_INDEX] = divisorCount;
                count++;
            }

            number++;
        }
    }

    /**
     * Output the contents of the two arrays which are passed as parameters.
     *
     * @param seqDiv = 2-dimension array with prime numbers, divisor counts.
     * @param primeDiv = 2-dimension array with prime numbers, divisor counts.
     */
    static void outputResults(int[][] seqDiv, int[][] primeDiv) {
        System.out.printf("%12s %12s %12s %12s\n",
                "Prime", "SeqDiv", "Prime", "PrimeDiv");
        for (int i = 0; i < NUM_OF_PRIMES; i++) {
            System.out.printf("%12d %12d %12d %12d\n",
                    seqDiv[i][PRIME_INDEX], seqDiv[i][COUNT_INDEX],
                    primeDiv[i][PRIME_INDEX], primeDiv[i][COUNT_INDEX]);
        }
    }  // (end 'outputResults')
}
