package a307a.program.GUI.Splits;

import a307a.program.GUI.GraphicsManager;
import a307a.program.GUI.MenuBar.MidiFile;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;

//All methods are static as they only contain code for executing specific tasks.
//Furthermore, this class does not house any values that would be useful to have in
// instances of this class.
public class FileList {
    private GraphicsManager graphicsManager;

    //This method only exists to collect the layouts made by the private methods of this
    // class.
    public BorderPane listsOfFiles(List<MidiFile> srcFiles, List<MidiFile>
            cmpFiles) {
        BorderPane centralLayout = new BorderPane();
        Text srcText = new Text("Source File");
        Text compText = new Text("Compare file");

        centralLayout.setCenter(arrangeLists(gridMaker(srcFiles), gridMaker(cmpFiles)));
        centralLayout.setTop(listDenoter(srcText, compText));

        return centralLayout;
    }

    //All private methods below this comment exist to heighten the abstraction level of
    // listsOfFiles.
    private SplitPane listDenoter(Node object1, Node object2) {
        SplitPane splitPane = new SplitPane();
        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();
        splitPane.setDividerPositions(0.5);
        stackPane1.maxWidthProperty()
                .bind(splitPane.widthProperty()
                        .multiply(0.49));
        stackPane2.maxWidthProperty()
                .bind(splitPane.widthProperty()
                        .multiply(0.49));

        stackPane1.getChildren()
                .addAll(object1);
        stackPane2.getChildren()
                .addAll(object2);
        splitPane.getItems()
                .addAll(stackPane1, stackPane2);
        return splitPane;
    }

    private SplitPane arrangeLists(Node object1, Node object2) {
        SplitPane splitPane2 = new SplitPane();
        ScrollPane scrollPane1 = new ScrollPane();
        ScrollPane scrollPane2 = new ScrollPane();
        scrollPane1.maxWidthProperty()
                .bind(splitPane2.widthProperty()
                        .multiply(0.49));
        scrollPane2.maxWidthProperty()
                .bind(splitPane2.widthProperty()
                        .multiply(0.49));

        scrollPane1.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane2.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane1.setContent(object1);
        scrollPane2.setContent(object2);
        splitPane2.getItems()
                .addAll(scrollPane1, scrollPane2);
        return splitPane2;

    }

    //
    private GridPane gridMaker(List<MidiFile> files) {
        GridPane fileList = new GridPane();
        fileList.setGridLinesVisible(true);
        for (int count = 0; count < files.size(); ++count) {
            fileList.add(createFileEntry(files, count), 0, count);
            fileList.add(new Text(Integer.toString(count)), 1, count);
            fileList.add(buttonDeleteFileEntry(count, files), 2, count);
        }

        return fileList;
    }

    private VBox createFileEntry(List<MidiFile> files, int passedCount) {
        VBox entry = new VBox();
        entry.getChildren()
                .add(new Text(files.get(passedCount)
                        .getFilePath()
                        .getName()));
        for (int count = 0;
             count < files.get(passedCount)
                     .getCheckBoxes()
                     .size();
             ++count
                ) {
            entry.getChildren()
                    .add(files.get(passedCount)
                            .getCheckBoxes()
                            .get(count));
        }
        return entry;
    }

    private Button buttonDeleteFileEntry(int entry, List<MidiFile> files) {
        Button button = new Button("Remove");
        button.setOnAction(event -> {
            files.remove(entry);
            graphicsManager.updateDisplay();
        });
        return button;
    }

    public FileList(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
    }
}

