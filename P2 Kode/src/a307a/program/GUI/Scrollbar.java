package a307a.program.GUI;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.stage.Stage;

public class Scrollbar extends Application {



    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        Scene scene = new Scene(root, 180, 180);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrollbar");

        final ScrollBar scrollbar = new ScrollBar();

        scrollbar.setLayoutX(scene.getWidth()-scrollbar.getWidth());
        scrollbar.setMin(0);
        scrollbar.setOrientation(Orientation.VERTICAL);
        scrollbar.setPrefHeight(180);
        scrollbar.setMax(360);

        root.getChildren().addAll(scrollbar);

        primaryStage.show();
    }
}