package a307a.algorithm;

import java.util.List;

import a307a.midilib.parser.AMelody;
import a307a.midilib.parser.INGram;
import a307a.midilib.parser.INGramFactory;
import a307a.midilib.parser.NGramFactory;
/*
 * This class is an implementation of the Ukkonen measure algorithm.
 * It measures the similarity of two melodies based on the absolute difference in frequencies of equal NGrams in two melodies
 */
public class Ukkonen implements IAlgorithm {
	private final String name = "Ukkonen Measure Algorithm";
	private int nGramMagnitude;
	
	public Ukkonen(int nGramMagnitude){
		this.nGramMagnitude = nGramMagnitude;
	}

	public String getName() {
		return name;
	}

	public int getNGramMagnitude() {
		return nGramMagnitude;
	}

	public void setNGramMagnitude(int nGramMagnitude) {
		this.nGramMagnitude = nGramMagnitude;
	}

	public double compareTo(AMelody midiMelody1, AMelody midiMelody2) {
		INGramFactory nGramFactory = new NGramFactory();
		List<INGram> firstMelodyNGrams = nGramFactory.getNGrams(midiMelody1.getPitchIntervals(), nGramMagnitude);
		List<INGram> secondMelodyNGrams = nGramFactory.getNGrams(midiMelody2.getPitchIntervals(), nGramMagnitude);
		int nGramFrequencyDifferenceSum = 0;
		int amountOfNGrams = 0;

		for(INGram n : firstMelodyNGrams) {
			amountOfNGrams += n.getFrequency();
		}
		for(INGram n : secondMelodyNGrams) {
			amountOfNGrams += n.getFrequency();
		}
		
		for (INGram n : firstMelodyNGrams) {
			if (secondMelodyNGrams.contains(n)) {
				INGram theNGram = this.findNGramInList(secondMelodyNGrams, n);
				nGramFrequencyDifferenceSum += Math.abs(n.getFrequency() - theNGram.getFrequency());
			} else {
				nGramFrequencyDifferenceSum += n.getFrequency();
			}
		}
		for(INGram n :secondMelodyNGrams) {
			if(!firstMelodyNGrams.contains(n)) {
				nGramFrequencyDifferenceSum += n.getFrequency();
			}
		}
		return 1 - ((double)nGramFrequencyDifferenceSum / (double)amountOfNGrams);
	}
	INGram findNGramInList(List<INGram> listWithNGram, INGram nGramToFind) {
		return listWithNGram.stream()
		.filter(x -> x.equals(nGramToFind))
		.findFirst()
		.orElse(null);
	}
}