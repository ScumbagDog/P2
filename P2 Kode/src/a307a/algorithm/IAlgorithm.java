package a307a.algorithm;

import a307a.midilib.parser.AMelody;

// Compare melody because we have to compare something
public interface IAlgorithm {
    String getName();

    double compareTo(AMelody midiMelody1, AMelody midiMelody2);

    void setNGramMagnitude(int magnitude);
}