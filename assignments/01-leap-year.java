import java.util.Scanner;

public class lab03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a month in the year:");
        int month = input.nextInt();

        System.out.print("Enter a year:");
        int year = input.nextInt();

        int days = 0;
        String name = "";

        if (month == 1) {
            name = "January";
            days = 31;
        } else if (month == 2) {
            name = "February";
            if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
                days = 29;
            } else {
                days = 28;
            }
        } else if (month == 3) {
            name = "March";
            days = 31;
        } else if (month == 4) {
            name = "April";
            days = 30;
        } else if (month == 5) {
            name = "May";
            days = 31;
        } else if (month == 6) {
            name = "June";
            days = 30;
        } else if (month == 7) {
            name = "July";
            days = 31;
        } else if (month == 8) {
            name = "August";
            days = 31;
        } else if (month == 9) {
            name = "September";
            days = 30;
        } else if (month == 10) {
            name = "October";
            days = 31;
        } else if (month == 11) {
            name = "November";
            days = 30;
        } else if (month == 12) {
            name = "December";
            days = 31;
        } else {
            System.out.println("Invalid input");
            return;
        }
        
        System.out.println(name + " " + year + " has " + days + " days");
    }
}

