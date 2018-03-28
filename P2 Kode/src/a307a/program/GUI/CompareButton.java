package a307a.program.GUI;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root, 300, 250));

        VBox vBox = new VBox();
        root.setRight(vBox);


        Button compareButton = new Button();
        compareButton.setText("Compare");
        compareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Comparing...");
            }
        });

        vBox.getChildren().add(compareButton);

        primaryStage.show();
    }

}