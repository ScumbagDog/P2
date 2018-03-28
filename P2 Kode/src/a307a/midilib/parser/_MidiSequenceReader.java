package a307a.midilib.parser;

import javax.sound.midi.Sequence;
import java.util.List;

class _MidiSequenceReader extends MidiSequenceReader{

	/**
	 * Constructs a reader from the given MIDI sequence.
	 *
	 * @param sequence
	 */
	public _MidiSequenceReader(Sequence sequence){
		super(sequence);
	}

	@Override
	public List<Note> getAllNotesOnChannel(int channel){
		return null;
	}

	@Override
	public int getNumberOfPlayedChannels(){
		return 0;
	}
}
