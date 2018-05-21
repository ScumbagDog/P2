package GUI;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Scrollbar extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        /* Vi skal have en borderpane som alle være ting skal være i. Inde i den borderpane skal der være en scrollbar til hver af vinduerne, i dette tilfælde
        center vinduet*/

        //her laves borderpanen
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 180, 180);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrollbar");

        //her laves scrollbaren
        final ScrollBar scrollbar = new ScrollBar();

        //her gives scrollbaren nogle egenskaber

        scrollbar.setMin(0);
        scrollbar.setOrientation(Orientation.VERTICAL);
        scrollbar.setPrefHeight(180);
        scrollbar.setMax(360);


        //her sættes scrollbaren i centervinduet
        borderPane.setCenter(scrollbar);

        primaryStage.show();
    }
}