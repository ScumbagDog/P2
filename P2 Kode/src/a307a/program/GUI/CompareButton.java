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

        /* Vi skal have en borderpane som alle være ting skal være i. Inde i den borderpane skal der være en vertical box i højre vindue. Inde i den box
        skal vi have compareknap*/

        //Her laves borderpanen
        BorderPane root = new BorderPane();
        primaryStage.setScene(new Scene(root, 300, 250));

        //Her laves vertical boxen
        VBox vBox = new VBox();

        //Her laves compareknappen og sættes til at skrive Comparing... når man klikker på den
        Button compareButton = new Button();
        compareButton.setText("Compare");
        compareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Comparing...");
            }
        });

        //Her indsættes knappen i vertical boxen
        vBox.getChildren().add(compareButton);

        //Her sættes boxen i det højre vindue.
        root.setRight(vBox);

        primaryStage.show();
    }

}