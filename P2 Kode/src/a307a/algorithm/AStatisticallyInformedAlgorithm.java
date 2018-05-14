package a307a.algorithm;

import a307a.midilib.parser.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AStatisticallyInformedAlgorithm implements IAlgorithm{
	final List<List<INGram>> nGramLists;
	final int magnitude;

	public AStatisticallyInformedAlgorithm(
			Collection<AMelody> sourceMelodies, int magnitude
	){
		this.magnitude = magnitude;
		this.nGramLists = getListOfNGramLists(sourceMelodies, magnitude);
	}

	protected double getTermFrequency(AMelody melody, INGram melodicTerm){
		NGramFactory nf = new NGramFactory();
		List<INGram> grams = nf.getNonDistinct(melody,
				melodicTerm.getMagnitude()
		);
		double freq = grams.stream()
				.filter(e->e.equals(melodicTerm))
				.count();

		return melodicTerm.getFrequency() / (double) grams.size();
	}

	protected double getInvertedDocumentFrequency(INGram melodicTerm){
		int numberOfMelodies = nGramLists.size();
		int melodiesWithTerm = getNumberOfMelodiesWithMelodicTerm
				(melodicTerm);
		return melodiesWithTerm == 0 ?
				1.0 :
				Math.log(numberOfMelodies / melodiesWithTerm);
	}

	/* Returns the number of melodies a term (NGram) appears in at least
	 * once from a list of melodies. */
	private int getNumberOfMelodiesWithMelodicTerm(INGram melodicTerm){
		return this.nGramLists.stream()
				.filter(nGramList->nGramList.contains(melodicTerm))
				.collect(Collectors.toList())
				.size();
	}

	private List<List<INGram>> getListOfNGramLists(
			Collection<AMelody> melodies, int magnitude
	){
		INGramFactory nFact = new NGramFactory();
		return melodies.stream()
				.map(melody->nFact.getNGrams(melody, magnitude))
				.collect(Collectors.toList());
	}
}