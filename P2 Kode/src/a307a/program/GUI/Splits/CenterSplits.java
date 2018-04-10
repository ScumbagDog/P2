package a307a.program.GUI.Splits;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

        stackPane1.getChildren().add(CenterSplits.FileListing(srcFiles));
        stackPane2.getChildren().add(CenterSplits.FileListing(cmpFiles));

        splitPane.getItems().addAll(stackPane1, stackPane2);
        return splitPane;
    }

    public static VBox FileListing(List<File> files){
        VBox fileList = new VBox();
        List<Text> fileText = new ArrayList<>();
        for(int count = 0; count < files.size(); ++count){
            fileText.add(new Text(files.get(count).getName()));
            fileList.getChildren().add(fileText.get(count));
        }

        return fileList;
    }


}
