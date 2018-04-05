package a307a.midilib.parser;
import java.util.List;

public interface NGramFactory {
	public List<NGram> getNGrams(List<Integer> IntervalList, int nGramMagnitude);
}

