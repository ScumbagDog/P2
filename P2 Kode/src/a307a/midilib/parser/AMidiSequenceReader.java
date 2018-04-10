package a307a.midilib.parser;

import javax.sound.midi.Sequence;
import java.util.List;

/**
 * Represents a midi sequence being read. Contains methods
 * for extracting data from the sequence.
 */
public abstract class AMidiSequenceReader{
	/**
	 * The sequence that is being operated on.
	 */
	protected Sequence sequence;

	/**
	 * Constructs a reader from the given MIDI sequence.
	 * @param sequence
	 */
	public AMidiSequenceReader(Sequence sequence){
		this.sequence = sequence;
	}

	private AMidiSequenceReader(){}

	public abstract IMelody getMelody(int channel);

	/**
	 * Reads the sequence and returns all notes on specified
	 * channel.
	 * @param channel
	 * @return notes on channel.
	 */
	public abstract List<INote> getNotesOnChannel(int channel);

	/**
	 * Returns number of channels being played in the sequ-
	 * ence.
	 * @return channels
	 */
	public abstract int getNumberOfPlayedChannels();
}