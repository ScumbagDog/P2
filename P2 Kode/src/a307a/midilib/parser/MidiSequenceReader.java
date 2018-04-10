package a307a.midilib.parser;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import java.util.*;

class MidiSequenceReader extends AMidiSequenceReader{
	private final Track[] tracks;
	private final Set<Integer> channels;
	private final int numChannels;

	private final int midiNoteOn = 0x9;

	public MidiSequenceReader(Sequence sequence){
		super(sequence);
		this.tracks = sequence.getTracks();
		this.channels = this.getChannels();
		this.numChannels = this.channels.size();
	}

	@Override
	public Set<Integer> getChannels(){
		return this.getChannels(this.tracks);
	}

	private Set<Integer> getChannels(Track[] tracks){
		Set<Integer> channels = new HashSet<>();
		for(Track t : tracks)
			for(int i = 0; i < t.size(); i++){
				int status = t.get(i).getMessage().getStatus();
				if((status >> 4) == this.midiNoteOn){
					channels.add(status & 0x0f);
					break;
				}
			}
		return channels;
	}

	@Override
	public IMelody getMelody(int channel){
		List<INote> notes = getNotesOnChannel(channel);
		return new Melody(notes);
	}

	@Override
	public List<INote> getNotesOnChannel(int channel){
		List<Track> tracks = findTracksWithChannel(channel);
		System.out.println("Tracks is empty: " + tracks.isEmpty());
		List<INote> notes = new LinkedList<>();

		for(Track t: tracks)
			for(int i = 0; i < t.size(); i++){
				MidiEvent event = t.get(i);
				if((event.getMessage().getStatus() >> 4)
						== this.midiNoteOn){
					byte[] message = event.getMessage().getMessage();
					int velocity = message[2] & 0xff;
					if(velocity > 0){
						int pitch = message[1] & 0xff;
						long tick = event.getTick();
						INote note = new Note(pitch, velocity, tick);
						notes.add(note);
					}
				}
			}
		return notes;
	}

	private List<Track> findTracksWithChannel(int channel){
		List<Track> tracksOnChannel = new LinkedList<Track>();
		for(Track t : this.tracks)
			for(int i = 0; i < t.size(); i++){
				MidiEvent event = t.get(i);
				int status = event.getMessage().getStatus();
				if((status >> 4) == this.midiNoteOn
						&& (status & 0x0f) == channel){
					tracksOnChannel.add(t);
					break;
				}
			}
		return tracksOnChannel;
	}

	@Override
	public int getNumberOfPlayedChannels(){
		return this.channels.size();
	}

}