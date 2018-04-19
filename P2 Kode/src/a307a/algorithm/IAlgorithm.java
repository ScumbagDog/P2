package a307a.algorithm;

import java.io.File;

import a307a.midilib.parser.Melody;

// Compare melody because we have to compare something
public interface IAlgorithm {
    public String getName();
    public double compareTo(Melody midiMelody1, Melody midiMelody2);
}