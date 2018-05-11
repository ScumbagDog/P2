package a307a.midilib.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
 * NGramFactory creates NGrams from a list of intervals.
 */
public class NGramFactory implements INGramFactory {

	@Override
	public List<INGram> getNGrams(AMelody melody, int nGramMagnitude){
		return getNGrams(melody.getPitchIntervals(), nGramMagnitude);
	}

	public List<INGram> getNGrams(List<Integer> intervalList, int nGramMagnitude) {
		List<NGram> nGramList = new ArrayList<>();
		int i = 0;
		if (nGramMagnitude > 0) {
			while (intervalList.size() >= (nGramMagnitude + i)) {
				NGram newNGram = this.createNGram(intervalList, i, nGramMagnitude);
				if (nGramList.contains(newNGram)) {
					this.incrementNGram(nGramList, newNGram);
				}
				else {
					nGramList.add(newNGram);
				}
				i++;
			}
		}
		List <INGram> returnList = new ArrayList<>(nGramList);
		return returnList;
	}


	private NGram createNGram(List<Integer> intervalList, Integer currentIndex, int nGramMagnitude) {
		NGram returnNGram;
		List<Integer> nGramIntervals = new ArrayList<>();
		for (int i = 0; i < nGramMagnitude; i++) {
			nGramIntervals.add(intervalList.get(currentIndex + i));
		}
		returnNGram = new NGram(nGramIntervals);
		return returnNGram;
	}
	private void incrementNGram(List<NGram> nGramList, NGram nGramToIncrement) {
		for (Iterator<NGram> it = nGramList.iterator(); it.hasNext();) {
			NGram n = it.next();
			if (n.equals(nGramToIncrement)) {
				n.incrementFrequency();
				break;
			}
		}
	}
}