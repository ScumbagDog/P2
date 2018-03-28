package a307a.program.backend;
import java.util.List;

import a307a.midilib.parser.Note;

public interface NGrammifyer {
	public List<List<Note>> getNGrams(List<Note> inputNotes, int nGramMagnitude);
}
