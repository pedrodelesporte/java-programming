import java.util.Scanner;

public class TriangleMenu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the three sides of the triangle ");
        double side1 = input.nextDouble();
        double side2 = input.nextDouble();
        double side3 = input.nextDouble();

        input.nextLine();

        System.out.print("Enter the color of the triangle ");
        String color = input.nextLine();

        System.out.print("Enter a boolean value for filled (true or false) ");
        boolean filled = input.nextBoolean();

        Triangle triangle = new Triangle(side1, side2, side3, color, filled);

        System.out.println("Triangle: " + triangle.toString());
        System.out.printf("Perimeter = %8.2f, Area = %8.2f\n",
        triangle.getPerimeter(), triangle.getArea());

        input.close();
    }
}
