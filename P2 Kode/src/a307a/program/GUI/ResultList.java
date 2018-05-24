package a307a.program.GUI;

import a307a.algorithm.AStatisticallyInformedAlgorithm;
import a307a.algorithm.IAlgorithm;
import a307a.midilib.parser.*;
import a307a.program.GUI.MenuBar.FileListEditor;
import a307a.program.GUI.MenuBar.MidiFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.midi.InvalidMidiDataException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ResultList{
	private ObservableList<DataResult> data
			= FXCollections.observableArrayList();
	private TableView table = new TableView();
	private TableColumn fileName1 = new TableColumn("Files");
	private TableColumn result = new TableColumn("Result");
	private Button compareMelodies = new Button();
	private Button saveResults = new Button();
	private HBox buttons = new HBox(compareMelodies, saveResults);
	private StackPane resultStack1 = new StackPane(this.getTable());
	private StackPane resultStack2 = new StackPane(buttons);
	private GraphicsManager graphicsManager;
	private List<SelectableAlgorithm> listOfAlgorithms;
	private FileStorage fileStorage = new FileStorage();

	public ResultList(
			GraphicsManager graphicsManager,
			List<SelectableAlgorithm> listOfAlgorithms
	){
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.listOfAlgorithms = listOfAlgorithms;

		fileName1.setCellValueFactory(new PropertyValueFactory<DataResult,
				String>(
				"fileName"));
		result.setCellValueFactory(new PropertyValueFactory<DataResult,
				String>(
				"resultValue"));

		table.getColumns()
				.addAll(fileName1, result);

		this.graphicsManager = graphicsManager;
		initiateRightPane();
		setCompareButtonFunctionality();
		saveResultsToFile();
	}

	public void addTableEntry(String compInformation, double result){
		data.add(new DataResult(compInformation, result));
		table.setItems(data);
	}

	public void adjustListWidth(double windowWidth){
		table.setPrefWidth(windowWidth / 3);
	}

	private void initiateRightPane(){
		graphicsManager.getResultSplit()
				.setOrientation(Orientation.VERTICAL);
		graphicsManager.getResultSplit()
				.setDividerPositions(0.9);

		resultStack2.setMaxHeight(1);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(20);
		graphicsManager.getResultSplit()
				.getItems()
				.addAll(resultStack1, resultStack2);
	}

	private void setCompareButtonFunctionality(){
		compareMelodies.setStyle("-fx-font-size: 10pt;");
		compareMelodies.setText("Compare");
		compareMelodies.setOnAction(event->{
			openConfirmationWindow();
		});
	}

	private String setConfirmationText(){
		return "A total of " + graphicsManager.getSelectedFiles()
				.getSrcMidiFiles()
				.size() + " source files and " + graphicsManager
				.getSelectedFiles()
				.getCompMidiFiles()
				.size() + " comparison files have been selected.\n"
				+ "Do you want to begin the comparison sequence?";
	}

	private void openConfirmationWindow(){
		Stage confirmAction = new Stage();
		Text confirmationText = new Text(setConfirmationText());
		Button startComparison = new Button("Start");

		startComparison.setOnAction(event2->{
			executeAlgorithms();
			confirmAction.close();
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
	}

	private void executeAlgorithms(){
		Comparison comparison = new Comparison(this,
				graphicsManager.getSelectedFiles()
						.getSrcMidiFiles(),
				graphicsManager.getSelectedFiles()
						.getCompMidiFiles());

		/* Go through every algorithm's check box and see if it is checked. */
		for(SelectableAlgorithm algorithm : listOfAlgorithms){
			if(algorithm.getBox()
					.isSelected()){

				/* If the selected algorithm is statistically informed, get the
				 * user to set its dataset. */
				IAlgorithm executableAlgorithm = algorithm.getAlgorithm();
				if(executableAlgorithm instanceof AStatisticallyInformedAlgorithm)
					setCollectionForAlgorithm((AStatisticallyInformedAlgorithm)
							executableAlgorithm);

				/* Execute and display the result of the comparisons. */
				executableAlgorithm.setNGramMagnitude(Integer.parseInt(algorithm
						.getTextField()
						.getText()));
				comparison.useAlgorithm(executableAlgorithm);
			}
		}
	}

	private void setCollectionForAlgorithm(
			AStatisticallyInformedAlgorithm algorithm
	){
		midiReferenceNotificationWindow();
		try{
			setCollection(algorithm);
		}catch(InvalidMidiDataException | IOException e){
			System.out.println(
					"Invalid file loaded into the sequence " + "reader");
		}
	}

	private void setCollection(
			AStatisticallyInformedAlgorithm statisticallyInformedAlgorithm
	) throws InvalidMidiDataException, IOException{
		List<MidiFile> referenceFiles = new ArrayList<>();
		List<File> preprocessedFiles
				= new ArrayList<>(FileListEditor.addFile());
		fileStorage.initiateMidiFileList(preprocessedFiles, referenceFiles);
		AMidiSequenceReader reader;
		List<AMelody> melodies = new ArrayList<>();
		for(MidiFile referenceFile : referenceFiles){
			reader
					= MidiTools.getMidiSequenceReader(referenceFile.getFilePath());
			for(int count = 0;
					count < referenceFile.getCheckBoxes()
							.size();
					++count){
				melodies.add(reader.getMelody(count));
			}
		}
		statisticallyInformedAlgorithm.setMelodyCollection(melodies);
	}

	private void midiReferenceNotificationWindow(){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(
				"You have selected an algorithm that needs to be statistically "
						+ "informed of which "
						+ "types of melodies are common and which types are not."
						+ "\n\nFor this purpose, you will now be allowed to select as"
						+ " many MIDI files as you feel necessary."
						+ "\n\nDo not be alarmed if this message appears multiple "
						+ "times, as that just means you've selected multiple "
						+ "algorithms that require these statistics.");
		alert.setHeaderText("Please read:");
		alert.setTitle("Statistically informed algorithms need statistics.");
		alert.showAndWait();
	}

	private void saveResultsToFile(){
		saveResults.setText("Save Results");
		saveResults.setStyle("-fx-font-size: 10pt;");
		saveResults.setOnAction(event->{
			try{
                FileChooser pathFinder = new FileChooser();
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text (.txt)", ".txt");
                pathFinder.getExtensionFilters().add(filter);
                String fileName = pathFinder.showSaveDialog(new Stage()).toString();
				PrintWriter writer = new PrintWriter(fileName, "UTF-8");
				data.forEach(dataResult->writer.println(dataResult.getEntry()));
				writer.close();
			}catch(FileNotFoundException e){
				System.err.println("File made does not exist");
			}catch(UnsupportedEncodingException e){
				System.err.println("Invalid encoding, fix it.");
			}
		});
	}

	public TableView getTable(){
		return table;
	}

}

