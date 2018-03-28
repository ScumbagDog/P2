package a307a.program.GUI;

import a307a.program.GUI.MenuBar.FileTab;
import a307a.program.GUI.MenuBar.settings.SettingsFile;
import a307a.program.GUI.MenuBar.settings.SettingsMenu;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuFile {
    public static void MainMenu(Stage stage) {
        stage.setTitle("Main Menu");
        int windowHeight, windowWidth;
        Boolean windowFullscreen;
        List<File> srcFileList = new ArrayList<>(),
                compFileList = new ArrayList<>();
        Text fileName = new Text("Awaiting action...");


        windowWidth = Integer.parseInt(SettingsFile.AccessSettings("width"));
        windowHeight = Integer.parseInt(SettingsFile.AccessSettings("height"));
        windowFullscreen = Boolean.parseBoolean(SettingsFile.AccessSettings("fullscreen"));

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Files");
        MenuItem addSrcFile = new MenuItem("Add Source");
        addSrcFile.setOnAction(event -> {
            srcFileList.add(FileTab.AddFile());
            fileName.setText("File " + srcFileList.get(srcFileList.size() - 1) + " has been added!");
        });
        MenuItem addCompFile = new MenuItem("Add Comparison");
        addCompFile.setOnAction(event -> {
            compFileList.add(FileTab.AddFile());
            fileName.setText("File " + compFileList.get(compFileList.size() - 1) + " has been added!");
        });
        menuFile.getItems().addAll(addSrcFile, addCompFile);

        Menu menuSetting = new Menu("Window");
        MenuItem addSetting = new MenuItem("Settings");
        addSetting.setOnAction(event -> {
            String version = "???";
            SettingsMenu.WindowSettings(version);
        });
        menuSetting.getItems().add(addSetting);

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

        menuBar.getMenus().addAll(menuFile, menuSetting);
        algorithmBar.getMenus().addAll(menuAlgorithm);

        //Panel del
        VBox vbox = new VBox(50);
        vbox.setTranslateX(50);
        vbox.setTranslateY(50);
        SplitPane rightSplitPanel = new SplitPane();
        rightSplitPanel.setPrefSize(300, 500);
        final CheckBox algorithm1 = new CheckBox("Algoritme1");
        rightSplitPanel.getItems().addAll(algorithm1);
        vbox.getChildren().add(algorithm1);

        BorderPane elementHolder = new BorderPane();
        BorderPane algorithmList = new BorderPane();
        elementHolder.setTop(menuBar);
        elementHolder.setLeft(algorithmList);
        algorithmList.setBottom(algorithmBar);
        elementHolder.setBottom(fileName);

        Scene scene = new Scene(elementHolder, windowWidth, windowHeight);
        scene.setFill(Color.OLDLACE);

        stage.setScene(scene);
        stage.setFullScreen(windowFullscreen);
        stage.show();
    }
}
