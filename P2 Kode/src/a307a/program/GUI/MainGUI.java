package a307a.program.GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage stage = new Stage();
        MenuFile.MainMenu(stage);
    }
}