package a307a.midilib.parser;
import java.util.List;

public interface NGrammifier {
	public List<NGram> getNGrams(List<Interval> IntervalList, int nGramMagnitude);
}
