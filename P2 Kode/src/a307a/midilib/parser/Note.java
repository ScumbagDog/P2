package a307a.midilib.parser;

/**
 * Represents a note.
 */
public interface Note{
	int getPitch();
	int getVelocity();
	int getDuration();
	int getMelodicDuration();
	int getTick();
}
