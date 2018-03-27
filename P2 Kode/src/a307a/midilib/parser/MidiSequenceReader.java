package a307a.midilib.parser;

public abstract class MidiSequenceReader{
	abstract Note getNextNote();
	abstract Note getNextNoteOnChannel(int channel);
}