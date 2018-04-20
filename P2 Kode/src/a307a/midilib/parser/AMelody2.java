package a307a.midilib.parser;

import java.util.*;

public class AMelody2 extends IMelody{
	AMelody2(List<INote> notes){
		super(notes);
	}

	@Override
  public List<INote> getNotes(){
			return this.notes;
  }

  @Override
  public List<Integer> getPitchIntervals(){
		List<Integer> intervals = new LinkedList<>();
		int notesSize = this.notes.size();
		for(int i = 0; i < notesSize-1; i++)
			intervals.add(this.notes.get(i+1).getPitch()
					- this.notes.get(i).getPitch());

		return intervals;
  }

  @Override
  public int size(){
		return this.notes.size();
  }
}