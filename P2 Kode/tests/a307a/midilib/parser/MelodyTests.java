package a307a.midilib.parser;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class MelodyTests {
    File file = new File("a307a/midilib/parser/Mester-Jakob.mid");
    AMelody melody = null;

    private static Set<Integer> CodeExecution() {
        Set<Integer> testSet = new TreeSet<>();
        testSet.add(5);
        testSet.add(0);

        return testSet;
    }

//    @Test
//    public void ChannelCountTest() throws IOException, InvalidMidiDataException {
//        assertEquals(MelodyTests.CodeExecution().size(), melody.getChannels(file).size());
//    }
}
