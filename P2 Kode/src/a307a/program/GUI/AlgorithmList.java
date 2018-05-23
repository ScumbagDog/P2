package a307a.program.GUI;

import a307a.algorithm.SumCommon;
import a307a.algorithm.TFIDFSumUnion;
import a307a.algorithm.Ukkonen;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmList extends CheckBox {
    /* This class exists to allow the user to select which algorithms
     * they want to compare with. */
    public static List<SelectableAlgorithm> listAlgorithm() {
        //TODO: NGrams-algoritmer skal kende MAGNITUDEN af NGrams.
        List<SelectableAlgorithm> algorithmList = new ArrayList<>();
        algorithmList.add(new SelectableAlgorithm("Ukkonen Measure Algorithm", new Ukkonen()));
        algorithmList.add(new SelectableAlgorithm("Sum Common Measure Algorithm", new SumCommon()));
        algorithmList.add(new SelectableAlgorithm("TFIDF Sum Union Algorithm", new TFIDFSumUnion()));

        return algorithmList;
    }
}
/* TODO dette er et midlertidigt løsning */
     /*   VBox vBox = new VBox();
        CheckBox firstCheckBox = new CheckBox("Algorithm 1");
        CheckBox secondCheckBox = new CheckBox("Alogrithm 2");
        CheckBox thirdCheckBox = new CheckBox("Algorithm 3");
        firstCheckBox.setSelected(false);
        secondCheckBox.setSelected(false);
        thirdCheckBox.setSelected(false);
        vBox.getChildren().addAll(firstCheckBox, secondCheckBox, thirdCheckBox);

        return vBox;
    }
}
*/