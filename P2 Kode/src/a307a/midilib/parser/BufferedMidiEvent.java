package a307a.midilib.parser;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;

class BufferedMidiEvent{
	private final int status;
	private final byte[] message;
	private final long tick;

	BufferedMidiEvent(MidiEvent event){
		this.status = event.getMessage().getStatus();
		this.message = event.getMessage().getMessage();
		this.tick = event.getTick();
	}

	/* Function checks if a midi-message is a note-on message. */
	boolean isNoteOn(){
		/* Most significant 4 bits of the status byte tells the type
		 * of the midi message. */
		return (this.status >> 4) == 0x9;
	}

	int getChannel(){
		/* Mask returns bit of status byte that shows what
		 * channel a note is played on. */
		return this.status & 0x0f;
	}

	public int getPitch(){
		return this.message[1] & 0xff;
	}

	public int getVelocity(){
		return this.message[2] & 0xff;
	}

	public long getTick(){
		return this.tick;
	}
}
