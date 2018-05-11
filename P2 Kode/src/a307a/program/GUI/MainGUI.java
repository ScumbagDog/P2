package a307a.program.GUI;

import a307a.program.GUI.MenuBar.AccessBar;
import a307a.program.GUI.MenuBar.settings.SettingsFile;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

//This class exists solely to house the 'start' method
public class MainGUI extends Application{
	private Stage stage = new Stage();
	private SettingsFile settings = new SettingsFile();
	private List<CheckBox> listOfAlgorithms = AlgorithmList.listAlgorithm();
	private AccessBar accessBar = new AccessBar();
	GraphicsManager graphicsManager = new GraphicsManager(accessBar);
	private Button compareMelodies = new Button();
	private Resultlist resultlist = new Resultlist();
	private StackPane resultStack1 = new StackPane(resultlist.getTable());
	private StackPane resultStack2 = new StackPane(compareMelodies);
	private Comparison comparison = new Comparison();

	//Essentially the 'main' method of JavaFX.
	@Override
	public void start(Stage primaryStage){
		stage.setTitle("Main Menu");
		graphicsManager.loadAlgorithms(listOfAlgorithms);
		accessBar.setFileMenuItemFunctionality(
				graphicsManager.getSelectedFiles(),
				graphicsManager
		);
		setCompareButtonFunctionality();
		initiateResultList();

		graphicsManager.updateDisplay();
		Scene scene = new Scene(
				graphicsManager.getElementHolder(),
				settings.getWindowWidth(),
				settings.getWindowHeight()
		);
		scene.setFill(Color.OLDLACE);

		stage.setScene(scene);
		stage.setFullScreen(settings.getWindowFullscreen());
		stage.show();
	}

	private void setCompareButtonFunctionality(){
		compareMelodies.setStyle("-fx-font-size: 10pt;");
		compareMelodies.setText("Compare");
		compareMelodies.setOnAction(event->{
			Stage confirmAction = new Stage();
			Text confirmationText = new Text("A total of "
					+ graphicsManager.getSelectedFiles()
					.getSrcMidiFiles()
					.size()
					+ " source files and "
					+ graphicsManager.getSelectedFiles()
					.getCompMidiFiles()
					.size()
					+ " comparison files have been selected.\n"
					+ "Do you want to begin the comparison sequence?");
			Button startComparison = new Button("Start");
			startComparison.setOnAction(event2->{
				comparison.useUkonnen(resultlist,
						graphicsManager.getSelectedFiles()
								.getSrcMidiFiles(),
						graphicsManager.getSelectedFiles()
								.getCompMidiFiles()
				);
			});
			Button cancelComparison = new Button("Cancel");
			cancelComparison.setOnAction(event3->{
				confirmAction.close();
			});
			BorderPane content = new BorderPane();
			content.setTop(confirmationText);
			content.setLeft(startComparison);
			content.setRight(cancelComparison);
			confirmAction.setScene(new Scene(content));
			confirmAction.show();
		});
	}

	private void initiateResultList(){
		graphicsManager.getResultSplit()
				.setOrientation(Orientation.VERTICAL);
		graphicsManager.getResultSplit()
				.setDividerPositions(0.9);
		resultStack2.setMaxSize(100, 100);
		graphicsManager.getResultSplit()
				.getItems()
				.addAll(resultStack1, resultStack2);
	}
}