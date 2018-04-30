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
    private VBox algorithms = new VBox();
    private int windowHeight, windowWidth;
    private Boolean windowFullscreen;
    private List<File> srcFileList = new ArrayList<>(),
            compFileList = new ArrayList<>();
    private List<CheckBox> listOfAlgorithms = AlgorithmList.listAlgorithm();
    private List<MidiFile> srcMidiFiles = new ArrayList<>();
    private List<MidiFile> compMidiFiles = new ArrayList<>();
    private Text fileName = new Text("Awaiting action...");
    private BorderPane elementHolder = new BorderPane();
    private FileList splitLists = new FileList();
    private MenuBar menuBar = new MenuBar();
    private Menu menuFile = new Menu("Files");
    private MenuItem addSrcFile = new MenuItem("Add Source");
    private MenuItem removeFile = new MenuItem("Remove");
    private MenuItem addCompFile = new MenuItem("Add Comparison");
    private Menu menuSetting = new Menu("Window");
    private MenuItem addSetting = new MenuItem("Settings");
    private Button compareMelodies = new Button();
    private Resultlist resultlist = new Resultlist();
    private StackPane resultStack1 = new StackPane(resultlist.getTable());
    private StackPane resultStack2 = new StackPane(compareMelodies);
    private SplitPane resultSplit = new SplitPane();
    private StackPane algorithmStack = new StackPane(algorithms);
    private Comparison comparison = new Comparison();

    //Essentially the 'main' method of JavaFX.
    @Override
    public void start(Stage primaryStage) {
        stage.setTitle("Main Menu");

        for(int i = 0; i < listOfAlgorithms.size(); ++i){
            algorithms.getChildren().add(listOfAlgorithms.get(i));
        }

        loadSettings();
        setFileMenuItemFunctionality();
        setSettingsMenuItemFunctionality();
        menuBar.getMenus().addAll(menuFile, menuSetting);
        setCompareButtonFunctionality();

        resultSplit.setOrientation(Orientation.VERTICAL);
        resultSplit.setDividerPositions(0.9);
        resultStack2.setMaxSize(100, 100);
        resultSplit.getItems().addAll(resultStack1, resultStack2);

        updateDisplay();

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

    private void updateDisplay(){
        elementHolder.setTop(menuBar);
        elementHolder.setLeft(algorithmStack);
        elementHolder.setBottom(fileName);
        elementHolder.setCenter(splitLists.ListsOfFiles(srcMidiFiles, compMidiFiles));
        elementHolder.setRight(resultSplit);
    }

    private void loadSettings(){
        windowWidth = Integer.parseInt(SettingsFile.AccessSettings("width"));
        windowHeight = Integer.parseInt(SettingsFile.AccessSettings("height"));
        windowFullscreen = Boolean.parseBoolean(SettingsFile.AccessSettings("fullscreen"));
    }

    private void setFileMenuItemFunctionality(){
        addSrcFile.setOnAction(event -> { loadFile(srcFileList, srcMidiFiles); });
        addCompFile.setOnAction(event -> { loadFile(compFileList, compMidiFiles); });
        removeFile.setOnAction(event -> { new FileListEditor(srcMidiFiles, compMidiFiles, elementHolder); });
        menuFile.getItems().addAll(addSrcFile, addCompFile, removeFile);
    }

    private void setSettingsMenuItemFunctionality(){
        addSetting.setOnAction(event -> {
            String version = "???";
            SettingsMenu.WindowSettings(version);
        });
        menuSetting.getItems().add(addSetting);
    }

    private void setCompareButtonFunctionality(){
        compareMelodies.setStyle("-fx-font-size: 10pt;");
        compareMelodies.setText("Compare");
        compareMelodies.setOnAction(event -> {
            Stage confirmAction = new Stage();
            Text confirmationText = new Text("A total of " + srcMidiFiles.size() + " source files and "
                    + compMidiFiles.size() + " comparison files have been selected.\n" +
                    "Do you want to begin the comparison sequence?");
            Button startComparison = new Button("Start");
            startComparison.setOnAction(event2 -> {comparison.useUkonnen(resultlist, srcMidiFiles, compMidiFiles);});
            Button cancelComparison = new Button("Cancel");
            cancelComparison.setOnAction(event3 -> {confirmAction.close();});
            BorderPane content = new BorderPane();
            content.setTop(confirmationText);
            content.setLeft(startComparison);
            content.setRight(cancelComparison);
            confirmAction.setScene(new Scene(content));
            confirmAction.show();
        });
    }
}
