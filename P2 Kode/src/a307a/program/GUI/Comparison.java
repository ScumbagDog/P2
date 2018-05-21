package a307a.program.GUI;

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

    //
    public void useUkonnen(
            ResultList resultList,
            List<MidiFile> srcFiles,
            List<MidiFile> compFiles
    ) {
        try {
            Ukkonen ukkonen = new Ukkonen(2);
            for (MidiFile srcFile : srcFiles) {
                srcReader = MidiTools.getMidiSequenceReader(srcFile.getFilePath());
                srcName = srcFile.getFileName();
                for (int srcBoxes = 0;
                     srcBoxes < srcFile.getCheckBoxes()
                             .size();
                     ++srcBoxes
                        ) {
                    if (srcFile.getCheckBoxes()
                            .get(srcBoxes)
                            .isSelected()) {
                        srcText = srcFile.getCheckBoxes()
                                .get(srcBoxes)
                                .getText();
                        srcMelody = srcReader.getMelody(Integer.parseInt(srcText));
                        for (MidiFile compFile : compFiles) {
                            compReader = MidiTools.getMidiSequenceReader(compFile.getFilePath());
                            compName = compFile.getFileName();
                            for (int compBoxes = 0;
                                 compBoxes < compFile.getCheckBoxes()
                                         .size();
                                 ++compBoxes
                                    ) {
                                if (compFile.getCheckBoxes()
                                        .get(compBoxes)
                                        .isSelected()) {
                                    compText = compFile.getCheckBoxes()
                                            .get(compBoxes)
                                            .getText();
                                    compMelody = compReader.getMelody(Integer.parseInt(compText));
                                    resultList.addTableEntry(srcName
                                            + " Channel"
                                            + srcText
                                            + " to "
                                            + compName
                                            + " Channel"
                                            + compText, ukkonen.compareTo(srcMelody, compMelody));
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

}