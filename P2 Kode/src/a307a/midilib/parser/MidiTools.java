package a307a.midilib.parser;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Contains tools used to extract data from
 * midi files. Used in algorithmic comparisons.
 */
public class MidiTools {

    /* Cannot create an instance of MidiTools. */
    private MidiTools() {
    }

    public static INGramFactory getMelodicNGrammifier(File file) {
        return null;
    }

    /**
     * Returns a MidiSequenceReader object from
     * the given file.
     *
     * @param midiFile
     * @return new MidiSequenceReader object.
     */
    public static AMidiSequenceReader getMidiSequenceReader(File midiFile)
            throws InvalidMidiDataException, IOException {
        Sequence sequence = MidiSystem.getSequence(midiFile);
        return getMidiSequenceReader(sequence);
    }

    public static AMidiSequenceReader getMidiSequenceReader(Sequence sequence) {
        return new MidiSequenceReader(sequence);
    }

    public static List<INGram> getNGrams(AMelody m1, int magnitude) {
        NGramFactory nFact = new NGramFactory();
        return nFact.getNGrams(m1, magnitude);
    }
}