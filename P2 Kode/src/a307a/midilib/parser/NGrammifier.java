package a307a.midilib.parser;
import java.util.List;
import java.util.Set;

public interface NGrammifier {
	public Set<NGram> getNGrams(List<Integer> IntervalList, int nGramMagnitude);
}

