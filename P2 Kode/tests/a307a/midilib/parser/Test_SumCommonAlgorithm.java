package a307a.midilib.parser;

import a307a.algorithm.IAlgorithm;
import a307a.algorithm.SumCommon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_SumCommonAlgorithm {
    File file1 = new File("P2 Kode/Mester-Jakob.mid");
    File file2 = new File("P2 Kode/March-i-G.mid");
    AMidiSequenceReader msr1 = MidiTools.getMidiSequenceReader(file1);
    AMidiSequenceReader msr2 = MidiTools.getMidiSequenceReader(file2);

    IAlgorithm sumC = new SumCommon(2);

    public Test_SumCommonAlgorithm() throws InvalidMidiDataException, IOException {
    }

    /* Test to see if two identical songs give the value 1. */
    @Test
    void test1() {
        AMelody mel1 = msr1.getMelody(0);
        AMelody mel2 = msr1.getMelody(0);
        double compRes = sumC.compareTo(mel1, mel2);
        System.err.println(compRes);
    }

    /* Test with very different melody. */
    @Test
    void test2() {
        AMelody mel1 = msr1.getMelody(0);
        AMelody mel2 = msr2.getMelody(0);
        double compRes = sumC.compareTo(mel1, mel2);
        System.err.println(compRes);
    }

    @Test
    void test3() {
        AMelody mel1 = msr2.getMelody(0);
        AMelody mel2 = msr2.getMelody(1);
        double compRes = sumC.compareTo(mel1, mel2);
        System.err.println(compRes);
    }

    @Test
    void test4() {
        AMelody mel1 = msr2.getMelody(1);
        AMelody mel2 = msr2.getMelody(2);
        double compRes = sumC.compareTo(mel1, mel2);
        System.err.println(compRes);
    }
}
