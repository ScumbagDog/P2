package a307a.midilib.parser;

import javax.sound.midi.MidiEvent;

/* Represent a MIDI event with methods for accessing details. */
class BufferedMidiEvent{
	private final int status;
	private final byte[] message;
	private final long tick;

	BufferedMidiEvent(MidiEvent event){
		this.status = event.getMessage()
				.getStatus();
		this.message = event.getMessage()
				.getMessage();
		this.tick = event.getTick();
	}

	/* Checks if a midi-message is a note-on message. */
	boolean isNoteOn(){
		/* Most significant 4 bits of the status byte tells the type
		 * of the midi message. */
		return (this.status >> 4) == 0x9;
	}

	/* Returns the channel the message occurs on. */
	//TODO: Returnerer kanal ligemeget hvilken type event der er tale om(!), bør rette!
	int getChannel(){
		/* Mask returns bit of status byte that shows what
		 * channel a note is played on. */
		return this.status & 0x0f;
	}

	/* Returns the pitch of the MIDI note-on message. (Assuming the message
	 * is a note-on. message.) */
	public int getPitch(){
		return this.message[1] & 0xff;
	}

	/* Returns the velocity (volume) of the MIDI note-on message. (Assuming the
	 * message is a note-on message.) */
	public int getVelocity(){
		return this.message[2] & 0xff;
	}

	/* Returns the MIDI absolute tick of the event. */
	public long getTick(){
		return this.tick;
	}
}