package a307a.program.GUI.Splits;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CenterSplits {

    public static SplitPane MakeSplit(){
        SplitPane splitPane = new SplitPane();
        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();
        splitPane.setDividerPositions(0.5);
        stackPane1.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));
        stackPane2.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));

        Text text = new Text("Source File");
        Text text2 = new Text("Compare file");
        stackPane1.getChildren().add(text);
        stackPane2.getChildren().add(text2);

        splitPane.getItems().addAll(stackPane1, stackPane2);
        return splitPane;
    }

    public static SplitPane MakeSplit(List<File> srcFiles, List<File> cmpFiles){
        SplitPane splitPane = new SplitPane();
        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();
        splitPane.setDividerPositions(0.5);
        stackPane1.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));
        stackPane2.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.49));

        stackPane1.getChildren().add(CenterSplits.FileListing(srcFiles, srcFiles, cmpFiles));
        stackPane2.getChildren().add(CenterSplits.FileListing(cmpFiles, srcFiles, cmpFiles));

        splitPane.getItems().addAll(stackPane1, stackPane2);
        return splitPane;
    }

    private static GridPane FileListing(List<File> files, List<File> srcFiles, List<File> cmpFiles){
        GridPane fileList = new GridPane();
        fileList.setGridLinesVisible(true);
        fileList.setHgap(5);
        fileList.setVgap(5);
        List<Text> fileText = new ArrayList<>();
        for(int count = 0; count < files.size(); ++count){
            fileText.add(new Text(files.get(count).getName()));
            fileList.add(fileText.get(count), 0, count);
            fileList.add(FileRemoval.Button(files, files.get(count), fileText, fileText.get(count)), 1, count);
        }

        return fileList;
    }


}
