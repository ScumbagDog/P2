package GUI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import jdk.jfr.Percentage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuFile {

    public static void MainMenu(Stage stage) {
        stage.setTitle("Main Menu");
        List<File> compFileList = new ArrayList<>();
        Text fileName = new Text("Awaiting action...");

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Files");
        MenuItem addFile = new MenuItem("Add");
        addFile.setOnAction(event -> {
            Stage srcFile = new Stage();

            FileChooser browseSourceFile = new FileChooser();
            browseSourceFile.setTitle("Select a source file");
            File filePath = browseSourceFile.showOpenDialog(srcFile);

            fileName.setText("File " + filePath.getName() + " has been added!");
        });
        menuFile.getItems().add(addFile);

        Menu menuSetting = new Menu("Window");
        MenuItem addSetting = new MenuItem("Settings");
        addSetting.setOnAction(event -> {
            String version = "???";
            SettingsMenu.WindowSettings(version);
        });
        menuSetting.getItems().add(addSetting);

        Menu menuCompare = new Menu("Compare");


        MenuBar algorithmBar = new MenuBar();

        Menu menuAlgorithm = new Menu("Algorithm");
        CheckMenuItem addAlgorithm = new CheckMenuItem("Algorithm1");
        addAlgorithm.setOnAction(event -> {
            if (addAlgorithm.isSelected()) {
                System.out.println("Algorithm 1 has been selected");
            } else {
                System.out.println("Algorithm 1 has been removed");
            }

        });
        menuAlgorithm.getItems().add(addAlgorithm);

        menuBar.getMenus().addAll(menuFile, menuCompare, menuSetting);
        algorithmBar.getMenus().addAll(menuAlgorithm);

        //Panel del
        HBox hbox = new HBox(50);
        hbox.setTranslateX(50);
        hbox.setTranslateY(50);
        SplitPane rightSplitPanel = new SplitPane();
        rightSplitPanel.setPrefSize(300, 500);
        final CheckBox algorithm1 = new CheckBox("Algoritme1");
        rightSplitPanel.getItems().addAll(algorithm1);
        hbox.getChildren().add(algorithm1);

        BorderPane elementHolder = new BorderPane();
        BorderPane algorithmList = new BorderPane();
        elementHolder.setTop(menuBar);
        elementHolder.setLeft(algorithmList);
        algorithmList.setBottom(algorithmBar);
        elementHolder.setBottom(fileName);

        Scene scene = new Scene(elementHolder, 600, 600);
        scene.setFill(Color.OLDLACE);

        stage.setScene(scene);
        stage.show();
    }
}
