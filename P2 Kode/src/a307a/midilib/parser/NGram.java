package a307a.midilib.parser;

import java.util.List;

public interface NGram{
	public List<Interval> getIntervals();
	public int getFrequency();
	public void increment();
}
