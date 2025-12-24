/*
 * CSC-239 Project 3: Simple Drawing Application
 *
 * Date: 2025-11-21
 * Student: Pedro Delesporte
 * Description: This program draws lines on the screen using the arrow keys.
 * It starts in the center. The lines are 10 pixels long. Holding Shift moves
 * the cursor without drawing. Pressing Delete clears the screen.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SimpleDrawingApp extends Application {

    private double currentX;
    private double currentY;

    // pane where we will add our lines
    private Pane drawingPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // create the pane
        drawingPane = new Pane();

        // start the pen in the middle of the screen (300, 200)
        currentX = 300;
        currentY = 200;

        // crete the scene with size 600x400
        Scene scene = new Scene(drawingPane, 600, 400);

        // when a key is pressed, call our helper method to handle it
        scene.setOnKeyPressed(e -> handleKeys(e));

        // set up the stage and show it
        primaryStage.setTitle("CSC-239 Project 3");
        primaryStage.setScene(scene);
        primaryStage.show();

        // make sure the pane is ready to receive key presses
        drawingPane.requestFocus();
    }

//TODO:
    private void handleKeys(KeyEvent event) {
        // check if  user pressed DELETE
        if (event.getCode() == KeyCode.DELETE) {
            clearScreen();
            return;
        }

        // temporary variables to calculate where we  move next
        double nextX = currentX;
        double nextY = currentY;

        // check arrow key was pressed and adjust the next position
        // use 10 pixels as the step size
        if (event.getCode() == KeyCode.UP) {
            nextY = nextY - 10;
        } else if (event.getCode() == KeyCode.DOWN) {
            nextY = nextY + 10;
        } else if (event.getCode() == KeyCode.LEFT) {
            nextX = nextX - 10;
        } else if (event.getCode() == KeyCode.RIGHT) {
            nextX = nextX + 10;
        } else {
            // ignore other keys
            return;
        }

        // check if SHIFT key is being held down
        if (event.isShiftDown()) {
            // if Shift is down, we just move
            movePen(nextX, nextY);
        } else {
            // if Shift is NOT down, we draw a line
            drawLiine(nextX, nextY);
        }
    }

//TODO:
    private void drawLiine(double newX, double newY) {
        // create new line from current x,y to new x,y
        Line myLine = new Line(currentX, currentY, newX, newY);

        // add the line to the screen
        drawingPane.getChildren().add(myLine);

        // update our current position to the new spot
        currentX = newX;
        currentY = newY;
    }


    private void movePen(double newX, double newY) {
        // just update the variables
        currentX = newX;
        currentY = newY;
    }


    private void clearScreen() {
        // remove all lines from the pane
        drawingPane.getChildren().clear();

        // reset coordinates to the center (300, 200)
        currentX = 300;
        currentY = 200;
    }
}
