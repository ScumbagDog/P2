package a307a.program.GUI.MenuBar.settings;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsMenu{

	//This method is needed to create the window users interact with to change the
	// settings.
	public static void WindowSettings(String version){
		Stage stage = new Stage();
		stage.setTitle("Settings");
		BorderPane generalPane = new BorderPane();
		BorderPane versionPane = new BorderPane();
		generalPane.setBottom(versionPane);

		GridPane dimensions = new GridPane();
		Text dimensionWidthText = new Text("Width"), dimensionHeightText = new Text
				("Height"),
				checkboxText = new Text("Fullscreen");
		TextField dimensionWidth = new TextField(), dimensionHeight = new TextField();
		CheckBox fullscrn = new CheckBox();
		Button saveSettings = new Button("Save"), clearSettings
				= new Button("Reset Settings");
		dimensions.getChildren()
				.addAll(dimensionWidthText,
						dimensionHeightText,
						checkboxText,
						dimensionWidth,
						dimensionHeight,
						fullscrn,
						saveSettings,
						clearSettings
				);

		saveSettings.setOnAction(event->{
			SettingsFile fileEditor = new SettingsFile();
			fileEditor.EditSettings("width", dimensionWidth.getText());
			fileEditor.EditSettings("height", dimensionHeight.getText());
			if(fullscrn.isSelected())
				fileEditor.EditSettings("fullscreen", "true");
			else
				fileEditor.EditSettings("fullscreen", "false");
		});
		clearSettings.setOnAction(event->{
			SettingsFile fileEditor = new SettingsFile();
			fileEditor.EditSettings("width", "600");
			fileEditor.EditSettings("height", "600");
			fileEditor.EditSettings("fullscreen", "false");
		});

		dimensions.setConstraints(dimensionWidthText, 0, 0);
		dimensions.setConstraints(dimensionHeightText, 0, 1);
		dimensions.setConstraints(checkboxText, 0, 2);
		dimensions.setConstraints(dimensionWidth, 1, 0);
		dimensions.setConstraints(dimensionHeight, 1, 1);
		dimensions.setConstraints(fullscrn, 1, 2);
		dimensions.setConstraints(saveSettings, 2, 0);
		dimensions.setConstraints(clearSettings, 2, 1);
		dimensions.setAlignment(Pos.BASELINE_CENTER);
		generalPane.setCenter(dimensions);

		Text versionInfo = new Text(version);
		versionPane.setRight(versionInfo);

		Scene scene = new Scene(generalPane);
		stage.setScene(scene);

		stage.show();
	}
}
