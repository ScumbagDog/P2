package a307a.midilib.parser;

/**
 * Represents a midi file being read. Returns different
 * melodic sequences.
 */
public abstract class MidiSequenceReader{
	/**
	 * Returns the next note of the sequence across tracks
	 * @return next note of the sequence.
	 */
	abstract Note getNextNote();
	abstract Note getNextNoteOnChannel(int channel);

}