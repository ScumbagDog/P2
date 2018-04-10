package a307a.midilib.parser;

import java.util.List;

public interface INGram {

	public List<Integer> getIntervals();

	public int getFrequency();

	public void incrementFrequency();
	
}
