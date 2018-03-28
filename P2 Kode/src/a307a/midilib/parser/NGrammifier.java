package a307a.midilib.parser;
import java.util.List;

public interface NGrammifier<T> {
	public List<NGram<T>> getNGrams(List<T> inputList, int nGramMagnitude);
}
