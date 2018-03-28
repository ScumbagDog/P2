package a307a.midilib.parser;

import java.util.List;

class _NGram<T> implements NGram {
	int frequency;
	List<T> intervals;
	 _NGram(int frequency, List<T> nGrams) {
		super();
		this.frequency = frequency;
		this.intervals = nGrams;
	}

	public int getFrequency() {
		return frequency;
	}

	public List<T> getIntervals() {
		return intervals;
	}
	public void increment() {
		frequency++;
	}
	
}
