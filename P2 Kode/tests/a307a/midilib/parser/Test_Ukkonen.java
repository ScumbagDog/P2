package a307a.midilib.parser;

import a307a.algorithm.IAlgorithm;
import a307a.algorithm.Ukkonen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_Ukkonen{
	File file1 = new File("C:\\Users\\Conrad\\IdeaProjects\\P2_\\P2 Kode\\src\\a307a\\midilib\\parser\\Mester-Jakob.mid");
	File file2 = new File("C:\\Users\\Conrad\\Desktop\\MIDI\\March i G.mid");
	AMidiSequenceReader msr1 = MidiTools.getMidiSequenceReader(file1);
	AMidiSequenceReader msr2 = MidiTools.getMidiSequenceReader(file2);

	public Test_Ukkonen() throws InvalidMidiDataException, IOException{
	}

	/* Test to see if two identical songs give the value 1. */
	@Test
	void test1(){
		AMelody mel1 = msr1.getMelody(0);
		AMelody mel2 = msr1.getMelody(0);
		IAlgorithm uk = new Ukkonen(2);
		double compRes = uk.compareTo(mel1, mel2);
		System.err.println(compRes);
	}

	/* Test with very different melody. */
	@Test
	void test2(){
		AMelody mel1 = msr1.getMelody(0);
		AMelody mel2 = msr2.getMelody(0);
		IAlgorithm uk = new Ukkonen(2);
		double compRes = uk.compareTo(mel1, mel2);
		System.err.println(compRes);
	}

	@Test
	void test3(){
		AMelody mel1 = msr2.getMelody(0);
		AMelody mel2 = msr2.getMelody(1);
		IAlgorithm uk = new Ukkonen(2);
		double compRes = uk.compareTo(mel1, mel2);
		System.err.println(compRes);
	}
}