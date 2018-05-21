package a307a.algorithm;

import a307a.midilib.parser.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AStatisticallyInformedAlgorithm implements
		IAlgorithm{
	final Collection<List<INGram>> nGramLists;
	final int magnitude;

	public AStatisticallyInformedAlgorithm(
			Collection<AMelody> sourceMelodies, int magnitude
	){
		this.magnitude = magnitude;
		this.nGramLists = getNGramLists(sourceMelodies, magnitude);
	}

	protected double getTermFrequency(AMelody melody, INGram melodicTerm){
		INGramFactory nFact = new NGramFactory();
		List<INGram> terms = nFact.getNGrams(melody,
				melodicTerm.getMagnitude());

		long frequency = terms.stream()
				.filter(melodicTerm::equals)
				.count();
		return (double) frequency / (double) terms.size();
	}

	protected double getInvertedDocumentFrequency(INGram melodicTerm){
		int numberOfMelodies = nGramLists.size();
		return Math.log(
				numberOfMelodies / (double) getNumberOfMelodiesWithMelodicTerm(
						melodicTerm));
	}

	/* Returns the number of melodies in source collection a term (NGram)
	 * appears in at least once . */
	private long getNumberOfMelodiesWithMelodicTerm(INGram melodicTerm){
		return this.nGramLists.stream()
				.filter(nGramList->nGramList.stream()
						.anyMatch(melodicTerm::equals))
				.count();
	}

	private Collection<List<INGram>> getNGramLists(
			Collection<AMelody> melodies, int magnitude
	){
		INGramFactory nFact = new NGramFactory();
		return melodies.stream()
				.map(melody->nFact.getNGrams(melody, magnitude))
				.collect(Collectors.toList());
	}
}