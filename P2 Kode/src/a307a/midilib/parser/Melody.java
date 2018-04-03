package a307a.midilib.parser;

import java.util.List;
import java.util.Set;

public interface Melody extends Iterable<Note>{
	double getBeat(Note note);
	Set<Integer> getChannels();
	List<Integer> getIntervals();
}
