package a307a.midilib.parser;

import java.util.List;

class _NGram implements NGram {
	int frequency;
	List<Interval> intervals;
	 _NGram(int frequency, List<Interval> intervals) {
		super();
		this.frequency = frequency;
		this.intervals = intervals;
	}

	public int getFrequency() {
		return frequency;
	}

	public List<Interval> getIntervals() {
		return intervals;
	}
	public void increment() {
		frequency++;
	}
	
}
