package a307a.program.GUI;

import a307a.algorithm.*;
import a307a.midilib.parser.AMelody;
import a307a.midilib.parser.AMidiSequenceReader;
import a307a.midilib.parser.MidiTools;
import a307a.program.GUI.MenuBar.FileListEditor;
import a307a.program.GUI.MenuBar.MidiFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.midi.InvalidMidiDataException;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ResultList {
    private ObservableList<DataResult> data = FXCollections.observableArrayList();
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

    public void addTableEntry(String compInformation, double result) {
        data.add(new DataResult(compInformation, result));
        table.setItems(data);
    }

    public void adjustListWidth(double windowWidth) {
        table.setPrefWidth(windowWidth / 3);
    }

    public ResultList(GraphicsManager graphicsManager, List<SelectableAlgorithm> listOfAlgorithms) {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.listOfAlgorithms = listOfAlgorithms;

        fileName1.setCellValueFactory(new PropertyValueFactory<DataResult, String>
                ("fileName"));
        result.setCellValueFactory(new PropertyValueFactory<DataResult, String>
                ("resultValue"));

        table.getColumns()
                .addAll(fileName1, result);

        this.graphicsManager = graphicsManager;
        initiateRightPane();
        setCompareButtonFunctionality();
        saveResultsToFile();
    }

    private void initiateRightPane() {
        graphicsManager.getResultSplit()
                .setOrientation(Orientation.VERTICAL);
        graphicsManager.getResultSplit()
                .setDividerPositions(0.9);
        resultStack2.setMaxSize(100, 100);
        graphicsManager.getResultSplit()
                .getItems()
                .addAll(resultStack1, resultStack2);
    }

    private void setCompareButtonFunctionality() {
        compareMelodies.setStyle("-fx-font-size: 10pt;");
        compareMelodies.setText("Compare");
        compareMelodies.setOnAction(event -> {
            openConfirmationWindow();
        });
    }

    private String setConfirmationText() {
        return "A total of "
                + graphicsManager.getSelectedFiles()
                .getSrcMidiFiles()
                .size()
                + " source files and "
                + graphicsManager.getSelectedFiles()
                .getCompMidiFiles()
                .size()
                + " comparison files have been selected.\n"
                + "Do you want to begin the comparison sequence?";
    }

    private void openConfirmationWindow() {
        Stage confirmAction = new Stage();
        Text confirmationText = new Text(setConfirmationText());
        Button startComparison = new Button("Start");


        startComparison.setOnAction(event2 -> {
            executeAlgorithms();
            confirmAction.close();
        });

        Button cancelComparison = new Button("Cancel");
        cancelComparison.setOnAction(event3 -> {
            confirmAction.close();
        });

        BorderPane content = new BorderPane();
        content.setTop(confirmationText);
        content.setLeft(startComparison);
        content.setRight(cancelComparison);
        confirmAction.setScene(new Scene(content));
        confirmAction.show();
    }

    private void executeAlgorithms() {
        Comparison comparison = new Comparison(this,
                graphicsManager.getSelectedFiles()
                        .getSrcMidiFiles(),
                graphicsManager.getSelectedFiles()
                        .getCompMidiFiles());

        for (SelectableAlgorithm algorithm : listOfAlgorithms) {
            if (algorithm.getBox().isSelected()) {
                if (algorithm.getAlgorithm() instanceof AStatisticallyInformedAlgorithm) {
                    AStatisticallyInformedAlgorithm statisticallyInformedAlgorithm = (AStatisticallyInformedAlgorithm) algorithm.getAlgorithm();
                    try{setCollection(statisticallyInformedAlgorithm, Integer.parseInt(algorithm.getTextField().getText()));}
                    catch(InvalidMidiDataException | IOException e){ System.out.println("Invalid file loaded into the sequence reader");}
                }
                algorithm.getAlgorithm().setNGramMagnitude(Integer.parseInt(algorithm.getTextField().getText()));
                comparison.useAlgorithm(algorithm.getAlgorithm());
            }
        }
    }

    private void setCollection(AStatisticallyInformedAlgorithm statisticallyInformedAlgorithm, int magnitude) throws InvalidMidiDataException, IOException {
        List<MidiFile> referenceFiles = new ArrayList<>();
        List<File> preprocessedFiles = new ArrayList<>(FileListEditor.addFile());
        fileStorage.initiateMidiFileList(preprocessedFiles, referenceFiles);
        AMidiSequenceReader reader;
        List<AMelody> melodies = new ArrayList<>();
        for(MidiFile referenceFile : referenceFiles){
            reader = MidiTools.getMidiSequenceReader(referenceFile.getFilePath());
            for(int count = 0; count < referenceFile.getCheckBoxes().size(); ++count) {
                melodies.add(reader.getMelody(count));
            }
        }
        statisticallyInformedAlgorithm.setMelodyCollection(melodies, magnitude);
    }

    private void saveResultsToFile() {
        saveResults.setText("Save Results");
        saveResults.setOnAction(event -> {
            try {
                PrintWriter writer = new PrintWriter(getDate() + ".txt", "UTF-8");
                data.stream().forEach(dataResult -> writer.println(dataResult.getEntry()));
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("The file you made does not exist");
            } catch (UnsupportedEncodingException e) {
                System.out.println("Invalid encoding, fix it.");
            }
        });
    }

    private String getDate() {
        ZonedDateTime date = ZonedDateTime.now();
        String dash = "-";
        return Integer.toString(date.getHour())
                + dash + Integer.toString(date.getMinute())
                + dash + Integer.toString(date.getDayOfMonth())
                + dash + Integer.toString(date.getMonthValue())
                + dash + Integer.toString(date.getYear());
    }

    public TableView getTable() {
        return table;
    }

}

