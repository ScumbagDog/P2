package a307a.program.GUI;

import a307a.program.GUI.MenuBar.MidiFile;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private List<File> srcFileList;
    private List<File> compFileList;
    private List<MidiFile> srcMidiFiles;
    private List<MidiFile> compMidiFiles;


    public FileStorage() {
        srcFileList = new ArrayList<>();
        compFileList = new ArrayList<>();
        srcMidiFiles = new ArrayList<>();
        compMidiFiles = new ArrayList<>();
    }

    public void initiateMidiFileList(List<File> listOfFiles, List<MidiFile> listOfMidis) {
        for (int count = 0; count < listOfFiles.size(); ++count) {
            try {
                listOfMidis.add(new MidiFile(listOfFiles.get(count)));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listOfFiles.clear();
    }

    public List<File> getSrcFileList() {
        return srcFileList;
    }

    public List<File> getCompFileList() {
        return compFileList;
    }

    public List<MidiFile> getSrcMidiFiles() {
        return srcMidiFiles;
    }

    public List<MidiFile> getCompMidiFiles() {
        return compMidiFiles;
    }
}
