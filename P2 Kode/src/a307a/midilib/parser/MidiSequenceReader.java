package a307a.midilib.parser;

import javax.sound.midi.Sequence;

/**
 * Represents a midi file being read. Returns different
 * melodic sequences.
 */
public abstract class MidiSequenceReader{
	private MidiSequenceReader(){}

	/**
	 * Starts reading from the specified sequence.
	 * @param sequence
	 */
	public MidiSequenceReader(Sequence sequence){}
	/**
	 * Returns the next note of the sequence across tracks
	 * @return next note of the sequence.
	 */
	public abstract Note getNextNote();
	public abstract Note getNextNoteOnChannel(int channel);
}