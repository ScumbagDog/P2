package a307a.midilib.parser;

import java.util.List;

public interface INGram{
	List<Integer> getIntervals();
	int getFrequency();
	int getMagnitude();
}
