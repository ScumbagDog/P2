package a307a.algorithm;

import a307a.midilib.parser.AMelody2;

// Compare melody because we have to compare something
public interface IAlgorithm {
    public String getName();
    public double compareTo(AMelody2 midiMelody1, AMelody2 midiMelody2);
}