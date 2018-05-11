package a307a.midilib.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Test_MidiSequenceReader{
	Sequence sequence;
	File file;
	AMidiSequenceReader msr;
	@BeforeAll
	void beforeAll() throws InvalidMidiDataException, IOException{
		this.file = new File("March-i-G.mid");
		this.sequence = MidiSystem.getSequence(file);
		this.msr = MidiTools.getMidiSequenceReader(file);
	}
	@Test
	void test1() throws InvalidMidiDataException, IOException{
//		System.err.println(msr.getChannels());
		assertEquals(3 ,msr.getNumberOfPlayedChannels());
		System.err.println("Notes on channel: "
				+ msr.getNotesOnChannel(1).size());

		AMelody melody = msr.getMelody(1);
		System.err.println(melody.size());
	}

	@Test
	void test2(){
		assertEquals(msr.getNotesOnChannel(1).size(), msr.getMelody(1).getNotes().size());
		assertEquals(msr.getNotesOnChannel(2).size(), msr.getMelody(2).size());
	}
}