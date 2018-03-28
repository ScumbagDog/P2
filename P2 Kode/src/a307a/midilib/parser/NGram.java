package a307a.midilib.parser;

import java.util.List;

public interface NGram<T> {
	public List<T> getIntervals();
	public int getFrequency();
	public void increment();
}
