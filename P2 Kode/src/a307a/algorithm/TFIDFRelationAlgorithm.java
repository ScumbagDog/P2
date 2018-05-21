package a307a.algorithm;

import a307a.midilib.parser.*;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

public class TFIDFRelationAlgorithm extends
		AStatisticallyInformedAlgorithm{
	public TFIDFRelationAlgorithm(
			Collection<AMelody> sourceMelodies, int magnitude
	){
		super(sourceMelodies, magnitude);
	}

	double TFIDF(
			AMelody melody,
			INGram term,
			BiFunction<Double, Double, Double> operator
	){
		return operator.apply(getTermFrequency(melody, term),
				getInvertedDocumentFrequency(term));
	}

	double sumUnion(
			AMelody m1, AMelody m2, BiFunction<Double, Double, Double> operator
	){
		List<INGram> terms1 = MidiTools.getNGrams(m1, magnitude);
		List<INGram> terms2 = MidiTools.getNGrams(m2, magnitude);

		double result = terms1.stream()
				.flatMap(t->terms2.stream())
				.mapToDouble(t->TFIDF(m1, t, operator) * TFIDF(m2, t, operator))
				.sum() / Math.sqrt(terms1.stream()
				.flatMap(t->terms2.stream())
				.mapToDouble(t->Math.pow(TFIDF(m1, t, operator), 2) * Math.pow(
						TFIDF(m1, t, operator),
						2))
				.sum());
		System.out.println(result);
		return result;
	}

	@Override
	public String getName(){
		return null;
	}

	@Override
	public double compareTo(
			AMelody midiMelody1, AMelody midiMelody2
	){
		return sumUnion(midiMelody1, midiMelody2, (d1, d2)->d1 * d2);
	}
}
