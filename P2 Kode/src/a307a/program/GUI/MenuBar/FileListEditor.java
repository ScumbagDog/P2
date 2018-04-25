package a307a.program.GUI.MenuBar;

import a307a.Exceptions.InvalidInputException;
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
//Furthermore, this class does not house any values that would be useful to have in instances of this class.
public class FileListEditor {
    //This window for removing files was added as an alternative to having a removal button next to each individual file entry.
    //The feature for having individual buttons for each file entry may be readded in the future.
    public static void remove(List<File> srcFiles, List<File> compFiles, BorderPane elementHolder){
        Stage stage = new Stage();
        List<Integer> listOfFiles = new ArrayList<>();
        Text minFlavorText = new Text("From file number:");
        Text maxFlavorText = new Text("to");
        Text checkboxFlavorText = new Text("remove from:");
        ToggleGroup listSelector = new ToggleGroup();
        RadioButton src = new RadioButton("Source file list");
        src.setToggleGroup(listSelector);
        RadioButton comp = new RadioButton("Comparison file list");
        comp.setToggleGroup(listSelector);
        TextField minFileNumber = new TextField();
        minFileNumber.setAlignment(Pos.CENTER);
        TextField maxFileNumber = new TextField();
        maxFileNumber.setAlignment(Pos.CENTER);

        Button listCutter = new Button("remove from list");
        listCutter.setOnAction((ActionEvent event) -> {
            try{
                if(src.isSelected()){
                    RemoveSelectedFile(minFileNumber, maxFileNumber, srcFiles);
                    elementHolder.setCenter(FileList.listsOfFiles(srcFiles, compFiles));
                }else if(comp.isSelected()){
                    RemoveSelectedFile(minFileNumber, maxFileNumber, compFiles);
                    elementHolder.setCenter(FileList.listsOfFiles(srcFiles, compFiles));
                }else{
                    ErrorWindow("Please select a list to remove files from.");
                }
            }catch(InvalidInputException e){ //https://mangadex.org/chapter/20209/16
                ErrorWindow("Invalid input detcted. Please ensure that:" +
                        "               \n* None of the fields are empty." +
                        "               \n* No negative numbers have been inserted." +
                        "               \n* The index number of the first file is not lower then the number of the last file." +
                        "               \n* None of the inputs are larger than the size of the file list.");
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

    //The code for removing files is placed in its own method as it's used multiple times.
    private static void RemoveSelectedFile(TextField minFileNumber, TextField maxFileNumber, List<File> fileList) throws InvalidInputException {
        int minNumber = Integer.parseInt(minFileNumber.getText()),
            maxNumber = Integer.parseInt(maxFileNumber.getText()),
            numberOfFilesRemoved = maxNumber - minNumber;

        if(maxNumber == 0){
            maxNumber = minNumber;
        }
        if((minNumber < 0)||(maxNumber < 0)){
            throw new InvalidInputException();
        }else if(minNumber > maxNumber){
            throw new InvalidInputException();
        }else if((minNumber > fileList.size()) || maxNumber > fileList.size()){
            throw new InvalidInputException();
        }
        for(int x = maxNumber; x >= minNumber; --x){
            fileList.remove(x);
        }

    }

    //AddFile was made to reduce instances of code in MainGUI
    public static List<File> AddFile(){
        Stage srcFile = new Stage();

        FileChooser browseSourceFile = new FileChooser();
        browseSourceFile.setTitle("Select a file to be added");

        //Stopper folk fra at vælge filer vi ikke kan arbejde med.
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("MIDI (*.mid)", "*.mid");
        browseSourceFile.getExtensionFilters().add(filter);

        return browseSourceFile.showOpenMultipleDialog(srcFile);
    }

    //An error window to tell the user when they fuck up in the removal process.
    private static void ErrorWindow(String errorMessage){
        Stage stage = new Stage();
        BorderPane pane = new BorderPane();
        Text message = new Text(errorMessage);
        pane.setCenter(message);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
