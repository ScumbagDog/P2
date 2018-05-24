package a307a.algorithm;

import a307a.midilib.parser.*;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class TFIDFSumUnion extends AStatisticallyInformedAlgorithm{
	private int nGramMagnitude;

	public TFIDFSumUnion(){
		magnitude = 2;
	}

	public TFIDFSumUnion(
			Collection<AMelody> sourceMelodies, int magnitude
	){
		super(sourceMelodies, magnitude);
	}

	@Override
	public String getName(){
		return "TF-IDF Sum union";
	}

	@Override
	public double compareTo(
			AMelody midiMelody1, AMelody midiMelody2
	){
		double result = sumUnion(midiMelody1, midiMelody2, (d1, d2)->d1 * d2);

		double normalized = 1 / result;
		System.out.println("Res: " + normalized);
		return normalized;
	}

	@Override
	public void setNGramMagnitude(int magnitude){
		nGramMagnitude = magnitude;
	}

	private double TFIDF(
			AMelody melody,
			INGram term,
			BiFunction<Double, Double, Double> operator
	){
		double idf = getInvertedDocumentFrequency(term);
		double res = operator.apply(getTermFrequency(melody, term), idf);
		return res;
	}

	private double sumUnion(
			AMelody m1, AMelody m2, BiFunction<Double, Double, Double> operator
	){
		List<INGram> terms1 = MidiTools.getNGrams(m1, magnitude);
		List<INGram> terms2 = MidiTools.getNGrams(m2, magnitude);

		Set<INGram> union = terms1.stream()
				.flatMap(t->terms2.stream())
				.collect(Collectors.toSet());

		double dividend1 = union.stream()
				.mapToDouble(t->TFIDF(m1, t, operator))
				.sum();

		double dividend2 = union.stream()
				.mapToDouble(t->TFIDF(m2, t, operator))
				.sum();

		double divisor1 = Math.pow(union.stream()
				.mapToDouble(t->TFIDF(m1, t, operator))
				.sum(), 2);
		double divisor2 = Math.pow(union.stream()
				.mapToDouble(t->TFIDF(m1, t, operator))
				.sum(), 2);

		double result = (dividend1 * dividend2) / Math.sqrt(
				divisor1 * divisor2);
		return result;
	}

}
