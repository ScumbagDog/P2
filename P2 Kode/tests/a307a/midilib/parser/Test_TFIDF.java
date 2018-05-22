package a307a.midilib.parser;

import a307a.algorithm.AStatisticallyInformedAlgorithm;
import a307a.algorithm.TFIDFRelationAlgorithm;
import org.junit.jupiter.api.*;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_TFIDF{
	final static String collectionPath = "P2 Kode/tests/MidiTestCollection/aof";
	Set<AMelody> melodyCollection;
	private AMidiSequenceReader msr;

	@BeforeAll
	void beforeall(){
		melodyCollection = new HashSet<>();
		File folder = new File(collectionPath);
		assert folder != null;

		File[] files = folder.listFiles();

		assert files != null;
		melodyCollection = Arrays.stream(files)
				.filter(File::isFile)
				.filter(f->f.getName()
						.endsWith(".mid"))
				.map(midiFile->{
					try{
						return MidiTools.getMidiSequenceReader(midiFile);
					}catch(InvalidMidiDataException | IOException e){
						e.printStackTrace();
					}
					return null;
				})
				.filter(Objects::nonNull)
				.flatMap(msr->msr.getChannels()
						.stream()
						.map(msr::getMelody))
				.collect(Collectors.toSet());
		assert melodyCollection != null;
		assert !melodyCollection.isEmpty();
	}

	@Test
	void test1() throws InvalidMidiDataException, IOException{
		AStatisticallyInformedAlgorithm tfidf = new TFIDFRelationAlgorithm(
				melodyCollection,
				2);
		AMidiSequenceReader msr = MidiTools.getMidiSequenceReader(new File(
				"P2 " + "Kode/March-i-G.mid"));
		AMelody m1 = msr.getMelody(0);
		AMelody m2 = msr.getMelody(0);
		double result = tfidf.compareTo(m1, m2);

		/* Assert comparison with same melody returns 1.0*/
		assertEquals(1.0, result);

		m2 = msr.getMelody(1);
		result = tfidf.compareTo(m1, m2);
		assertTrue(result < 1.0);

		m2 = msr.getMelody(2);
		result = tfidf.compareTo(m1, m2);
		assertTrue(result < 1.0);
	}

	@Test
	void test2() throws InvalidMidiDataException, IOException{
		AStatisticallyInformedAlgorithm tfidf = new TFIDFRelationAlgorithm(
				melodyCollection,
				2);
		AMidiSequenceReader msr = MidiTools.getMidiSequenceReader(new File(
				"P2 Kode/March-i-G.mid"));

		AMelody melody1 = msr.getMelody(0);

		/* Get a melody from another voice of the piece - should be very
		 * similar*/
		AMelody melody1Almost = msr.getMelody(1);

		double resultAlmostSame = tfidf.compareTo(melody1, melody1Almost);

		/* Assert that even though the melodies are similar, they are not
		identical. */
		assertTrue(resultAlmostSame < 1.0);

		/* Get a melody from a very different piece. */
		msr = MidiTools.getMidiSequenceReader(new File(
				"P2 " + "Kode/tests/Mester-Jakob.mid"));
		AMelody veryDifferent = msr.getMelody(0);

		/* Assert that the very different melody return a smaller value. */
		double resultVeryDifferent = tfidf.compareTo(melody1Almost,
				veryDifferent);
		assertTrue(resultAlmostSame > resultVeryDifferent);
	}
}