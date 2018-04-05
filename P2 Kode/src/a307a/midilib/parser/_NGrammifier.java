package a307a.midilib.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class _NGrammifier implements NGrammifier {

	public Set<NGram> getNGrams(List<Integer> intervalList, int nGramMagnitude) {
		Set<NGram> returnSet = new HashSet<>();
		int i = 0;
		System.out.println(intervalList.size());

		while (intervalList.size() >= (nGramMagnitude + i)) {
			NGram newNGram = this.createNGram(intervalList, i, nGramMagnitude);
			System.out.println(returnSet);
			if (returnSet.contains(newNGram)) {
				for(Iterator<NGram> it = returnSet.iterator(); it.hasNext(); ) {
					NGram n = it.next();
					if(n.equals(newNGram))
					{
						n.increment();
						break;
					}
				}
				
			} else {
				returnSet.add(newNGram);
			}
			i++;
		}
		return returnSet;
	}

	private NGram createNGram(List<Integer> intervalList, Integer currentIndex, int nGramMagnitude) {

		NGram returnNGram;
		List<Integer> nGramIntervals = new ArrayList<>();
		for (int i = 0; i < nGramMagnitude; i++) {
			nGramIntervals.add(intervalList.get(currentIndex + i));
		}
		returnNGram = new _NGram(1, nGramIntervals);
		return returnNGram;
	}
}
