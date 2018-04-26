package a307a.program.GUI;

import a307a.program.GUI.MenuBar.FileListEditor;
import a307a.program.GUI.MenuBar.MidiFile;
import a307a.program.GUI.MenuBar.settings.SettingsFile;
import a307a.program.GUI.MenuBar.settings.SettingsMenu;
import a307a.program.GUI.Splits.FileList;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This class exists solely to house the 'start' method
public class MainGUI extends Application {
    private Stage stage = new Stage();
    private int windowHeight, windowWidth;
    private Boolean windowFullscreen;
    private List<File> srcFileList = new ArrayList<>(),
            compFileList = new ArrayList<>();
    private List<CheckBox> listOfAlgorithms = AlgorithmList.ListAlgorithm();
    private List<MidiFile> srcMidiFiles = new ArrayList<>();
    private List<MidiFile> compMidiFiles = new ArrayList<>();
    private Text fileName = new Text("Awaiting action...");
    private BorderPane elementHolder = new BorderPane();
    private FileList splitLists = new FileList();

    //Essentially the 'main' method of JavaFX.
    @Override
    public void start(Stage primaryStage) {
        stage.setTitle("Main Menu");
        windowWidth = Integer.parseInt(SettingsFile.AccessSettings("width"));
        windowHeight = Integer.parseInt(SettingsFile.AccessSettings("height"));
        windowFullscreen = Boolean.parseBoolean(SettingsFile.AccessSettings("fullscreen"));

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Files");
        MenuItem addSrcFile = new MenuItem("Add Source");
        addSrcFile.setOnAction(event -> { loadFile(srcFileList, srcMidiFiles); });

        MenuItem addCompFile = new MenuItem("Add Comparison");
        addCompFile.setOnAction(event -> { loadFile(compFileList, compMidiFiles); });

        MenuItem removeFile = new MenuItem("Remove");
        removeFile.setOnAction(event -> { new FileListEditor(srcMidiFiles, compMidiFiles, elementHolder); });
        menuFile.getItems().addAll(addSrcFile, addCompFile, removeFile);

        Menu menuSetting = new Menu("Window");
        MenuItem addSetting = new MenuItem("Settings");
        addSetting.setOnAction(event -> {
            String version = "???";
            SettingsMenu.WindowSettings(version);
        });
        menuSetting.getItems().add(addSetting);

        menuBar.getMenus().addAll(menuFile, menuSetting);

        Button compareMelodies = new Button();
        compareMelodies.setStyle("-fx-font-size: 10pt;");
        compareMelodies.setText("Compare");
        compareMelodies.setOnAction(event -> {
            Stage confirmAction = new Stage();
            Text confirmationText = new Text("A total of " + srcFileList.size() + "source files and "
                    + compFileList.size() + "comparison files have been selected.\n" +
                    "Do you want to begin the comparison sequence?");
            Button startComparison = new Button();

            Button cancelComparison = new Button();
        });
        VBox algorithms = new VBox();
        for(int i = 0; i < listOfAlgorithms.size(); ++i){
            algorithms.getChildren().add(listOfAlgorithms.get(i));
        }
        StackPane algorithmStack = new StackPane(algorithms);

        SplitPane resultSplit = new SplitPane();
        resultSplit.setOrientation(Orientation.VERTICAL);
        resultSplit.setDividerPositions(0.9);
        StackPane resultStack1 = new StackPane(Resultlist.AddResultTable());
        StackPane resultStack2 = new StackPane(compareMelodies);
        resultStack2.setMaxSize(100, 100);
        resultSplit.getItems().addAll(resultStack1, resultStack2);

        elementHolder.setTop(menuBar);
        elementHolder.setLeft(algorithmStack);
        elementHolder.setBottom(fileName);
        elementHolder.setCenter(splitLists.ListsOfFiles(srcMidiFiles, compMidiFiles));
        elementHolder.setRight(resultSplit);

        Scene scene = new Scene(elementHolder, windowWidth, windowHeight);
        scene.setFill(Color.OLDLACE);

        stage.setScene(scene);
        stage.setFullScreen(windowFullscreen);
        stage.show();
    }

    private void loadFile(List<File> listOfFiles, List<MidiFile> listOfMidis){
        listOfFiles.addAll(FileListEditor.AddFile());
        initiateMidiFileList(listOfFiles, listOfMidis);
        fileName.setText("File \"" + listOfMidis.get(listOfMidis.size() - 1).getFilePath().getName() + "\" has been added!");
        elementHolder.setCenter(splitLists.ListsOfFiles(srcMidiFiles, compMidiFiles));
    }

    private void initiateMidiFileList(List<File> listOfFiles, List<MidiFile> listOfMidis){
        for(int count = 0; count < listOfFiles.size(); ++count){
            try {
                listOfMidis.add(new MidiFile(listOfFiles.get(count)));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listOfFiles.clear();
    }
}
