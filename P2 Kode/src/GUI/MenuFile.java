package GUI;
/*import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javafx.application.Application; */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
/*import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; */
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuFile {

    public static void MainMenu(Stage stage) {
        stage.setTitle("Main Menu");
        int windowWidth = 400, windowHeight = 350;
        List<File> compFileList = new ArrayList<>();

        Text infoBar = new Text();
            infoBar.setTextAlignment(TextAlignment.CENTER);
            infoBar.setText("Awaiting action...");

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Files");
            MenuItem addFile = new MenuItem("Add");
                addFile.setOnAction(event -> {
                    Stage srcFile = new Stage();

                    FileChooser browseSourceFile = new FileChooser();
                    browseSourceFile.setTitle("Select a source file");
                    File filePath = browseSourceFile.showOpenDialog(srcFile);

                    infoBar.setText("File " + filePath.getName() + " has been added!");
                });
            menuFile.getItems().add(addFile);

        Menu menuSetting = new Menu("Settings");

        Menu menuCompare = new Menu("Compare");

        menuBar.getMenus().addAll(menuFile, menuCompare, menuSetting);

        BorderPane elementHolder = new BorderPane();
            //elementHolder.getChildren().add(infoBar);
                elementHolder.setBottom(infoBar);
                elementHolder.setTop(menuBar);
       // VBox menuHolder = new VBox();
         //   menuHolder.getChildren().addAll(menuBar, elementHolder);


        Scene scene = new Scene(elementHolder, 400, 350);
        scene.setFill(Color.OLDLACE);

        stage.setScene(scene);
        stage.show();
    }
}
