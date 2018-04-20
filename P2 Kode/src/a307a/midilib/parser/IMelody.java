package a307a.midilib.parser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a melody.
 */
public abstract class IMelody{
	protected List<INote> notes;

	IMelody(List<INote> notes){
		this.notes = notes.stream()
				.map(INote::clone)
				.collect(Collectors.toList());
	}
	/**
	 * Creates a list of the melodic intervals in the melody.
	 * @return List of sequential intervals.
	 */
	abstract List<Integer> getPitchIntervals();

	/**
	 * Returns a non-distinct list of the notes in the melody.
	 * @return List of notes.
	 */
	abstract List<INote> getNotes();

	/**
	 * Returns the number of notes in the melody.
	 * @return number of notes.
	 */
	abstract int size();
}