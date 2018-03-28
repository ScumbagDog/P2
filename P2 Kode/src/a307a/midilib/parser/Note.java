package a307a.midilib.parser;

/**
 * Represents a note.
 */
public interface Note{
	/**
	 * @return the MIDI pitch of the note.
	 */
	int getPitch();

	/**
	 * @return the MIDI velocity of the note.
	 */
	int getVelocity();

	/**
	 * The absolute beat, measured in quarter notes
	 * of the note.
	 * @return absolute beat.
	 */
	double getBeat();

	/**
	 * @return duration of the note in quarter notes.
	 */
	double getDuration();

	/**
	 * The melodic duration is the time from when this
	 * note is played to the next note in the melody.
	 * @return melodic duration of the note in quarter notes.
	 */
	double getMelodicDuration();

	/**
	 * @return the absolute tick of the note.
	 */
	int getTick();

	/**
	 * @return duration of the note in MIDI ticks.
	 */
	int getTickDuration();

	/**
	 * The melodic duration is the time from when this
	 * note is played to the next note in the melody.
	 * @return the melodic duration of the note in MIDI ticks.
	 */
	int getMelodicTickDuration();

	/**
	 * @return the channel the note was played on.
	 */
	int getChannel();
}