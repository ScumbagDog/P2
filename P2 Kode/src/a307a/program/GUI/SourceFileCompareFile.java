package a307a.program.GUI;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;




public class SourceFileCompareFile extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 180, 180);
        primaryStage.setScene(scene);


        SplitPane splitPane = new SplitPane();
        borderPane.setCenter(splitPane);
        StackPane stackPane = new StackPane();
        StackPane stackPane2 = new StackPane();

        splitPane.setDividerPositions(0.5);


        SplitPane splitPane2 = new SplitPane();
        borderPane.setTop(splitPane2);
        StackPane stackPane3 = new StackPane();
        StackPane stackPane4 = new StackPane();

        splitPane2.setDividerPositions(0.5);
        splitPane2.setOrientation(Orientation.VERTICAL);

        SplitPane splitPane3 = new SplitPane();
        StackPane stackPane5 = new StackPane();
        StackPane stackPane6 = new StackPane();

        splitPane3.setDividerPositions(0.5);
        splitPane3.setOrientation(Orientation.HORIZONTAL);



        Text text = new Text("Source File");
        Text text2 = new Text("Compare file");


        HBox hBox = new HBox();
        hBox.getChildren().add(text);

        HBox hBox2 = new HBox();
        hBox2.getChildren().add(text2);



        Button button = new Button();
        button.setText("Get file");
        button.setOnAction(event -> {
            Stage stage = new Stage();

            FileChooser browseSourceFile = new FileChooser();
            browseSourceFile.setTitle("Select a source file");
            browseSourceFile.showOpenDialog(stage);

        });


        button.setLayoutX(235);
        button.setLayoutY(225);


        Button button2 = new Button();
        button2.setText("Get file");
        button2.setOnAction(event -> {
            Stage stage = new Stage();

            FileChooser browseSourceFile = new FileChooser();
            browseSourceFile.setTitle("Select a compare file");
            browseSourceFile.showOpenDialog(stage);

        });

        button2.setLayoutX(235);
        button2.setLayoutY(225);


        splitPane.getItems().addAll(stackPane, stackPane2);


        stackPane.getChildren().add(button);
        stackPane2.getChildren().add(button2);



        splitPane2.getItems().addAll(stackPane3, stackPane4); //stackPane3 skal have menuen

        stackPane4.getChildren().add(splitPane3);
        splitPane3.getItems().addAll(stackPane5, stackPane6);

        stackPane5.getChildren().add(hBox);
        stackPane6.getChildren().add(hBox2);

        primaryStage.show();

    }
}

