package a307a.program.GUI;

import a307a.program.GUI.MenuBar.settings.SettingsFile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

//This class exists solely to house the 'start' method
public class MainGUI extends Application {
    private Stage stage = new Stage();
    private SettingsFile settings = new SettingsFile();
    private List<CheckBox> listOfAlgorithms = AlgorithmList.listAlgorithm();
    private GraphicsManager graphicsManager = new GraphicsManager();
    private ResultList resultList = new ResultList(graphicsManager, settings.getWindowWidth());


    //Essentially the 'main' method of JavaFX.
    @Override
    public void start(Stage primaryStage) {
        stage.setTitle("Main Menu");
        graphicsManager.loadAlgorithms(listOfAlgorithms);
        graphicsManager.getAccessBar().setFileMenuItemFunctionality(
                graphicsManager.getSelectedFiles(),
                graphicsManager
        );

        graphicsManager.updateDisplay();
        Scene scene = new Scene(
                graphicsManager.getElementHolder(),
                settings.getWindowWidth(),
                settings.getWindowHeight()
        );
        scene.setFill(Color.OLDLACE);

        stage.setScene(scene);
        stage.setFullScreen(settings.getIsWindowFullscreen());
        stage.widthProperty().addListener(((observable, oldValue, newValue) -> {
            resultList.adjustListWidth((Double) newValue);
        }));
        stage.show();
    }
}