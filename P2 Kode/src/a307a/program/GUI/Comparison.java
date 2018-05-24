package a307a.program.GUI;

import a307a.algorithm.IAlgorithm;
import a307a.algorithm.Ukkonen;
import a307a.midilib.parser.*;
import a307a.program.GUI.MenuBar.MidiFile;
import javafx.scene.control.Alert;

import javax.sound.midi.InvalidMidiDataException;
import java.io.IOException;
import java.util.List;

//import a307a.midilib.parser.Melody;

//This class is intended to act as the link between frontend and backend
//by containing methods which call upon the algorithms and forwards the results to the
// front end.
public class Comparison {
    private AMidiSequenceReader srcReader;
    private AMidiSequenceReader compReader;
    private AMelody srcMelody;
    private AMelody compMelody;
    private String srcText;
    private String srcName;
    private String compText;
    private String compName;
    private ResultList resultList;
    private List<MidiFile> srcFiles;
    private List<MidiFile> compFiles;

    public void useAlgorithm(IAlgorithm algorithm) {
        try {
            for (MidiFile srcFile : srcFiles) {
                prepareSrcReader(srcFile);
                for (int srcBoxes = 0;
                     srcBoxes < srcFile.getCheckBoxes().size();
                     ++srcBoxes) {
                    if (srcFile.getCheckBoxes().get(srcBoxes).isSelected()) {
                        prepareSrcMelody(srcFile, srcBoxes);
                        for (MidiFile compFile : compFiles) {
                            prepareCompReader(compFile);
                            for (int compBoxes = 0;
                                 compBoxes < compFile.getCheckBoxes().size();
                                 ++compBoxes) {
                                if (compFile.getCheckBoxes().get(compBoxes).isSelected()) {
                                    prepareCompMelody(compFile, compBoxes);
                                    executeAlgorithm(algorithm);
                                }
                            }
                        }
                    }
                }
            }

        } catch (InvalidMidiDataException | IOException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid file");
            error.setContentText(
                    "One or more of the selected files are either corrupt or incompatible with the"
                            + " program."
                            + "\nThe comparison process will therefore be aborted.");
            error.showAndWait();

            return;
        }
    }

    public Comparison(ResultList resultList, List<MidiFile> srcFiles, List<MidiFile> compFiles) {
        this.resultList = resultList;
        this.srcFiles = srcFiles;
        this.compFiles = compFiles;
    }

    private void executeAlgorithm(IAlgorithm algorithm){
        resultList.addTableEntry(algorithm.getName() + ": "
                + srcName + " Channel"
                + srcText + " to " + compName + " Channel"
                + compText, algorithm.compareTo(srcMelody, compMelody));
    }

    private void prepareSrcReader(MidiFile file) throws InvalidMidiDataException, IOException {
        srcReader = MidiTools.getMidiSequenceReader(file.getFilePath());
        srcName = file.getFileName();
    }

    private void prepareCompReader(MidiFile file) throws InvalidMidiDataException, IOException {
        compReader = MidiTools.getMidiSequenceReader(file.getFilePath());
        compName = file.getFileName();
    }

    private void prepareSrcMelody(MidiFile file, int boxes){
        srcText = file.getCheckBoxes().get(boxes).getText();
        srcMelody = srcReader.getMelody(Integer.parseInt(srcText));
    }

    private void prepareCompMelody(MidiFile file, int boxes){
        compText = file.getCheckBoxes().get(boxes).getText();
        compMelody = compReader.getMelody(Integer.parseInt(compText));
    }
}