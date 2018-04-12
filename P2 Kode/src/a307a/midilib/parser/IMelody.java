package a307a.midilib.parser;

import java.util.List;

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

	/**
	 * Returns the number of notes in the melody.
	 * @return number of notes.
	 */
	int size();
}
