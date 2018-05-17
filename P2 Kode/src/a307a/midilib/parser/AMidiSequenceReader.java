package a307a.midilib.parser;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Represents a midi sequence being read. Contains methods
 * for extracting data from the sequence and creating AMelody
 * objects.
 */
public abstract class AMidiSequenceReader {
    /**
     * The sequence that is being operated on.
     */
    protected Sequence sequence;

    /**
     * Constructs a reader from the given MIDI sequence.
     *
     * @param sequence
     */
    public AMidiSequenceReader(Sequence sequence) {
        this.sequence = sequence;
    }

    /**
     * Constructs a reader from the given MIDI file.
     *
     * @param midiFile
     */
    public AMidiSequenceReader(File midiFile) throws InvalidMidiDataException, IOException {
        this.sequence = MidiSystem.getSequence(midiFile);
    }

    /* Communicates that subclasses should not have a parameterless
     * constructor. */
    private AMidiSequenceReader() {
    }

    public abstract Set<Integer> getChannels();

    /**
     * Creates an AMelody object from notes on the given
     * channel.
     *
     * @param channel
     * @return AMelody object.
     */
    public abstract AMelody getMelody(int channel);

    /**
     * Reads the sequence and returns all notes on specified
     * channel.
     *
     * @param channel
     * @return notes on channel.
     */
    public abstract List<INote> getNotesOnChannel(int channel);

    /**
     * Returns number of channels being played in the sequ-
     * ence.
     *
     * @return channels
     */
    public abstract int getNumberOfPlayedChannels();
}