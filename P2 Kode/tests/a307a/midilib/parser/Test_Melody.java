package a307a.midilib.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_Melody{
	@Test
	void test1() throws InvalidMidiDataException, IOException{
		File file = new File("C:\\Users\\Conrad\\IdeaProjects\\P2_\\P2 Kode\\src\\a307a\\midilib\\parser\\Mester-Jakob.mid");
		AMidiSequenceReader msr = MidiTools.getMidiSequenceReader(file);
		IMelody melody = msr.getMelody(0);

		List<Integer> intervals = melody.getPitchIntervals();
		System.err.println(intervals);
		System.err.println(intervals.size());
	}
}
