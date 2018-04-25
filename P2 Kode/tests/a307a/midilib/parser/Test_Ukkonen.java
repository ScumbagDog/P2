package a307a.midilib.parser;

import a307a.algorithm.IAlgorithm;
import a307a.algorithm.Ukkonen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_Ukkonen{
	File file1 = new File("a307a/midilib/parser/Mester-Jakob.mid");
	AMidiSequenceReader msr1 = MidiTools.getMidiSequenceReader(file1);

	public Test_Ukkonen() throws InvalidMidiDataException, IOException{
	}

	/* Test to see if two identical songs give the value 1. */
	@Test
	void test1(){
		IMelody mel1 = msr1.getMelody(0);
		IMelody mel2 = msr1.getMelody(0);
		IAlgorithm uk = new Ukkonen(2);
		double compRes = uk.compareTo(mel1, mel2);
		System.err.println(compRes);
	}
}
