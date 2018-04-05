package a307a.midilib.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class NGramFactory implements INGramFactory {

	public List<INGram> getNGrams(List<Integer> intervalList, int nGramMagnitude) {
		List<INGram> returnList = new ArrayList<>();
		int i = 0;

		if (nGramMagnitude > 0) {
			while (intervalList.size() >= (nGramMagnitude + i)) {
				INGram newNGram = this.createNGram(intervalList, i, nGramMagnitude);
				if (returnList.contains(newNGram)) {
					for (Iterator<INGram> it = returnList.iterator(); it.hasNext();) {
						INGram n = it.next();
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

	private INGram createNGram(List<Integer> intervalList, Integer currentIndex, int nGramMagnitude) {

		INGram returnNGram;
		List<Integer> nGramIntervals = new ArrayList<>();
		for (int i = 0; i < nGramMagnitude; i++) {
			nGramIntervals.add(intervalList.get(currentIndex + i));
		}
		returnNGram = new NGram(nGramIntervals);
		return returnNGram;
	}
}
