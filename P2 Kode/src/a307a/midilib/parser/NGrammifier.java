package a307a.midilib.parser;
import java.util.List;

<<<<<<< Updated upstream
public interface NGrammifier {
	public List<NGram> getNGrams(List<Interval> IntervalList, int nGramMagnitude);
}
=======
public interface NGrammifier<T> {
	public List<NGram<T>> getNGrams(List<T> inputList, int nGramMagnitude);
}
>>>>>>> Stashed changes
