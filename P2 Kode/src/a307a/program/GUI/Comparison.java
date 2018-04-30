package a307a.program.GUI;

import a307a.algorithm.Ukkonen;
import a307a.midilib.parser.AMelody;
import a307a.midilib.parser.AMidiSequenceReader;
import a307a.midilib.parser.MidiTools;
import a307a.program.GUI.MenuBar.MidiFile;
import javafx.scene.control.Alert;
//import a307a.midilib.parser.Melody;

import javax.sound.midi.InvalidMidiDataException;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This class is intended to act as the link between frontend and backend
//by containing methods which call upon the algorithms and forwards the results to the front end.
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
    public void useUkonnen(Resultlist resultlist, List<MidiFile> srcFiles, List<MidiFile> compFiles) {
        try {
            Ukkonen ukkonen = new Ukkonen(2);
            for (int srcCount = 0; srcCount < srcFiles.size(); ++srcCount) {
                srcReader = MidiTools.getMidiSequenceReader(srcFiles.get(srcCount).getFilePath());
                srcName = srcFiles.get(srcCount).getFileName();
                for(int srcBoxes = 0; srcBoxes < srcFiles.get(srcCount).getCheckBoxes().size(); ++srcBoxes){
                    if(srcFiles.get(srcCount).getCheckBoxes().get(srcBoxes).isSelected()) {
                        srcText = srcFiles.get(srcCount).getCheckBoxes().get(srcBoxes).getAccessibleText();
                        srcMelody = srcReader.getMelody(Integer.parseInt(srcText));
                        for(int compCount = 0; compCount < compFiles.size(); ++compCount){
                            compReader = MidiTools.getMidiSequenceReader(compFiles.get(compCount).getFilePath());
                            compName = compFiles.get(compCount).getFileName();
                            for(int compBoxes = 0; compBoxes < compFiles.get(compCount).getCheckBoxes().size(); ++compCount){
                                if(compFiles.get(compCount).getCheckBoxes().get(compBoxes).isSelected()){
                                    compText = compFiles.get(compCount).getCheckBoxes().get(compBoxes).getAccessibleText();
                                    compMelody = compReader.getMelody(Integer.parseInt(compText));
                                    resultlist.addTableEntry(srcName + " Channel" + srcText + " to " + compName + " Channel" + compText,
                                            ukkonen.compareTo(srcMelody, compMelody));
                                }
                            }
                        }
                    }
                }
            }

        }catch(InvalidMidiDataException | IOException e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid file");
            error.setContentText("One or more of the selected files are either corrupt or incompatible with the program." +
                    "\nThe comparison process will therefore be aborted.");
            error.showAndWait();

            return;
        }
    }

}