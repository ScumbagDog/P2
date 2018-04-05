package a307a.midilib.parser;

import java.util.List;

class _NGram implements NGram {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + frequency;
		result = prime * result + ((intervals == null) ? 0 : intervals.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		_NGram other = (_NGram) obj;
		if (frequency != other.frequency)
			return false;
		if (intervals == null) {
			if (other.intervals != null)
				return false;
		} else if (!intervals.equals(other.intervals))
			return false;
		return true;
	}
	int frequency;
	List<Integer> intervals;
	 _NGram(int frequency, List<Integer> intervals) {
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
