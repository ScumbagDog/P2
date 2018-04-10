package a307a.midilib.parser;

import org.junit.jupiter.api.Test;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MelodyTests {

    Melody melody = new Melody();
    File file = new File("a307a/midilib/parser/Mester-Jakob.mid");

    private static Set<Integer> CodeExecution(){
        Set<Integer> testSet = new TreeSet<>();
        testSet.add(5);
        testSet.add(0);

        return testSet;
    }

    @Test
    public void ChannelCountTest() throws IOException, InvalidMidiDataException {
        assertEquals(MelodyTests.CodeExecution().size(), melody.getChannels(file).size());
    }
}
