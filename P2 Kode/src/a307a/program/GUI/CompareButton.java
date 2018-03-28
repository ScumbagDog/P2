package a307a.program.GUI;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;


public class CompareButton extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        primaryStage.setScene(new Scene(root, 300, 250));

        Button btn = new Button();
        btn.setText("Compare");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Comparing...");
            }
        });

        btn.setLayoutX(235);
        btn.setLayoutY(225);

        root.getChildren().add(btn);

        primaryStage.show();
    }

}