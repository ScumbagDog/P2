package a307a.midilib.parser;

import java.util.*;

/*
 * An NGram is a sorted list of intervals.
 * This class also handles the frequency of its exact NGram in the melody it was created from
 */
public class NGram implements INGram {
    private int frequency;
    private List<Integer> intervals;

    NGram(List<Integer> intervals) {
        super();
        this.frequency = 1;
        this.intervals = new ArrayList<Integer>(intervals);
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
        } else if (!intervals.equals(other.intervals))
            return false;
        return true;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int getMagnitude() {
        return intervals.size();
    }

    public List<Integer> getIntervals() {
        return intervals;
    }

    public void incrementFrequency() {
        frequency++;
    }
}
