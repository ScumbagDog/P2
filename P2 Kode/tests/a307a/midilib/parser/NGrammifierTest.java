package a307a.midilib.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class NGrammifierTest {

	@Test
	void OneGramTest() {
		List<Interval> intervals = new ArrayList<Interval>();
		List<NGram> nGrams = new ArrayList<NGram>();
		intervals.add(new Interval(0., 2.));
		intervals.add(new Interval(2., 2.));
		intervals.add(new Interval(2., 2.));
		intervals.add(new Interval(0., -4.));
		NGrammifier nGrammifier = new _NGrammifier();
		nGrams = nGrammifier.getNGrams(intervals, 1);
	}

}
