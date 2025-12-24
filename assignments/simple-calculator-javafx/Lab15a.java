import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Lab15a extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label lblNum1 = new Label("First number:");
        TextField txtNum1 = new TextField();

        Label lblNum2 = new Label("Second number:");
        TextField txtNum2 = new TextField();

        Label lblResult = new Label("Answer:");
        TextField txtResult = new TextField();
        txtResult.setEditable(false);  // user should not type here

        // flowPane for labels and text box
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(10, 10, 10, 10));

        flowPane.getChildren().addAll(
                lblNum1, txtNum1,
                lblNum2, txtNum2,
                lblResult, txtResult
        );


        Button btnAdd = new Button("Add");
        Button btnSubtract = new Button("Subtract");
        Button btnMultiply = new Button("Multiply");
        Button btnDivide = new Button("Divide");

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10, 10, 10, 10));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(
                btnAdd, btnSubtract, btnMultiply, btnDivide
        );

        // simple helper method inside start via anonymous class
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // read numbers
                double n1;
                double n2;

                try {
                    n1 = Double.parseDouble(txtNum1.getText());
                    n2 = Double.parseDouble(txtNum2.getText());
                } catch (NumberFormatException ex) {
                    // if user types something not a number, clear result
                    txtResult.setText("");
                    return;
                }

                double answer = 0.0;

                if (event.getSource() == btnAdd) {
                    answer = n1 + n2;
                } else if (event.getSource() == btnSubtract) {
                    answer = n1 - n2;
                } else if (event.getSource() == btnMultiply) {
                    answer = n1 * n2;
                } else if (event.getSource() == btnDivide) {
                    // using doubles: n1 / 0.0 will give infinity
                    answer = n1 / n2;
                }

                txtResult.setText(Double.toString(answer));
            }
        };

        btnAdd.setOnAction(handler);
        btnSubtract.setOnAction(handler);
        btnMultiply.setOnAction(handler);
        btnDivide.setOnAction(handler);



        BorderPane root = new BorderPane(); //border pane still in start method body
        root.setCenter(flowPane);
        root.setBottom(buttonBox);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Simple Calculator - Pedro Delesporte");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
