package a307a.midilib.parser;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Contains tools used to extract data from
 * midi files. Used in algorithmic comparisons.
 */
public class MidiTools{

	public static INGramFactory<Integer> getMelodicNGrammifier(File file){
		return null;
	}
	/**
	 * Returns a MidiSequenceReader object from
	 * the given file.
	 * @param midiFile
	 * @return new MidiSequenceReader object.
	 */
	public static AMidiSequenceReader getMidiSequenceReader(File midiFile) throws InvalidMidiDataException, IOException{
		Sequence sequence = MidiSystem.getSequence(midiFile);
		return getMidiSequenceReader(sequence);
	}

	public static AMidiSequenceReader getMidiSequenceReader(Sequence sequence){
		return new MidiSequenceReader(sequence);
	}
}