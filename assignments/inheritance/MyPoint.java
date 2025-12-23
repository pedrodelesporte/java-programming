package lab11a;

public class MyPoint {
    double x, y;

    public MyPoint() {
        this(0, 0);
    }

    public MyPoint(double xValue, double yValue) {
        this.x = xValue;
        this.y = yValue;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(double xValue, double yValue) {
        return Math.sqrt(Math.pow(x - xValue, 2) + Math.pow(y - yValue, 2));
    }

    public double distance(MyPoint otherPoint) {
        return distance(otherPoint.getX(), otherPoint.getY());
    }
}
