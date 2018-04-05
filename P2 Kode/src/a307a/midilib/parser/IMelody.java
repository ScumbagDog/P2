package a307a.midilib.parser;

import java.util.List;
import java.util.Set;

public interface IMelody extends Iterable<INote>{
	double getBeat(INote note);
	Set<Integer> getChannels();
	List<Integer> getIntervals();
}
