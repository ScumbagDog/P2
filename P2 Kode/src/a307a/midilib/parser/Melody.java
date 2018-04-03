package a307a.midilib.parser;

public interface Melody extends Iterable<Note>{
	double getBeat(Note note);
}
