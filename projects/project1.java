/*
 * CSC-239 Project 1: Day of the Year
 * Student: Pedro Delesporte
 * Date: 10/10/2025
 * Description: This program prompts the user to input a string of text
 * representing a numeric date in the following format:
 * MM DD YYYY
 * The program outputs the day of that particular year that
 * the string represents.
 */

package projects;
import java.util.Scanner;

public class project1 {
    static void main() {
        Scanner input = new Scanner(System.in);

        while (true) { // This creates an INFINITE LOOP that keeps asking for dates until the user says "quit"
            System.out.print("Please enter a date in the format MM DD YYYY (or 'q' to quit): ");

            // Check if user wants to quit
            if (input.hasNext("q")) {
                System.out.println("Goodbye!");
                break;
            }

            // Validate all three numbers
            if (input.hasNextInt()) {
                int month = input.nextInt();

                if (input.hasNextInt()) {
                    int day = input.nextInt();

                    if (input.hasNextInt()) {
                        int year = input.nextInt();

                        // Check if date is valid
                        if (isValidDate(month, day, year)) {
                            int daysElapsed = calculateDaysElapsed(month, day, year);
                            System.out.println("Number of days elapsed from January 1, " +  year + ": " + daysElapsed);
                            System.out.println(); // empty line for spacing
                        } else {
                            System.out.println("Invalid date!");
                            System.out.println(); // empty line for spacing
                        }

                    } else {
                        System.out.println("Please enter three numbers or 'q' to quit!");
                        input.next(); // clear invalid input
                    }

                } else {
                    System.out.println("Please enter three numbers or 'q' to quit!");
                    input.next(); // clear invalid input
                }

            } else {
                System.out.println("Please enter three numbers or 'q' to quit!");
                input.next(); // clear invalid input
            }

            // Clear the buffer for the next iteration
            input.nextLine();
        }

        input.close();
    }

    // Method to check if a year is a leap year
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Method to check if the date is valid
    public static boolean isValidDate(int month, int day, int year) {
        // Check basic ranges
        if (month < 1 || month > 12) {
            System.out.println("Month must be between 1-12");
            return false;
        }
        if (year < 1900 || year > 2100) {
            System.out.println("Year must be between 1900-2100");
            return false;
        }

        // Check day based on month
        if (month == 2) { // February
            if (isLeapYear(year)) {
                if (day < 1 || day > 29) {
                    System.out.println("February in " + year + " has 29 days (leap year)");
                    return false;
                }
            } else {
                if (day < 1 || day > 28) {
                    System.out.println("February in " + year + " has 28 days");
                    return false;
                }
            }
        }
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            // April, June, September, November have 30 days
            if (day < 1 || day > 30) {
                System.out.println("Month " + month + " has 30 days");
                return false;
            }
        }
        else {
            // All other months have 31 days
            if (day < 1 || day > 31) {
                System.out.println("Month " + month + " has 31 days");
                return false;
            }
        }

        return true;
    }

    // Method to calculate days elapsed from January 1st
    public static int calculateDaysElapsed(int month, int day, int year) {
        int totalDays = 0;

        // Add days for complete months that have passed
        for (int m = 1; m < month; m++) {
            totalDays += getDaysInMonth(m, year);
        }

        // Add days in the current month
        totalDays += day;

        return totalDays;
    }

    // Method to get number of days in a specific month
    public static int getDaysInMonth(int month, int year) {
        return switch (month) {
            case 1 -> 31; // January
            case 2 -> isLeapYear(year) ? 29 : 28; // February
            case 3 -> 31; // March
            case 4 -> 30; // April
            case 5 -> 31; // May
            case 6 -> 30; // June
            case 7 -> 31; // July
            case 8 -> 31; // August
            case 9 -> 30; // September
            case 10 -> 31; // October
            case 11 -> 30; // November
            case 12 -> 31; // December
            default -> 0;
        };
    }
}
