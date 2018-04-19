package a307a.midilib.parser;

import javax.sound.midi.*;
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

	/* Returns a list of the 0-indexed channels being played on
	 * in the sequence. */
	@Override
	public Set<Integer> getChannels(){
		return this.getChannels(this.tracks);
	}

	private Set<Integer> getChannels(Track[] tracks){
		Set<Integer> channels = new HashSet<>();
		for(Track t : tracks)
			for(int i = 0; i < t.size(); i++){
				MidiMessage midiMessage = t.get(i).getMessage();
				/* For each note played, add the channel the note
				 * was played on. */
				if(isMidiNoteOnMessage(midiMessage))
					channels.add(
							getChannelFromMidiNoteOnMessage(midiMessage));
			}
		return channels;
	}

	/* Function checks if a midi-message is a note-on message. */
	private boolean isMidiNoteOnMessage(MidiMessage message){
		/* Most significant 4 bits of the status byte tells the type
		 * of the midi message. */
		return (message.getStatus() >> 4)
				== this.midiNoteOn;
	}

	private int getChannelFromMidiNoteOnMessage(MidiMessage message){
		/* Mask returns bit of status byte that shows what
		 * channel a note is played on. */
		return message.getStatus() & 0x0f;
	}

	@Override
	public IMelody getMelody(int channel){
		List<INote> notes = getNotesOnChannel(channel);
		return new Melody(notes);
	}

	/* Creates a list of notes being played on a channel. */
	@Override
	public List<INote> getNotesOnChannel(int channel){
		List<Track> tracks = findTracksWithChannel(channel);
		List<INote> notes = new LinkedList<>();

		for(Track t: tracks)
			notes.addAll(getNotesOnChannelFromTrack(channel, t));
		return notes;
	}

	//TODO: Gør privat.
	public List<INote> getNotesOnChannelFromTrack(int integer, Track t){
		List<INote> notes = new LinkedList<>();
		for(int i = 0; i < t.size(); i++){
			MidiEvent event = t.get(i);
			/* If a message in the track is a note-on message,
			 * create a short message. */
			if(isMidiNoteOnMessage(event.getMessage())){
				ShortMessage message = (ShortMessage)event.getMessage();

				/* Use the data from the short message to create
				 * a Note object and add it to the list. */
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

	/* Return a list of tracks that a channel is being played
	 * on. */
	private List<Track> findTracksWithChannel(int channel){
		List<Track> tracksOnChannel = new LinkedList<>();
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