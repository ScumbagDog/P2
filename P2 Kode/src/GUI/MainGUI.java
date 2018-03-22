package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MIDI Comparison System");
        int windowWidth = 1280, windowHeight = 720;

        Pane root = new Pane();

        Button source = new Button();
        Button compare = new Button();
        Button run = new Button();
        run.setText("Run!");
        Button settings = new Button();
        settings.setText("Settings");

        source.setText("Source files");
        compare.setText("Comparison files");


        source.setMaxWidth(windowWidth / 4);

        root.getChildren().add(source);
        root.getChildren().add(compare);
        root.getChildren().add(run);
        root.getChildren().add(settings);

        primaryStage.setScene(new Scene(root, windowWidth, windowHeight));
        primaryStage.show();
    }
}
