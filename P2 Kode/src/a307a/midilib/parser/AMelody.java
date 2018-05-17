package a307a.midilib.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a melody.
 */
public abstract class AMelody{
	protected List<INote> notes;

	AMelody(List<INote> notes){
		this.notes = notes.stream()
				.map(INote::clone)
				.collect(Collectors.toList());
	}

	/**
	 * Creates a list of the melodic intervals in the melody.
	 *
	 * @return List of sequential intervals.
	 */
	public List<Integer> getPitchIntervals(){
		List<Integer> intervals = new LinkedList<>();
		int notesSize = this.notes.size();
		for(int i = 0; i < notesSize - 1; i++)
			intervals.add(this.notes.get(i + 1)
					.getPitch() - this.notes.get(i)
					.getPitch());
		return intervals;
	}

	/**
	 * Returns a non-distinct list of the notes in the melody.
	 *
	 * @return List of notes.
	 */
	public List<INote> getNotes(){
		return this.notes;
	}


	/**
	 * Returns the number of notes in the melody.
	 *
	 * @return number of notes.
	 */
	public int size(){
		return this.notes.size();
	}
}