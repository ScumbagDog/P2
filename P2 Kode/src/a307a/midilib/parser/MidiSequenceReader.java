package a307a.midilib.parser;

import javax.sound.midi.*;
import java.util.*;

class MidiSequenceReader extends AMidiSequenceReader{
	private final Track[] tracks;
	private final Set<Integer> channels;
	private final int numChannels;

	private final int midiNoteOn = 0x9;

	// Constructor :^)
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
				MidiMessage midiMessage = t.get(i).getMessage();
				if(isMidiNoteOnMessage(midiMessage)){
					channels.add(
							getChannelFromMidiNoteOnMessage(midiMessage));
					break;
				}
			}
		return channels;
	}

	private boolean isMidiNoteOnMessage(MidiMessage message){
		return (message.getStatus() >> 4)
				== this.midiNoteOn;
	}

	private int getChannelFromMidiNoteOnMessage(
			MidiMessage message){
		return message.getStatus() & 0x0f;
	}

	@Override
	public IMelody getMelody(int channel){
		List<INote> notes = getNotesOnChannel(channel);
		return new Melody(notes);
	}

	@Override
	public List<INote> getNotesOnChannel(int channel){
		List<Track> tracks = findTracksWithChannel(channel);
		List<INote> notes = new LinkedList<>();

		for(Track t: tracks)
			for(int i = 0; i < t.size(); i++){
				MidiEvent event = t.get(i);
				if(isMidiNoteOnMessage(event.getMessage())){
					ShortMessage message = (ShortMessage)event.getMessage();
					int velocity = message.getData2();
					if(velocity > 0){
						int pitch = message.getData1();
						long tick = event.getTick();
						notes.add(new Note(pitch, velocity, tick));
					}
				}
			}
		return notes;
	}

	// Creates a list of tracks that a channel is being played
	// on.
	private List<Track> findTracksWithChannel(int channel){
		List<Track> tracksOnChannel = new LinkedList<Track>();
		for(Track t : this.tracks)
			for(int i = 0; i < t.size(); i++){
				MidiMessage message = t.get(i).getMessage();
				if(isMidiNoteOnMessage(message)
						&& getChannelFromMidiNoteOnMessage(message)
						== channel){
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