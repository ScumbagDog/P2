package a307a.midilib.parser;

import java.util.ArrayList;
import java.util.List;

class _NGrammifier implements NGrammifier {

	public List<NGram> getNGrams(List<Integer> intervalList, int nGramMagnitude) {
		List<NGram> returnList = new ArrayList<>();
		
		return null;
	}

	private NGram createNGram(List<Integer> intervalList, Integer currentInterval, int nGramMagnitude) {

		NGram returnNGram;
		List<Integer> nGramIntervals = new ArrayList<>();
		int currentIndex = intervalList.indexOf(currentInterval);

		for (int i = 0; i < nGramMagnitude; i++) {
			nGramIntervals.add(intervalList.get(currentIndex + i));
		}
		returnNGram = new _NGram(1, nGramIntervals);
		return returnNGram;
	}
}
