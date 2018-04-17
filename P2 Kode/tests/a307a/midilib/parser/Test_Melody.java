package a307a.midilib.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.sound.midi.*;
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

	@Test
	void test2() throws InvalidMidiDataException{
		Sequence seq = new Sequence((float) 0.0, 60000);

		seq.createTrack();
		Track t = seq.getTracks()[0];

		t.add(new MidiEvent(new ShortMessage(0x90, 0x50, 0x7f), 0));
		t.add(new MidiEvent(new ShortMessage(0x90, 0x4f, 0x7f), 150));

		MidiEvent e1 = t.get(0);
		MidiEvent e2 = t.get(1);

		System.err.println(e1.getMessage().getMessage()[1] & 0xff);
		System.err.println(e2.getMessage().getMessage()[1] & 0xff);

		AMidiSequenceReader reader = MidiTools.getMidiSequenceReader(seq);
		List<INote> notes = reader.getNotesOnChannel(0);

		for(INote n: notes)
			System.err.println(n.getPitch());
	}

	@Test
	void test3() throws InvalidMidiDataException{
		Sequence seq = new Sequence((float) 0.0, 60000);

		seq.createTrack();
		Track t = seq.getTracks()[0];

		t.add(new MidiEvent(new ShortMessage(0x90, 0x50, 0x7f), 0));
		t.add(new MidiEvent(new ShortMessage(0x90, 0x40, 0x7f), 0));
		t.add(new MidiEvent(new ShortMessage(0x90, 0x60, 0x7f), 0));
		t.add(new MidiEvent(new ShortMessage(0x90, 0x7f, 0x7f), 0));
		t.add(new MidiEvent(new ShortMessage(0x90, 0x50, 0x7f), 0));
		t.add(new MidiEvent(new ShortMessage(0x90, 0x4f, 0x7f), 150));
		t.add(new MidiEvent(new ShortMessage(0x90, 0x4f, 0x7f), 150));
		t.add(new MidiEvent(new ShortMessage(0x90, 0x4f, 0x7f), 150));

		AMidiSequenceReader reader = MidiTools.getMidiSequenceReader(seq);

		reader.getNotesOnChannel(0).forEach(n ->System.err.println(n.getPitch()));
//		IMelody melody = reader.getMelody(0);
//		System.err.println(melody.getNotes());
	}
}
