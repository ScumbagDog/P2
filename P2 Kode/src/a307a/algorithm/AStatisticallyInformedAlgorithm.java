package a307a.algorithm;

import a307a.midilib.parser.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AStatisticallyInformedAlgorithm implements
		IAlgorithm{
	int magnitude;
	private Collection<Collection<INGram>> nGramLists;

	public AStatisticallyInformedAlgorithm(){
		super();
	}

	/**
	 * @param sourceMelodies
	 * @param magnitude
	 */
	public AStatisticallyInformedAlgorithm(
			Collection<AMelody> sourceMelodies, int magnitude
	){
		this.magnitude = magnitude;
		this.nGramLists = getNGramLists(sourceMelodies, magnitude);
	}

	/**
	 * Sets the collection used for statistical comparison.
	 *
	 * @param melodyCollection
	 */
	public void setMelodyCollection(
			Collection<AMelody> melodyCollection
	){
		nGramLists = getNGramLists(melodyCollection, magnitude);
	}

	/**
	 * Sets the collection used for statistical comparison .
	 *
	 * @param melodyCollection
	 * @param magnitude
	 */
	public void setMelodyCollection(
			Collection<AMelody> melodyCollection, int magnitude
	){
		nGramLists = getNGramLists(melodyCollection, magnitude);
	}

	protected double getTermFrequency(AMelody melody, INGram melodicTerm){
		INGramFactory nFact = new NGramFactory();
		List<INGram> terms = nFact.getNGrams(melody,
				melodicTerm.getMagnitude());

		long frequency = terms.stream()
				.filter(melodicTerm::equals)
				.count();
		double res = (double) frequency / (double) terms.size();
		return res;
	}

	protected double getInvertedDocumentFrequency(INGram melodicTerm){
		double numberOfMelodies = nGramLists.size();

		double melodiesWithMelodicTerm = getNumberOfMelodiesWithMelodicTerm(
				melodicTerm);

		return melodiesWithMelodicTerm == 0.0 ? 0.0 : Math.log(
				numberOfMelodies / melodiesWithMelodicTerm);
	}

	/* Returns the number of melodies in source collection a term (NGram)
	 * appears in at least once . */
	private long getNumberOfMelodiesWithMelodicTerm(INGram melodicTerm){
		return nGramLists.stream()
				.filter(nGramList->nGramList.stream()
						.anyMatch(melodicTerm::equals))
				.count();
	}

	private Collection<Collection<INGram>> getNGramLists(
			Collection<AMelody> melodies, int magnitude
	){
		INGramFactory nFact = new NGramFactory();
		return melodies.stream()
				.map(melody->nFact.getNGrams(melody, magnitude))
				.collect(Collectors.toList());
	}
}