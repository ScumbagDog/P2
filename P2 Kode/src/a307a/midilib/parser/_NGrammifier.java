package a307a.midilib.parser;

import java.util.ArrayList;
import java.util.List;

class _NGrammifier implements NGrammifier{
	
	public List<NGram> getNGrams(List<Interval> intervalList, int nGramMagnitude){	
		return null;
		//TODO: Make exeption check on N gram magnitude 0
	}
	private NGram createNGram(List<Interval> intervalList, Interval currentInterval, int nGramMagnitude) {
		NGram returnNGram = null;
		List<Interval> nGramIntervals = new ArrayList<>();
		int currentIndex = intervalList.indexOf(currentInterval);
		if(intervalList.contains(intervalList.get(currentIndex + nGramMagnitude - 1))) {// -1 because N-Grams aren't zero-indexed
			for(int i = 0; i < nGramMagnitude; i++) {
				nGramIntervals.add(intervalList.get(currentIndex + i));
			}
		}
		return returnNGram;
	}
}
