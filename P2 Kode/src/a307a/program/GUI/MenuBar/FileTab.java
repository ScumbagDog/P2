package a307a.program.GUI.MenuBar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileTab {
    public static File AddFile(){
        Stage srcFile = new Stage();

        FileChooser browseSourceFile = new FileChooser();
        browseSourceFile.setTitle("Select a file to be added");

        //Stopper folk fra at v;lge filer vi ikke kan arbejde med.
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("MIDI (*.mid)", "*.mid");
        browseSourceFile.getExtensionFilters().add(filter);
        File filePath = browseSourceFile.showOpenDialog(srcFile);

        return filePath;
    }
}
