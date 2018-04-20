package a307a.program.GUI;

import a307a.algorithm.Ukkonen;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;

public class AlgorithmList extends CheckBox {

    public static VBox ListAlgorithm() {
        final int SizeOf = 5;
        Ukkonen algo1 = new Ukkonen();
        VBox vbox = new VBox();
        String findAlgorithm = algo1.getName();
         /*for (int i = 0; i <= SizeOf ; i++) {

         } */

        CheckBox cb1 = new CheckBox(findAlgorithm);
        cb1.setSelected(false);
        cb1.setIndeterminate(false);
        vbox.getChildren().addAll(cb1);

        return vbox;
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