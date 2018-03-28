package a307a.midilib.parser;
import java.util.List;

public interface NGrammifizierer {
	public List<List<Note>> getNGrams(List<Note> inputNotes, int nGramMagnitude);
}
