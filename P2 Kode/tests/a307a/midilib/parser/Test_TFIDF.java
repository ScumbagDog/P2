package a307a.midilib.parser;

import a307a.algorithm.AStatisticallyInformedAlgorithm;
import a307a.algorithm.TFIDFRelationAlgorithm;
import org.junit.jupiter.api.*;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test_TFIDF{
	final static String
			collectionPath
			= "P2 Kode/tests/MidiTestCollection/aof";
	Set<AMelody> melodyCollection;

	@BeforeAll
		//@SuppressWarnings("unchecked")
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
		AMelody m2 = msr.getMelody(1);

		double res = tfidf.compareTo(m1, m2);
		System.out.println("Comp res: " + res);
	}
}
