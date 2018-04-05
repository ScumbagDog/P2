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
		this.file = new File("C:\\Users\\Conrad\\Desktop\\MIDI\\March i G.mid");
		this.sequence = MidiSystem.getSequence(file);
		this.msr = MidiTools.getMidiSequenceReader(file);

	}
	@Test
	void test1() throws InvalidMidiDataException, IOException{
		assertEquals(3, msr.getNumberOfPlayedChannels());
	}
}