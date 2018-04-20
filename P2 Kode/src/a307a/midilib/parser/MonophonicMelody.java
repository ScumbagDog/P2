package a307a.midilib.parser;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MonophonicMelody extends IMelody {
	MonophonicMelody(List<INote> notes){
		super(notes);
		this.notes = getMonophonicNotes(notes);
	}

	List<INote> getMonophonicNotes(List<INote> notes){
		List<INote> monophonic = new LinkedList<>();

		/* For each unique tick... */
		for(Long tick: notes.stream()
				.map(INote::getTick)
				.distinct()
				.collect(Collectors.toList())){
			/* ...add the note with the highest pitch. */
			monophonic.add(notes.stream()
					.filter(n -> n.getTick() == tick)
					.max(Comparator.comparingInt(INote::getPitch))
					.get()
			);
		}
		return monophonic;
	}
	@Override
	List<Integer> getPitchIntervals(){
		return null;
	}

	@Override
	List<INote> getNotes(){
		return this.notes;
	}

	@Override
	int size(){
		return 0;
	}
}
