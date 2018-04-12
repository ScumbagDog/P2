package a307a.program.GUI.Splits;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileList{

    public static BorderPane ListsOfFiles(List<File> srcFiles, List<File> cmpFiles) {
        BorderPane centralLayout = new BorderPane();
        Text srcText = new Text("Source File");
        Text compText = new Text("Compare file");
        FileList fileList = new FileList();

        centralLayout.setCenter(SplitMaker(GridMaker(srcFiles), GridMaker(cmpFiles)));
        centralLayout.setTop(SplitMaker(srcText, compText));

        return centralLayout;
    }

    private static SplitPane SplitMaker(Node object1, Node object2){
        SplitPane splitPane = new SplitPane();
        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();
        splitPane.setDividerPositions(0.5);
        stackPane1.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));
        stackPane2.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));

        stackPane1.getChildren().add(object1);
        stackPane2.getChildren().add(object2);
        splitPane.getItems().addAll(stackPane1, stackPane2);
        return splitPane;
    }

    private static GridPane GridMaker(List<File> files){
        GridPane fileList = new GridPane();
        fileList.setGridLinesVisible(true);
        List<Text>  fileText = new ArrayList<>(),
                    fileIndex = new ArrayList<>();
        for(int count = 0; count < files.size(); ++count){
            fileText.add(new Text(files.get(count).getName()));
            fileList.add(fileText.get(count), 0, count);

            fileIndex.add(new Text(Integer.toString(count) ));
            fileList.add(fileIndex.get(count), 1, count);
        }

        return fileList;
    }
}

