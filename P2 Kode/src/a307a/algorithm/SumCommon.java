package a307a.algorithm;

import java.util.List;

import a307a.midilib.parser.INGram;
import a307a.midilib.parser.INGramFactory;
import a307a.midilib.parser.IMelody;
import a307a.midilib.parser.NGramFactory;

public class SumCommon implements IAlgorithm{
	private final String name = "Sum Common Measure Algorithm";
	private int nGramMagnitude;
	
	public SumCommon(int nGramMagnitude){
		this.nGramMagnitude = nGramMagnitude;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNGramMagnitude() {
		return nGramMagnitude;
	}

	public void setNGramMagnitude(int nGramMagnitude) {
		this.nGramMagnitude = nGramMagnitude;
	}
	
	public double compareTo(IMelody midiMelody1, IMelody midiMelody2) {
		INGramFactory nGramFactory = new NGramFactory();
		List<INGram> firstMelodyNGrams = nGramFactory.getNGrams(midiMelody1.getPitchIntervals(), nGramMagnitude);
		List<INGram> secondMelodyNGrams = nGramFactory.getNGrams(midiMelody2.getPitchIntervals(), nGramMagnitude);
		int nGramFrequencyCommonSum = 0;
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
				nGramFrequencyCommonSum += (n.getFrequency() + theNGram.getFrequency());
			}
		}
		
		return (nGramFrequencyCommonSum / amountOfNGrams);
	}
	
	INGram findNGramInList(List<INGram> listWithNGram, INGram nGramToFind) {
		return listWithNGram.stream()
		.filter(x -> x.equals(nGramToFind))
		.findFirst()
		.orElse(null);
	}
}
