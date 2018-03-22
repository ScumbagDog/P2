package GUI;
/*import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javafx.application.Application; */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
/*import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; */
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuFile {

    public static void MainMenu(Stage stage) {
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 400, 350);
        scene.setFill(Color.OLDLACE);

        MenuBar menuBar = new MenuBar();


        Menu menuFile = new Menu("Files");
            MenuItem addFile = new MenuItem("Add");
                addFile.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage srcFile = new Stage();
                        srcFile.setTitle("Select source file");
                        Group ass = new Group();
                        Scene fileScene = new Scene(ass, 50, 600);
                        srcFile.show();
                    }
                });
            menuFile.getItems().add(addFile);

        Menu menuSetting = new Menu("Settings");

        Menu menuCompare = new Menu("Compare");


        menuBar.getMenus().addAll(menuFile, menuCompare, menuSetting);


        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);

        stage.setScene(scene);
        stage.show();
    }
}
