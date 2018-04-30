package a307a.program.GUI.MenuBar;

import a307a.midilib.parser.MidiTools;
import javafx.scene.control.CheckBox;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MidiFile {
    private File filePath;
    private List<CheckBox> checkBoxes = new ArrayList<>();

    //If you ever figure out how to extract values from a set, then reinclude this shit.
    public MidiFile(File filePath) throws InvalidMidiDataException, IOException {
        this.filePath = filePath;
        for(int count = 0; count < 16; ++count){
            if(MidiTools.getMidiSequenceReader(this.filePath).getChannels().contains(count)){
                this.checkBoxes.add(new CheckBox(Integer.toString(count)));
            }
        }

    }

    public File getFilePath(){
        return this.filePath;
    }

    public String getFileName(){return this.filePath.getName();}

    public List<CheckBox> getCheckBoxes(){
        return this.checkBoxes;
    }
}
