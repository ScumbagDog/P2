package a307a.midilib.parser;

import java.util.*;

public class Melody implements IMelody{
	private final List<INote> notes;
	private final int notesSize;

	Melody(List<INote> notes){
		this.notes = this.getMonophonicNotes(notes);
		this.notesSize = this.notes.size();
	}

	private List<INote> getMonophonicNotes(List<INote> notes){
		List<INote> monophonicNotes = new LinkedList<>();

		for(int i = 0; i < notes.size() - 1; i++){
			INote firstNote = notes.get(i);
			INote secondNote = notes.get(i+1);
			if(firstNote.getTick() == secondNote.getTick()){
				monophonicNotes.add(firstNote.getPitch() >= secondNote.getPitch()?
						firstNote.clone() : secondNote.clone());
				i--;
			}
			else
				monophonicNotes.add(firstNote);
		}
		monophonicNotes.add(notes.get(notesSize).clone());
		return monophonicNotes;
	}

	@Override
  public List<INote> getNotes(){
			return this.notes;
  }

  @Override
  public List<Integer> getPitchIntervals(){
		List<Integer> intervals = new LinkedList<>();
		intervals.add(0);
		for(int i = 0; i < this.notesSize-1; i++)
			intervals.add(this.notes.get(i+1).getPitch()
					- this.notes.get(i).getPitch());

		return intervals;
  }

  @Override
  public int size(){
		return this.notesSize;
  }
}