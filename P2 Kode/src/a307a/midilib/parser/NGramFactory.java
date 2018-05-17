package a307a.midilib.parser;

import java.util.*;

/*
 * NGramFactory creates NGrams from a list of intervals.
 */
public class NGramFactory implements INGramFactory{

	@Override
	public List<INGram> getNGrams(AMelody melody, int nGramMagnitude){
		return getNGrams(melody.getPitchIntervals(), nGramMagnitude);
	}

	@Override
	public List<INGram> getNonDistinct(
			AMelody melody, int nGramMagnitude
	){
		return getNonDistinct(melody.getPitchIntervals(), nGramMagnitude);
	}

	@Override
	public List<INGram> getNonDistinct(
			List<Integer> intervalList, int nGramMagnitude
	){
		List<INGram> returnList = new ArrayList<>();
		int i = 0;

		if(nGramMagnitude > 0){
			while(intervalList.size() >= (nGramMagnitude + i)){
				INGram newNGram = createNGram(intervalList.subList(i,
						i + nGramMagnitude
				));
				returnList.add(newNGram);
				i++;
			}
		}
		return returnList;
	}

	INGram createNGram(List<Integer> ints){
		return new NGram(ints);
	}

	public List<INGram> getNGrams(
			List<Integer> intervalList, int nGramMagnitude
	){
		List<INGram> returnList = new ArrayList<>();
		int i = 0;

		if(nGramMagnitude > 0){
			while(intervalList.size() >= (nGramMagnitude + i)){
				INGram newNGram = this.createNGram(intervalList,
						i,
						nGramMagnitude
				);
				if(returnList.contains(newNGram)){
					this.incrementNGram(returnList, newNGram);
				}
				else{
					returnList.add(newNGram);
				}
				i++;
			}
		}
		return returnList;
	}


	private INGram createNGram(
			List<Integer> intervalList, Integer currentIndex, int nGramMagnitude
	){
		INGram returnNGram;
		List<Integer> nGramIntervals = new ArrayList<>();
		for(int i = 0; i < nGramMagnitude; i++){
			nGramIntervals.add(intervalList.get(currentIndex + i));
		}
		returnNGram = new NGram(nGramIntervals);
		return returnNGram;
	}

	private void incrementNGram(
			List<INGram> nGramList, INGram nGramToIncrement
	){
		for(Iterator<INGram> it = nGramList.iterator(); it.hasNext(); ){
			INGram n = it.next();
			if(n.equals(nGramToIncrement)){
				((NGram) n).incrementFrequency();
				break;
			}
		}
	}
}