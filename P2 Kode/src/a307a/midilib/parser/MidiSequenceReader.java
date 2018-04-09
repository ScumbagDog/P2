package a307a.midilib.parser;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class MidiSequenceReader extends AMidiSequenceReader{
	private int numChannels;
	private Track[] tracks;
	private Set<Integer> channels;

	private final int midiNoteOn = 0x9;

	/**
	 * Constructs a reader from the given MIDI sequence.
	 * @param sequence
	 */
	public MidiSequenceReader(Sequence sequence){
		super(sequence);
		this.setup(sequence);
	}

	@Override
	public List<INote> getAllNotesOnChannel(int channel){
		return null;
	}

	@Override
	public int getNumberOfPlayedChannels(){
		return channels.size();
	}

	private void setup(Sequence sequence){
		this.tracks = sequence.getTracks();
		this.channels = this.getChannels(this.tracks);
		this.numChannels = this.channels.size();
	}

	public IMelody getMelody(int channel){
		List<Track> tracks = findTracksWithChannel(channel);
		List<Note> notes = new LinkedList<>();

		for(Track t: tracks){
			int trackSize = t.size();
			for(int i = 0; i < trackSize; i++){
				MidiEvent event = t.get(i);
				int status = event.getMessage().getStatus();
				if((status >> 1) == this.midiNoteOn){
					int velocity = event.getMessage().getMessage()
					if()
				}
			}
		}
		return null;
	}

	private List<Track> findTracksWithChannel(int channel){
		List<Track> tracks = new LinkedList<Track>();

		for(Track t : tracks){
			int length = t.size();
			for(int i = 0; i < length; i++){
				MidiEvent event = t.get(i);
				int status = event.getMessage().getStatus();
				if((status >> 1) == this.midiNoteOn
						&& (status & 0x0f) == channel){
					tracks.add(t);
					break;
				}
			}
		}
		return tracks;
	}

	private Set<Integer> getChannels(Track[] tracks){
		Set<Integer> channels = new HashSet<>();

		for(Track t : tracks){
			int length = t.size();
			for(int i = 0; i < length; i++){
				MidiEvent event = t.get(i);
				int status = event.getMessage().getStatus();
				if((status >> 1) == this.midiNoteOn){
					channels.add(status & 0x0f);
					break;
				}
			}
		}
		return channels;
	}

}