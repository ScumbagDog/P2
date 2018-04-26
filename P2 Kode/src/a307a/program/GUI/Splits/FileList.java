package a307a.program.GUI.Splits;

import a307a.program.GUI.MenuBar.MidiFile;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//All methods are static as they only contain code for executing specific tasks.
//Furthermore, this class does not house any values that would be useful to have in instances of this class.
public class FileList{

    //This method only exists to collect the layouts made by the private methods of this class.
    public static BorderPane ListsOfFiles(List<MidiFile> srcFiles, List<MidiFile> cmpFiles) {
        BorderPane centralLayout = new BorderPane();
        Text srcText = new Text("Source File");
        Text compText = new Text("Compare file");

        centralLayout.setCenter(SplitMaker2(gridMaker(srcFiles), gridMaker(cmpFiles)));
        centralLayout.setTop(SplitMaker(srcText, compText));

        return centralLayout;
    }

    //All private methods below this comment exist to heighten the abstraction level of listsOfFiles.
    private static SplitPane SplitMaker(Node object1, Node object2){
        SplitPane splitPane = new SplitPane();
        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();
        splitPane.setDividerPositions(0.5);
        stackPane1.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));
        stackPane2.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));

        stackPane1.getChildren().addAll(object1);
        stackPane2.getChildren().addAll(object2);
        splitPane.getItems().addAll(stackPane1, stackPane2);
        return splitPane;
    }

    private static SplitPane SplitMaker2(Node object1, Node object2) {
        SplitPane splitPane2 = new SplitPane();
        ScrollPane scrollPane1 = new ScrollPane();
        ScrollPane scrollPane2 = new ScrollPane();
        scrollPane1.maxWidthProperty().bind(splitPane2.widthProperty().multiply(0.49));
        scrollPane2.maxWidthProperty().bind(splitPane2.widthProperty().multiply(0.49));

        scrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane1.setContent(object1);
        scrollPane2.setContent(object2);
        splitPane2.getItems().addAll(scrollPane1, scrollPane2);
        return splitPane2;

    }

    //
    private static GridPane gridMaker(List<MidiFile> files){
        GridPane fileList = new GridPane();
        fileList.setGridLinesVisible(true);
        List<Text>  fileText = new ArrayList<>(),
                    fileIndex = new ArrayList<>();
        for(int count = 0; count < files.size(); ++count){
            fileList.add(createFileEntry(files, count), 0, count);
            fileIndex.add(new Text(Integer.toString(count) ));
            fileList.add(fileIndex.get(count), 1, count);
        }

        return fileList;
    }

    private static VBox createFileEntry(List<MidiFile> files, int passedCount){
        VBox entry = new VBox();
        entry.getChildren().add(new Text(files.get(passedCount).getFilePath().getName()));
        for(int count = 0; count < files.get(passedCount).getCheckBoxes().size(); ++count){
            entry.getChildren().add(files.get(passedCount).getCheckBoxes().get(count));
        }
        return entry;
    }

}

