package a307a.program.GUI.MenuBar;

import a307a.Exceptions.InvalidInputException;
import a307a.program.GUI.GraphicsManager;
import a307a.program.GUI.Splits.FileList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//All methods are static as they only contain code for executing specific tasks.
//Furthermore, this class does not house any values that would be useful to have in
// instances of this class.
public class FileListEditor {
    private Stage stage = new Stage();
    private Text minFlavorText = new Text("From file number:");
    private Text maxFlavorText = new Text("to");
    private Text checkboxFlavorText = new Text("Remove from:");
    private ToggleGroup listSelector = new ToggleGroup();
    private RadioButton src = new RadioButton("Source file list");
    private RadioButton comp = new RadioButton("Comparison file list");
    private TextField minFileNumber = new TextField();
    private TextField maxFileNumber = new TextField();
    private Button listCutter = new Button("Remove from list");
    private GridPane gridPane = new GridPane();
    private FileList fileList;

    public FileListEditor(List<MidiFile> srcMidiFiles, List<MidiFile> compMidiFiles, GraphicsManager graphicsManager) {
        this.fileList = graphicsManager.getFileList();
        initializeRadioButtons();
        listCutter.setOnAction(event -> {
            remove(srcMidiFiles, compMidiFiles, graphicsManager.getElementHolder());
        });
        setElementPositions();
        createWindow();
    }

    //This window for removing files was added as an alternative to having a removal button next to each individual file entry.
    //The feature for having individual buttons for each file entry may be readded in the future.
    private void remove(List<MidiFile> srcMidiFiles, List<MidiFile> compMidiFiles, BorderPane elementHolder) {
        try {
            if (src.isSelected()) {
                RemoveSelectedFile(minFileNumber, maxFileNumber, srcMidiFiles);
                elementHolder.setCenter(fileList.ListsOfFiles(srcMidiFiles, compMidiFiles));
            } else if (comp.isSelected()) {
                RemoveSelectedFile(minFileNumber, maxFileNumber, compMidiFiles);
                elementHolder.setCenter(fileList.ListsOfFiles(srcMidiFiles, compMidiFiles));
            } else {
                ErrorWindow("Please select a list to remove files from.");
            }
        } catch (InvalidInputException e) {
            ErrorWindow("Invalid input detected. Please ensure that:" +
                    "               \n* None of the fields are empty." +
                    "               \n* No negative numbers have been inserted." +
                    "               \n* The index number of the first file is not lower then the number of the last file." +
                    "               \n* None of the inputs are larger than the size of the file list.");
        }
        ;
    }

    //The code for removing files is placed in its own method as it's used multiple times.
    private static void RemoveSelectedFile(TextField minFileNumber, TextField maxFileNumber, List<MidiFile> fileList) throws InvalidInputException {
        int minNumber = Integer.parseInt(minFileNumber.getText()),
                maxNumber = Integer.parseInt(maxFileNumber.getText()),
                numberOfFilesRemoved = maxNumber - minNumber;

        if (maxFileNumber.getText().trim().isEmpty()) {
            maxNumber = minNumber;
        }
        if (minFileNumber.getText().trim().isEmpty()) {
            throw new InvalidInputException();
        } else if ((minNumber < 0) || (maxNumber < 0)) {
            throw new InvalidInputException();
        } else if (minNumber > maxNumber) {
            throw new InvalidInputException();
        } else if ((minNumber > fileList.size()) || maxNumber > fileList.size()) {
            throw new InvalidInputException();
        }
        for (int x = maxNumber; x >= minNumber; --x) {
            fileList.remove(x);
        }

    }

    //AddFile was made to reduce instances of code in MainGUI
    public static List<File> AddFile() {
        Stage srcFile = new Stage();

        FileChooser browseSourceFile = new FileChooser();
        browseSourceFile.setTitle("Select a file to be added");

        //Stopper folk fra at vælge filer vi ikke kan arbejde med.
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "MIDI (*.mid)",
                "*.mid"
        );
        browseSourceFile.getExtensionFilters()
                .add(filter);

        return browseSourceFile.showOpenMultipleDialog(srcFile);
    }

    //An error window to tell the user when they fuck up in the removal process.
    private void ErrorWindow(String errorMessage) {
        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        Text message = new Text(errorMessage);
        pane.setCenter(message);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initializeRadioButtons() {
        src.setToggleGroup(listSelector);
        comp.setToggleGroup(listSelector);
    }

    private void setElementPositions() {
        minFileNumber.setAlignment(Pos.CENTER);
        maxFileNumber.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(3);
        gridPane.add(minFlavorText, 0, 0);
        gridPane.add(minFileNumber, 1, 0);
        gridPane.add(maxFlavorText, 2, 0);
        gridPane.add(maxFileNumber, 3, 0);
        gridPane.add(listCutter, 4, 0);
        gridPane.add(checkboxFlavorText, 0, 1);
        gridPane.add(src, 1, 1);
        gridPane.add(comp, 3, 1);
    }

    private void createWindow() {
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
