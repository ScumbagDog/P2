package a307a.program.GUI;

import javafx.geometry.Pos;
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
                dimensions.getChildren().addAll(dimensionWidthText,
                                                dimensionHeightText,
                                                dimensionWidth,
                                                dimensionHeight,
                                                saveSettings,
                                                clearSettings);

                dimensions.setConstraints(dimensionWidthText, 0, 0);
                dimensions.setConstraints(dimensionHeightText, 0, 1);
                dimensions.setConstraints(dimensionWidth, 1, 0);
                dimensions.setConstraints(dimensionHeight, 1, 1);
                dimensions.setConstraints(saveSettings, 2, 0);
                dimensions.setConstraints(clearSettings, 2, 1);
                dimensions.setAlignment(Pos.BASELINE_CENTER);
            generalPane.setCenter(dimensions);

        Text versionInfo = new Text(version);
        versionPane.setRight(versionInfo);

        Scene scene = new Scene(generalPane, 500, 500);
        stage.setScene(scene);

        stage.show();
    }
}
