package a307a.algorithm;

import a307a.midilib.parser.IMelody;

// Compare melody because we have to compare something
public interface IAlgorithm {
    public String getName();
    public double compareTo(IMelody midiMelody1, IMelody midiMelody2);
}