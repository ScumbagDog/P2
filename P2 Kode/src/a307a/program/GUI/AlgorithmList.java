package a307a.program.GUI;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public  class AlgorithmList
{

    public static VBox ListAlgorithm() {
         /*
         VBox vbox = new VBox();
         vbox.setTranslateX(50);
         vbox.setTranslateY(50);
         String findAlgorithm = getClass().getName();
         for (int i = 0; i <= 1; i++) {

         CheckBox cb1 = new CheckBox();
         }
         vbox.getChildren();

         return vbox;
    }
*/
        /* TODO dette er et midlertidigt løsning */
        VBox vBox = new VBox();
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
