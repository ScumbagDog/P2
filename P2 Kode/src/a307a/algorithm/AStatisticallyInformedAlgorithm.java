package a307a.algorithm;

import a307a.midilib.parser.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AStatisticallyInformedAlgorithm implements IAlgorithm{
	final List<List<INGram>> nGramLists;
	final int magnitude;

	public AStatisticallyInformedAlgorithm(List<AMelody> sourceMelodies, int magnitude){
		this.magnitude = magnitude;
		this.nGramLists = getListOfNGramLists(sourceMelodies, magnitude);
	}

	protected double getTermFrequency(AMelody melody, INGram melodicTerm){
		INGramFactory nFact = new NGramFactory();
		List<INGram> terms = nFact.getNGrams(melody, melodicTerm.getMagnitude());
		return melodicTerm.getFrequency() / terms.stream()
				.mapToInt(INGram::getFrequency)
				.sum();
	}

	protected double getInvertedDocumentFrequency(INGram melodicTerm){
		int numberOfMelodies = nGramLists.size();
		int numberOfMelodiesWithMelodicTerm = getNumberOfMelodiesWithMelodicTerm
				(melodicTerm);
		double invertedDocumentFrequency = Math.log(numberOfMelodies
				/ numberOfMelodiesWithMelodicTerm);
		return invertedDocumentFrequency;
	}

	/* Returns the number of melodies a term (NGram) appears in at least
	 * once from a list of melodies. */
	private int getNumberOfMelodiesWithMelodicTerm(INGram melodicTerm){
		int nGramMagnitude = melodicTerm.getMagnitude();

		return this.nGramLists.stream()
				.filter(nGramList->nGramList.stream()
						.anyMatch(melodicTerm::equals))
				.collect(Collectors.toList())
				.size();
	}

	private List<List<INGram>> getListOfNGramLists(List<AMelody> melodies, int magnitude){
		INGramFactory nFact = new NGramFactory();
		return melodies.stream()
				.map(melody->nFact.getNGrams(melody, magnitude))
				.collect(Collectors.toList());
	}
}