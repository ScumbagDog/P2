package a307a.program.GUI.MenuBar;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListEditor {
    static void Remove(List<File> srcFiles, List<File> compFiles){
        List<Integer> listOfFiles = new ArrayList<>();
        TextField minFileNumber = new TextField();
        TextField maxFileNumber = new TextField();
        Text minFlavorText = new Text("From file number:");
        Text maxFlavorText = new Text("to");
        Text checkboxFlavorText = new Text("Remove from:");
        CheckBox fileListSelection = new CheckBox();
        RadioButton listSelector = new RadioButton();
        RadioMenuItem src = new RadioMenuItem("Source FileListEditor");
        RadioMenuItem comp = new RadioMenuItem("Comparison FileListEditor");

        Button listCutter = new Button("Remove from list");
        listCutter.setOnAction((ActionEvent event) -> {
            if(src.isSelected()){
                RemoveSelectedFile(minFlavorText, maxFlavorText, srcFiles);
            }else if(comp.isSelected()){
                RemoveSelectedFile(minFlavorText, maxFlavorText, compFiles);
            }else{
                ErrorWindow("You did goofed now kiddo.");
            }
        });
    }

    private static void RemoveSelectedFile(Text minFileNumber, Text maxFileNumber, List<File> fileList){
        for(int x = Integer.parseInt(maxFileNumber.getText()); x <= Integer.parseInt(minFileNumber.getText()); --x){
            fileList.remove(x);
        }
    }

    private static void ErrorWindow(String errorMessage){
        Stage stage = new Stage();
        Pane pane = new Pane();
        Text message = new Text(errorMessage);
        pane.getChildren().add(message);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
