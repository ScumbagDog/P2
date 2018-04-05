package a307a.midilib.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class _NGram implements NGram {


	
	@Override
	public int hashCode() {
		return Objects.hash(intervals);
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
		if (intervals == null) {
			if (other.intervals != null)
				return false;
		} else if (!intervals.equals(other.intervals))
			return false;
		return true;
	}
	private int frequency;
	private List<Integer> intervals;
	 _NGram(List<Integer> intervals) {
		super();
		this.frequency = 1;
		this.intervals = new ArrayList<Integer>(intervals);
	}

	public int getFrequency() {
		return frequency;
	}

	public List<Integer> getIntervals() {
		return intervals;
	}
	public void incrementFrequency() {
		frequency++;
	}
	
	
}
