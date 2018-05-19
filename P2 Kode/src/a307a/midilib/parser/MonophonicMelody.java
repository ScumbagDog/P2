package a307a.midilib.parser;

import java.util.*;
import java.util.stream.Collectors;

class MonophonicMelody extends AMelody{
	MonophonicMelody(List<INote> notes){
		super(getMonophonicNotes(notes));
	}

	/* Returns a monophonic list of note (only one note at
	 * any given moment) by taking the note with highest pitch. */
	private static List<INote> getMonophonicNotes(List<INote> notes){
		List<INote> monophonic = new LinkedList<>();

		/* For each unique tick... */
		for(Long tick : notes.stream()
				.map(INote::getTick)
				.distinct()
				.collect(Collectors.toList())){
			/* ...add the note with the highest pitch. */
			monophonic.add(notes.stream()
					.filter(n->n.getTick() == tick)
					.max(Comparator.comparingInt(INote::getPitch))
					.get());
		}
		return monophonic;
	}
}