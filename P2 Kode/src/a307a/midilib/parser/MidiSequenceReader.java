package a307a.midilib.parser;

import java.util.List;

/**
 * Represents a midi file being read. Returns different
 * melodic sequences.
 */
public abstract class MidiSequenceReader{
	/**
	 * Returns the next note of the sequence across tracks
	 * @return next note of the sequence.
	 */
	abstract List<Note> getAllNotes();
	abstract List<Note> getAllNotesOnChannel(int channel);

}