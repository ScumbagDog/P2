package a307a.midilib.parser;

import javax.sound.midi.*;
import java.io.*;
import java.util.*;

public class Melody implements IMelody{
	private List<INote> notes;

	Melody(List<INote> notes){
		this.notes = this.createMonoPhonicNotes(notes);
	}

	private List<INote> createMonoPhonicNotes(List<INote> notes){
		return notes;
	}

	@Override
    public List<INote> getNotes(){
			return this.notes;
  }

  @Override
  public List<Integer> getIntervals(){
		return null;
  }
}