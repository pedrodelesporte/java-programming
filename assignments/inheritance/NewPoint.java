package lab11a;

public class NewPoint extends MyPoint {
    private static int numberOfObjects = 0;

    public NewPoint() {
        super();
        numberOfObjects++;
    }

    public NewPoint(double xValue, double yValue) {
        super(xValue, yValue);
        numberOfObjects++;
    }

    public static int getNumberOfObjects() {
        return numberOfObjects;
    }
}

