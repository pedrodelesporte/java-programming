package lab11a;

import java.util.Scanner;

public class Lab11a {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter x and y coordinates for p1 (or q to exit): ");
            String input1 = scanner.nextLine();

            if (input1.equalsIgnoreCase("q")) {
                break;
            }

            System.out.print("Enter x and y coordinates for p2 (or q to exit): ");
            String input2 = scanner.nextLine();

            if (input2.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                // coordinates for first point
                String[] coords1 = input1.split(" ");
                double x1 = Double.parseDouble(coords1[0]);
                double y1 = Double.parseDouble(coords1[1]);

                // coordinates for second point
                String[] coords2 = input2.split(" ");
                double x2 = Double.parseDouble(coords2[0]);
                double y2 = Double.parseDouble(coords2[1]);

                NewPoint p1 = new NewPoint(x1, y1);
                NewPoint p2 = new NewPoint(x2, y2);

                System.out.printf("p1=(%8.5f,%8.5f), p2=(%8.5f,%8.5f), numberOfObjects=%d\n",
                        p1.getX(), p1.getY(), p2.getX(), p2.getY(), NewPoint.getNumberOfObjects());

                // calculate and display distances
                double distanceObjectToObject = p1.distance(p2);
                double distanceObjectToCoords = p1.distance(p2.getX(), p2.getY());

                System.out.printf("Distance (object to object) = %8.5f\n", distanceObjectToObject);
                System.out.printf("Distance (object to X,Y coordinates) = %8.5f\n", distanceObjectToCoords);
            } catch (Exception e) {
                System.out.println("Invalid input format. Please enter coordinates as 'x y' (e.g., '3 4')");
            }
        }

        scanner.close();
    }
}
