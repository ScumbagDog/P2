package a307a.midilib.parser;

import java.util.List;

public interface INGramFactory{
	public List<INGram> getNonDistinct(
			AMelody melody, int nGramMagnitude
	);

	public List<INGram> getNonDistinct(
			List<Integer> intervalList, int nGramMagnitude
	);

	public List<INGram> getNGrams(
			List<Integer> IntervalList, int nGramMagnitude
	);

	public List<INGram> getNGrams(AMelody melody, int nGramMagnitude);
}
