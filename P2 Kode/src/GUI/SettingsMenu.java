package GUI;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsMenu {
    public static void WindowSettings(String version){
        Stage stage = new Stage();
        stage.setTitle("Settings");
        BorderPane generalPane = new BorderPane();
        BorderPane versionPane = new BorderPane();
        generalPane.setBottom(versionPane);

        Text versionInfo = new Text(version);
        versionPane.setRight(versionInfo);

    }
}
