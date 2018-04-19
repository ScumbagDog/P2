package a307a.midilib.parser;

import javax.sound.midi.*;
import java.util.*;
import java.util.stream.Collectors;

class MidiSequenceReader extends AMidiSequenceReader{
	private final Track[] tracks;
	private final Set<Integer> channels;
	private final int numChannels;
	private final List<MidiEvent> events;
	private final List<BufferedMidiEvent> bufferedEvents;

	public MidiSequenceReader(Sequence sequence){
		super(sequence);
		this.tracks = sequence.getTracks();
		this.events = this.getEventsFromTracks(this.tracks);
		this.bufferedEvents = this.getBufferedEvents(this.events);
		this.channels = this.getChannels();
		this.numChannels = this.channels.size();
	}

	private List<MidiEvent> getEventsFromTracks(Track[] tracks){
		List<MidiEvent> events = new ArrayList<>();
		for(Track t: this.tracks)
			events.addAll(getEventsFromTrack(t));

		/* Sort the list according to when events occur (tick). */
		events.sort((e1, e2)->(int) (e1.getTick() - e2.getTick()));
		return events;
	}

	private List<MidiEvent> getEventsFromTrack(Track track){
		int numEvents = track.size();
		List<MidiEvent> events = new ArrayList<>();
		for(int i = 0; i < numEvents; i++)
			events.add(track.get(i));
		return events;
	}

	/* Returns a list of BufferedMidiEvent objects from the list of
	 * events in the MIDI sequence. */
	private List<BufferedMidiEvent> getBufferedEvents(List<MidiEvent> events){
		return events.stream()
				.map(BufferedMidiEvent::new)
				.collect(Collectors.toList());
	}

	/* Returns a set of the 0-indexed channels being played on
	 * in the sequence.  */
	@Override
	public Set<Integer> getChannels(){
		return bufferedEvents.stream()
				.map(BufferedMidiEvent::getChannel)
				.collect(Collectors.toSet());
	}

	@Override
	public IMelody getMelody(int channel){
		List<INote> notes = getNotesOnChannel(channel);
		return new Melody(notes);
	}

	/* Creates a list of notes being played on a channel. */
	@Override
	public List<INote> getNotesOnChannel(int channel){
		return this.bufferedEvents.stream()
				.filter(BufferedMidiEvent::isNoteOn)
				.filter(bme -> bme.getChannel() == channel)
				.map(Note::new)
				.collect(Collectors.toList());
	}

	/* Returns the number of channels being played on in the sequence. */
	@Override
	public int getNumberOfPlayedChannels(){
		return this.channels.size();
	}
}