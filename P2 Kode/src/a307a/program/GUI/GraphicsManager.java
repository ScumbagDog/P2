package a307a.program.GUI;

import a307a.program.GUI.MenuBar.*;
import a307a.program.GUI.Splits.FileList;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.File;
import java.util.List;

public class GraphicsManager {
    private BorderPane elementHolder;
    private VBox algorithmVBox;
    private StackPane algorithmStack;
    private Text infoText;
    private FileList fileList;
    private FileStorage selectedFiles;
    private SplitPane resultSplit;
    private AccessBar accessBar;

    public GraphicsManager() {
        elementHolder = new BorderPane();
        algorithmVBox = new VBox();
        algorithmStack = new StackPane(algorithmVBox);
        infoText = new Text("Awaiting action...");
        fileList = new FileList(this);
        selectedFiles = new FileStorage();
        resultSplit = new SplitPane();
        accessBar = new AccessBar(fileList);
    }

    public void loadFile(List<File> listOfFiles, List<MidiFile> listOfMidis) {
        listOfFiles.addAll(FileListEditor.addFile());
        selectedFiles.initiateMidiFileList(listOfFiles, listOfMidis);
        infoText.setText("File \"" + listOfMidis.get(listOfMidis.size() - 1)
                .getFilePath()
                .getName() + "\" has been added!");
        elementHolder.setCenter(fileList.listsOfFiles(
                selectedFiles.getSrcMidiFiles(),
                selectedFiles.getCompMidiFiles()
        ));
    }

    public void updateDisplay() {
        elementHolder.setTop(accessBar.getMenuBar());
        elementHolder.setLeft(algorithmStack);
        elementHolder.setBottom(infoText);
        elementHolder.setCenter(fileList.listsOfFiles(
                selectedFiles.getSrcMidiFiles(),
                selectedFiles.getCompMidiFiles()
        ));
        elementHolder.setRight(resultSplit);
    }

    public void loadAlgorithms(List<SelectableAlgorithm> listOfAlgorithms) {
        for (SelectableAlgorithm listOfAlgorithm : listOfAlgorithms) {
            BorderPane algorithmBP = new BorderPane();
            algorithmBP.setLeft(listOfAlgorithm.getBox());
            algorithmBP.setRight(listOfAlgorithm.getTextField());

            algorithmVBox.getChildren().add(algorithmBP);
        }
    }

    public BorderPane getElementHolder() {
        return elementHolder;
    }

    public FileStorage getSelectedFiles() {
        return selectedFiles;
    }

    public SplitPane getResultSplit() {
        return resultSplit;
    }

    public AccessBar getAccessBar() {
        return accessBar;
    }

    public FileList getFileList() {
        return fileList;
    }


}
