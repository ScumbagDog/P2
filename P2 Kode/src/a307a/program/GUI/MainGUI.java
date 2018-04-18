package a307a.program.GUI;

import a307a.program.GUI.MenuBar.FileListEditor;
import a307a.program.GUI.MenuBar.FileTab;
import a307a.program.GUI.MenuBar.settings.SettingsFile;
import a307a.program.GUI.MenuBar.settings.SettingsMenu;
import a307a.program.GUI.Splits.FileList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Stage stage = new Stage();
        stage.setTitle("Main Menu");
        int windowHeight, windowWidth;
        Boolean windowFullscreen;
        List<File> srcFileList = new ArrayList<>(),
                compFileList = new ArrayList<>();
        Text fileName = new Text("Awaiting action...");

        BorderPane elementHolder = new BorderPane();
        FileList splitLists = new FileList();

        windowWidth = Integer.parseInt(SettingsFile.AccessSettings("width"));
        windowHeight = Integer.parseInt(SettingsFile.AccessSettings("height"));
        windowFullscreen = Boolean.parseBoolean(SettingsFile.AccessSettings("fullscreen"));

        MenuBar menuBar = new MenuBar();

        /*  Knappen der tilføjer musik filer.
            Koden der tilføjer filerne er sat ind i klassen FileTab da det bruges flere gange.
        */
        Menu menuFile = new Menu("Files");

        MenuItem addSrcFile = new MenuItem("Add Source");
        addSrcFile.setOnAction(event -> {
            srcFileList.add(FileTab.AddFile());
            fileName.setText("File \"" + srcFileList.get(srcFileList.size() - 1).getName() + "\" has been added!");
            elementHolder.setCenter(splitLists.ListsOfFiles(srcFileList, compFileList));
        });
        MenuItem addCompFile = new MenuItem("Add Comparison");
        addCompFile.setOnAction(event -> {
            compFileList.add(FileTab.AddFile());
            fileName.setText("File \"" + compFileList.get(compFileList.size() - 1).getName() + "\" has been added!");
            elementHolder.setCenter(splitLists.ListsOfFiles(srcFileList, compFileList));
        });

        MenuItem removeFile = new MenuItem("Remove");
        removeFile.setOnAction(event -> {
            FileListEditor.Remove(srcFileList, compFileList, elementHolder);
        });
        menuFile.getItems().addAll(addSrcFile, addCompFile, removeFile);

        Menu menuSetting = new Menu("Window");
        MenuItem addSetting = new MenuItem("Settings");
        addSetting.setOnAction(event -> {
            String version = "???";
            SettingsMenu.WindowSettings(version);
        });
        menuSetting.getItems().add(addSetting);

        menuBar.getMenus().addAll(menuFile, menuSetting);

        BorderPane algorithmList = new BorderPane();

        /* Kan ikke stå i klasser for sig selv, da man ellers ikke kan opdatere instansen af elementHolder i vores Main.*/
        elementHolder.setTop(menuBar);
        elementHolder.setLeft(algorithmList);
        elementHolder.setBottom(fileName);
        elementHolder.setCenter(splitLists.ListsOfFiles(srcFileList, compFileList));

        Scene scene = new Scene(elementHolder, windowWidth, windowHeight);
        scene.setFill(Color.OLDLACE);

        stage.setScene(scene);
        stage.setFullScreen(windowFullscreen);
        stage.show();
    }
}
