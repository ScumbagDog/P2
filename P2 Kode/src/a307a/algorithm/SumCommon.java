package a307a.algorithm;

import a307a.midilib.parser.*;

import java.util.List;

public class SumCommon implements IAlgorithm{
	private final String name = "Sum Common Measure Algorithm";
	private int nGramMagnitude;

	public SumCommon(int nGramMagnitude){
		this.nGramMagnitude = nGramMagnitude;
	}

	public String getName(){
		return name;
	}

	public int getNGramMagnitude(){
		return nGramMagnitude;
	}

	public void setNGramMagnitude(int nGramMagnitude){
		this.nGramMagnitude = nGramMagnitude;
	}

	public double compareTo(AMelody midiMelody1, AMelody midiMelody2){
		INGramFactory nGramFactory = new NGramFactory();
		List<INGram> firstMelodyNGrams
				= nGramFactory.getNGrams(midiMelody1.getPitchIntervals(), nGramMagnitude);
		List<INGram> secondMelodyNGrams
				= nGramFactory.getNGrams(midiMelody2.getPitchIntervals(), nGramMagnitude);
		int nGramFrequencyCommonSum = 0;
		int amountOfNGrams = 0;

		for(INGram n : firstMelodyNGrams){
			amountOfNGrams += n.getFrequency();
		}

		for(INGram n : secondMelodyNGrams){
			amountOfNGrams += n.getFrequency();
		}

		for(INGram n : firstMelodyNGrams){
			if(secondMelodyNGrams.contains(n)){
				INGram theNGram = this.findNGramInList(secondMelodyNGrams, n);
				nGramFrequencyCommonSum += (n.getFrequency() + theNGram.getFrequency());
			}
		}

		return ((double) nGramFrequencyCommonSum / (double) amountOfNGrams);
	}

	INGram findNGramInList(List<INGram> listWithNGram, INGram nGramToFind){
		return listWithNGram.stream()
				.filter(x->x.equals(nGramToFind))
				.findFirst()
				.orElse(null);
	}
}
