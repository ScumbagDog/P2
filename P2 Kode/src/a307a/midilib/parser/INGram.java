package a307a.midilib.parser;

import java.util.List;

public interface INGram<T extends Number> {

	public List<T> getIntervals();

	public int getFrequency();	
}
