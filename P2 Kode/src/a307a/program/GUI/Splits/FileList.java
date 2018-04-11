package a307a.program.GUI.Splits;

import a307a.program.GUI.Splits.CenterSplits;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.util.List;


public class FileList{

    public static BorderPane ListsOfFiles(List<File> srcFiles, List<File> cmpFiles) {
        BorderPane centralLayout = new BorderPane();

        CenterSplits listSplit = new CenterSplits();

        centralLayout.setCenter(listSplit.MakeSplit(srcFiles, cmpFiles));
        centralLayout.setTop(listSplit.MakeSplit());

        return centralLayout;
    }
}

