package a307a.midilib.parser;

import java.util.List;

class _NGram implements NGram {
	int frequency;
	List<Integer> intervals;
	 _NGram(int frequency, List<Integer> Integers) {
		super();
		this.frequency = frequency;
		this.intervals = intervals;
	}

	public int getFrequency() {
		return frequency;
	}

	public List<Integer> getIntervals() {
		return intervals;
	}
	public void increment() {
		frequency++;
	}
	
}
