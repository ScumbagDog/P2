package a307a.midilib.parser;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Represents a monophonic melody.
 */
public interface IMelody{

	/**
	 * Creates a list of the melodic intervals in the melody.
	 * @return List of sequential intervals.
	 */
	List<Integer> getPitchIntervals();

	/**
	 * Returns a non-distinct list of the notes in the melody.
	 * @return List of notes.
	 */
	List<INote> getNotes();
}
