package a307a.program.GUI;

import a307a.algorithm.Ukkonen;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmList extends CheckBox {

    public static List<CheckBox> ListAlgorithm() {
        List<CheckBox> checkboxList = new ArrayList<>();
        List<String> algorithmName = new ArrayList<>();
        Ukkonen ukonnen = new Ukkonen();
        algorithmName.add(ukonnen.getName());
         for (int i = 0; i < algorithmName.size() ; i++) {
            checkboxList.add(new CheckBox(algorithmName.get(i)));
            checkboxList.get(i).setSelected(false);
            checkboxList.get(i).setIndeterminate(false);
         }

        return checkboxList;
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