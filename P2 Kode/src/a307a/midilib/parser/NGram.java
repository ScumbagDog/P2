package a307a.midilib.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/*
 * An NGram is a sorted list of intervals.
 * This class also handles the frequency of its exact NGram in the melody it was created from
 */
public class NGram<T extends Number> implements INGram<T> {
	private int frequency;
	private List<T> intervals;

	NGram(List<T> intervals) {
		super();
		this.frequency = 1;
		this.intervals = new ArrayList<T>(intervals);
	}

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
		NGram other = (NGram) obj;
		if (intervals == null) {
			if (other.intervals != null)
				return false;
		}
		else if (!intervals.equals(other.intervals))
			return false;
		return true;
	}

	public int getFrequency() {
		return frequency;
	}

	public List<T> getIntervals() {
		return intervals;
	}

	protected void incrementFrequency() {
		frequency++;
	}
}
