package a307a.midilib.parser;
import java.util.List;

public interface NGrammifier {
	public List<List<Double>> getNGrams(List<T> inputNotes, int nGramMagnitude);
}
