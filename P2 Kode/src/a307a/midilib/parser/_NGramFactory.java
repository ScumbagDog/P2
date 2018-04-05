package a307a.midilib.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class _NGramFactory implements NGramFactory {

	public List<NGram> getNGrams(List<Integer> intervalList, int nGramMagnitude) {
		List<NGram> returnList = new ArrayList<>();
		int i = 0;

		if (nGramMagnitude > 0) {
			while (intervalList.size() >= (nGramMagnitude + i)) {
				NGram newNGram = this.createNGram(intervalList, i, nGramMagnitude);
				if (returnList.contains(newNGram)) {
					for (Iterator<NGram> it = returnList.iterator(); it.hasNext();) {
						NGram n = it.next();
						if (n.equals(newNGram)) {
							n.incrementFrequency();
							break;
						}
					}

				} else {
					returnList.add(newNGram);
				}
				i++;
			}
		}
		return returnList;
	}

	private NGram createNGram(List<Integer> intervalList, Integer currentIndex, int nGramMagnitude) {

		NGram returnNGram;
		List<Integer> nGramIntervals = new ArrayList<>();
		for (int i = 0; i < nGramMagnitude; i++) {
			nGramIntervals.add(intervalList.get(currentIndex + i));
		}
		returnNGram = new _NGram(nGramIntervals);
		return returnNGram;
	}
}
