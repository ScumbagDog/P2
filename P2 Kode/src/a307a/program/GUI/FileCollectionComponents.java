package a307a.program.GUI;

import a307a.program.GUI.MenuBar.MidiFile;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCollectionComponents {

    private final BorderPane elementPlaceHolder;
    private List<File> srcFiles = new ArrayList<>();
    private List<File> compFiles = new ArrayList<>();
    private List<MidiFile> srcMidiFiles = new ArrayList<>();
    private List<MidiFile> compMidiFiles = new ArrayList<>();

    public FileCollectionComponents(BorderPane elementPlaceHolder) {
        this.elementPlaceHolder = elementPlaceHolder;
    }

    public BorderPane getElementPlaceHolder() {
        return elementPlaceHolder;
    }

    public List<File> getSrcFiles() {
        return srcFiles;
    }

    public List<File> getCompFiles() {
        return compFiles;
    }

    public List<MidiFile> getSrcMidiFiles() {
        return srcMidiFiles;
    }

    public List<MidiFile> getCompMidiFiles() {
        return compMidiFiles;
    }
}
