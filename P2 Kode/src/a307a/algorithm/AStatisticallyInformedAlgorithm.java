package a307a.algorithm;

import a307a.midilib.parser.AMelody;
import a307a.midilib.parser.INGram;
import a307a.midilib.parser.INGramFactory;
import a307a.midilib.parser.NGramFactory;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AStatisticallyInformedAlgorithm implements IAlgorithm{
	private final List<List<INGram>> listOfNGramLists;

	protected double getTermFrequency(AMelody melody, INGram melodicTerm){
		INGramFactory nFact = new NGramFactory();
		List<INGram> terms = nFact.getNGrams(melody, melodicTerm.getMagnitude());
		return melodicTerm.getFrequency() / terms.stream()
				.mapToInt(INGram::getFrequency)
				.sum();
	}
	protected double getInvertedDocumentFrequency(INGram meloditcTerm){
		int numberOfMelodies = listOfNGramLists.size();
		int numberOfMelodiesWithMelodicTerm = getNumberOfMelodiesWithMelodicTerm(meloditcTerm);
		double invertedDocumentFrequency = Math.log(
				numberOfMelodies / numberOfMelodiesWithMelodicTerm
		);
		return invertedDocumentFrequency;
	}

	/* Returns the number of melodies a term (NGram) appears in at least
	 * once from a list of melodies. */
	private int getNumberOfMelodiesWithMelodicTerm(INGram melodicTerm){
		int nGramMagnitude = melodicTerm.getMagnitude();

		return this.listOfNGramLists.stream()
				.filter(nGramList -> nGramList.stream()
						.anyMatch(melodicTerm::equals)
				)
				.collect(Collectors.toList())
				.size();
	}

	private List<List<INGram>> getListOfNGramLists(List<AMelody> melodies, int magnitude){
		INGramFactory nFact = new NGramFactory();
		return melodies.stream()
				.map(melody -> nFact.getNGrams(melody, magnitude))
				.collect(Collectors.toList());
	}

	public AStatisticallyInformedAlgorithm(List<AMelody> sourceMelodies, int magnitude){
		this.listOfNGramLists = getListOfNGramLists(sourceMelodies, magnitude);
	}
}