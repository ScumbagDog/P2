package a307a.program.GUI;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.stage.Stage;

public class Scrollbar extends Application {

    final ScrollBar sc = new ScrollBar();


    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 180, 180);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrollbar");
        root.getChildren().addAll(sc);

        sc.setLayoutX(scene.getWidth()-sc.getWidth());
        sc.setMin(0);
        sc.setOrientation(Orientation.VERTICAL);
        sc.setPrefHeight(180);
        sc.setMax(360);


        primaryStage.show();
    }


}