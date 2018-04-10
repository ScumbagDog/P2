package a307a.midilib.parser;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IMelody{
	List<Integer> getPitchIntervals();
	List<INote> getNotes();
}
