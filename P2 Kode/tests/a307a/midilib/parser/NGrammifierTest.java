package a307a.midilib.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class NGrammifierTest {

	@Test
	void oneGramTest() {
		List<Integer> intervals = new ArrayList<>();
		Set<NGram> nGrams = new HashSet<>();
		Set<NGram> expectedNGrams = new HashSet<>();
		 //To takter af Mester Jakob
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		intervals.add(0);
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		NGrammifier nGrammifier = new _NGrammifier();
		nGrams = nGrammifier.getNGrams(intervals, 1);
		intervals.clear();
		
		intervals.add(2);
		expectedNGrams.add(new _NGram(4 ,intervals));
		intervals.clear();
		
		intervals.add(-4);
		expectedNGrams.add(new _NGram(2 ,intervals));
		intervals.clear();
		
		intervals.add(0);
		expectedNGrams.add(new _NGram(1 ,intervals));

		assertEquals(expectedNGrams, nGrams);
	}

}
