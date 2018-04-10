package a307a.midilib.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class NGramFactoryTest {

	@Test
	void oneGramTest() {
		List<Integer> intervals = new ArrayList<>();
		List<INGram> nGrams = new ArrayList<>();
		List<INGram> expectedNGrams = new ArrayList<>();
		// To takter af Mester Jakob
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		intervals.add(0);
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		INGramFactory nGramFactory = new NGramFactory();
		nGrams = nGramFactory.getNGrams(intervals, 1);
		intervals.clear();

		INGram newNGram;
		intervals.add(2);
		newNGram = new NGram(intervals);
		newNGram.incrementFrequency();
		newNGram.incrementFrequency();
		newNGram.incrementFrequency();
		expectedNGrams.add(newNGram);
		intervals.clear();

		intervals.add(-4);
		newNGram = new NGram(intervals);
		newNGram.incrementFrequency();
		expectedNGrams.add(newNGram);
		intervals.clear();

		intervals.add(0);
		newNGram = new NGram(intervals);
		expectedNGrams.add(newNGram);

		assertEquals(expectedNGrams, nGrams);
		for(INGram n : nGrams)
		{
			for(INGram k : expectedNGrams)
			{
				if (n.equals(k)) {
					assertEquals(k.getFrequency(), n.getFrequency());
				}
			}
		}
	}
	
	@Test
	void twoGramTest() {
		List<Integer> intervals = new ArrayList<>();
		List<INGram> nGrams = new ArrayList<>();
		List<INGram> expectedNGrams = new ArrayList<>();
		// To takter af Mester Jakob
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		intervals.add(0);
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		INGramFactory nGramFactory = new NGramFactory();
		nGrams = nGramFactory.getNGrams(intervals, 2);
		intervals.clear();

		INGram newNGram;
		intervals.add(2);
		intervals.add(2);
		newNGram = new NGram(intervals);
		newNGram.incrementFrequency();
		expectedNGrams.add(newNGram);
		intervals.clear();

		intervals.add(2);
		intervals.add(-4);
		newNGram = new NGram(intervals);
		newNGram.incrementFrequency();
		expectedNGrams.add(newNGram);
		intervals.clear();

		intervals.add(-4);
		intervals.add(0);
		newNGram = new NGram(intervals);
		expectedNGrams.add(newNGram);
		intervals.clear();
		
		intervals.add(0);
		intervals.add(2);
		newNGram = new NGram(intervals);
		expectedNGrams.add(newNGram);

		assertEquals(expectedNGrams, nGrams);
		for(INGram n : nGrams)
		{
			for(INGram k : expectedNGrams)
			{
				if (n.equals(k)) {
					assertEquals(k.getFrequency(), n.getFrequency());
				}
			}
		}
	}
	
	@Test
	void threeGramTest() {
		List<Integer> intervals = new ArrayList<>();
		List<INGram> nGrams = new ArrayList<>();
		List<INGram> expectedNGrams = new ArrayList<>();
		// To takter af Mester Jakob
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		intervals.add(0);
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		INGramFactory nGramFactory = new NGramFactory();
		nGrams = nGramFactory.getNGrams(intervals, 3);
		intervals.clear();

		INGram newNGram;
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		newNGram = new NGram(intervals);
		newNGram.incrementFrequency();
		expectedNGrams.add(newNGram);
		intervals.clear();

		intervals.add(2);
		intervals.add(-4);
		intervals.add(0);
		newNGram = new NGram(intervals);
		expectedNGrams.add(newNGram);
		intervals.clear();

		intervals.add(-4);
		intervals.add(0);
		intervals.add(2);
		newNGram = new NGram(intervals);
		expectedNGrams.add(newNGram);
		intervals.clear();
		
		intervals.add(0);
		intervals.add(2);
		intervals.add(2);
		newNGram = new NGram(intervals);
		expectedNGrams.add(newNGram);

		assertEquals(expectedNGrams, nGrams);
		for(INGram n : nGrams)
		{
			for(INGram k : expectedNGrams)
			{
				if (n.equals(k)) {
					assertEquals(k.getFrequency(), n.getFrequency());
				}
			}
		}
	}
	
	@Test
	void negativeMagnitude()
	{
		List<Integer> intervals = new ArrayList<>();
		List<INGram> nGrams = new ArrayList<>();
		// To takter af Mester Jakob
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		intervals.add(0);
		intervals.add(2);
		intervals.add(2);
		intervals.add(-4);
		INGramFactory nGramFactory = new NGramFactory();
		nGrams = nGramFactory.getNGrams(intervals, -1);
		assertTrue(nGrams.isEmpty());
	}
}
