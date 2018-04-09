package a307a.midilib.parser;

/**
 * Represents a note.
 */
public interface INote{
	/**
	 * @return the MIDI pitch of the note.
	 */
	int getPitch();

	/**
	 * @return the MIDI velocity of the note.
	 */
	int getVelocity();

	/**
	 * @return the absolute tick of the note.
	 */
	long getTick();
}