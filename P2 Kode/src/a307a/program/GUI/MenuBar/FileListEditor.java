package a307a.program.GUI.MenuBar;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListEditor {
    public static void Remove(List<File> srcFiles, List<File> compFiles){
        Stage stage = new Stage();
        List<Integer> listOfFiles = new ArrayList<>();
        Text minFlavorText = new Text("From file number:");
        Text maxFlavorText = new Text("to");
        Text checkboxFlavorText = new Text("Remove from:");
        ToggleGroup listSelector = new ToggleGroup();
        RadioButton src = new RadioButton("Source file list");
        src.setToggleGroup(listSelector);
        RadioButton comp = new RadioButton("Comparison file list");
        comp.setToggleGroup(listSelector);
        TextField minFileNumber = new TextField();
        minFileNumber.setAlignment(Pos.CENTER);
        TextField maxFileNumber = new TextField();
        maxFileNumber.setAlignment(Pos.CENTER);

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

        GridPane gridPane = new GridPane();
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

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();

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
