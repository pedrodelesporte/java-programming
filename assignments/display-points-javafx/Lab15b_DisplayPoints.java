
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Lab15b_DisplayPoints extends Application {

    // radius for each "point"
    private static final double POINT_RADIUS = 5.0;

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();

        // handle mouse clicks on the pane
        pane.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                double x = event.getX();
                double y = event.getY();

                if (event.getButton() == MouseButton.PRIMARY) {
                    // add a new point
                    Circle point = new Circle(x, y, POINT_RADIUS);
                    pane.getChildren().add(point);

                    // print to console
                    System.out.println("Left-click: added point at (" + x + ", " + y + ")");
                }
                else if (event.getButton() == MouseButton.SECONDARY) {
                    // remove a point if any at this position
                    ObservableList<Node> list = pane.getChildren();

                    // go from the end so we remove the top point first
                    for (int i = list.size() - 1; i >= 0; i--) {
                        Node node = list.get(i);
                        if (node instanceof Circle) {
                            Circle c = (Circle) node;

                            double dx = x - c.getCenterX();
                            double dy = y - c.getCenterY();
                            double distanceSquared = dx * dx + dy * dy;

                            if (distanceSquared <= POINT_RADIUS * POINT_RADIUS) {
                                list.remove(i);

                                // print to console
                                System.out.println("Right-click: removed point at ("
                                        + c.getCenterX() + ", " + c.getCenterY() + ")");
                                break; // stop after removing one point
                            }
                        }
                    }
                }
            }
        });

        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setTitle("Lab 15b - Display Points");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
