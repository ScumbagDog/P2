package a307a.midilib.parser;

import javax.sound.midi.*;
import java.io.*;
import java.util.*;

public class Melody implements IMelody{
	private final List<INote> notes;
	private final int notesSize;
	Melody(List<INote> notes){
		this.notes = this.getMonophonicNotes(notes);
		this.notesSize = this.notes.size();
	}

	private List<INote> getMonophonicNotes(List<INote> notes){
		List<INote> monophonic = new LinkedList<>(notes.subList(0, notes.size()));
		int monophonicSize = monophonic.size();

		for(int i = 0; i < monophonicSize - 1; i++){
			INote firstNote = monophonic.get(i);
			INote secondNote = monophonic.get(i);
			long tick1 = firstNote.getTick();
			long tick2 = secondNote.getTick();
			if(tick1 == tick2){
				List<INote> congruentNotes = new LinkedList<INote>();
				congruentNotes.add(firstNote);

				for(int j = i + 1; j < monophonicSize
						&& secondNote.getTick() == tick1; j++)
					congruentNotes.add(monophonic.get(j));

				int highestPitch = 0;
				while(congruentNotes.size() > 1)
					for(INote n : congruentNotes)
						if(n.getPitch() < highestPitch)
							congruentNotes.remove(n);
						else
							highestPitch = n.getPitch();

				monophonicSize = monophonic.size();
			}
		}
		return monophonic;
	}

	@Override
  public List<INote> getNotes(){
			return this.notes;
  }

  @Override
  public List<Integer> getPitchIntervals(){
		List<Integer> intervals = new LinkedList<>();

		for(int i = 0; i < this.notesSize-1; i++)
			intervals.add(this.notes.get(i).getPitch()
					- this.notes.get(i+1).getPitch());

		return intervals;
  }
}