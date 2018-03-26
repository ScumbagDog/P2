package a307a.program.GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsMenu {
    public static void WindowSettings(String version){
        Stage stage = new Stage();
        stage.setTitle("Settings");
        BorderPane generalPane = new BorderPane();
        BorderPane versionPane = new BorderPane();
        generalPane.setBottom(versionPane);

            GridPane dimensions = new GridPane();
                Text        dimensionWidthText = new Text("Width"),
                            dimensionHeightText = new Text("Height");
                TextField   dimensionWidth = new TextField(),
                            dimensionHeight = new TextField();
                Button      saveSettings = new Button("Save"),
                            clearSettings = new Button("Reset Settings");
                dimensions.getChildren().addAll(dimensionHeightText,
                                                dimensionWidthText,
                                                dimensionHeight,
                                                dimensionWidth,
                                                saveSettings,
                                                clearSettings);

        Text versionInfo = new Text(version);
        versionPane.setRight(versionInfo);

        Scene scene = new Scene(generalPane, 500, 500);
        stage.setScene(scene);

        stage.show();
    }
}
