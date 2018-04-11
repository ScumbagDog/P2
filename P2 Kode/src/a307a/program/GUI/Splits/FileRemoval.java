package a307a.program.GUI.Splits;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;
import java.util.List;

public class FileRemoval {
    public static Button Button(List<File> files, File file, List<Text> fileText, Text text){
        Button button = new Button();
        button.setOnAction(event -> {
            files.remove(file);
            fileText.remove(text);
        });

        return button;
    }
}
