package a307a.midilib.parser;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class _MidiSequenceReader extends MidiSequenceReader{
	private int numChannels;
	private Track[] tracks;
	private Set<Integer> channels;
	/**
	 * Constructs a reader from the given MIDI sequence.
	 * @param sequence
	 */
	public _MidiSequenceReader(Sequence sequence){
		super(sequence);
		this.setup(sequence);
	}

	@Override
	public List<Note> getAllNotesOnChannel(int channel){
		return null;
	}

	@Override
	public int getNumberOfPlayedChannels(){
		return channels.size();
	}

	private void setup(Sequence sequence){
		this.tracks = sequence.getTracks();
		this.channels = this.countChannels(this.tracks);
		int numTracks = 0;
		for(Track track: tracks){
			int numEvents = tracks.length;
			for(int i = 0; i < numEvents; i++){
				MidiEvent event = track.get(i);;
				byte[] msg = event.getMessage().getMessage();
				int status = msg[0] & 0xff;
				if((status & 0xF0) == 0x90){
					int velocity = msg[2] & 0xff;
					if(velocity > 0 ){

					}
				}
			}
		}
	}

	private Set<Integer> countChannels(Track[] tracks){
		Set<Integer> channels = new HashSet<>();

		for(Track t: tracks){
			int length = t.size();
			for(int i = 0; i < length; i++){

			}
		}
	}
}