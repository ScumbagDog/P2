package GUI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuFile {

    public static void MainMenu(Stage stage) {
        stage.setTitle("Main Menu");
        List<File> compFileList = new ArrayList<>();

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Files");
            MenuItem addFile = new MenuItem("Add");
                addFile.setOnAction(event -> {
                    Stage srcFile = new Stage();

                    FileChooser browseSourceFile = new FileChooser();
                    browseSourceFile.setTitle("Select a source file");
                    File filePath = browseSourceFile.showOpenDialog(srcFile);

                    Text fileName = new Text();
                    fileName.setText("File " + filePath.getName() + " has been added!");
                });
            menuFile.getItems().add(addFile);

        Menu menuSetting = new Menu("Settings");

        Menu menuCompare = new Menu("Compare");

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

        menuBar.getMenus().addAll(menuFile, menuCompare, menuAlgorithm, menuSetting);

        VBox elementHolder = new VBox();
        elementHolder.getChildren().addAll(menuBar);
        Scene scene = new Scene(elementHolder, 400, 350);
        scene.setFill(Color.OLDLACE);

        stage.setScene(scene);
        stage.show();
    }
}
