package a307a.algorithm;

import java.io.File;
import java.util.List;

import a307a.midilib.parser.*;

public class Ukkonen implements IAlgorithm {
	private final String name = "Ukkonen Measure Algorithm";
	private int nGramMagnitude = 1;

	public String getName() {
		return name;
	}

	public int getNGramMagnitude() {
		return nGramMagnitude;
	}

	public void setNGramMagnitude(int nGramMagnitude) {
		this.nGramMagnitude = nGramMagnitude;
	}

	public double compareTo(Melody midiMelody1, Melody midiMelody2) {
		INGramFactory nGramFactory = new NGramFactory();
		List<INGram> firstMelodyNGrams = nGramFactory.getNGrams(midiMelody1.getPitchIntervals(), nGramMagnitude);
		List<INGram> secondMelodyNGrams = nGramFactory.getNGrams(midiMelody2.getPitchIntervals(), nGramMagnitude);
		int nGramFrequencyDifferenceSum = 0;
		
		for (INGram n : firstMelodyNGrams) {
			if (secondMelodyNGrams.contains(n)) {
				INGram theNGram = secondMelodyNGrams.stream().filter(x -> x.equals(n)).findFirst().orElse(null);
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
		
		return 1 - (nGramFrequencyDifferenceSum / (firstMelodyNGrams.size() + secondMelodyNGrams.size()));
		// Not yet implemented
	}
}
