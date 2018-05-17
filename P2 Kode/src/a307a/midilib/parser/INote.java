package a307a.midilib.parser;

/**
 * Represents a MIDI note.
 */
public interface INote extends Cloneable{

	/**
	 * Pitch is the musical note being played.
	 *
	 * @return the MIDI pitch of the note.
	 */
	int getPitch();

	/**
	 * Returns the velocity (volume) of the note.
	 *
	 * @return the MIDI velocity of the note.
	 */
	int getVelocity();

	/**
	 * Returns absolute MIDI tick value of when the note is played.
	 *
	 * @return the absolute tick of the note.
	 */
	long getTick();

	INote clone();
}