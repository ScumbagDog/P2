package a307a.program.GUI;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CheckBoxMenuItem
{

    public VBox ListAlgorithm() {
         VBox vbox = new VBox();
         vbox.setTranslateX(50);
         vbox.setTranslateY(50);
         String findAlgorithm = getClass().getName();
         for (int i = 0; i <= 1; i++) {

         ComboBox Cb1 = new ComboBox();
         }
         vbox.getChildren();

         return vbox;
    }

    /* TODO dette er et midlertidigt løsning */
   /* VBox vbox = new VBox();
    vbox.setTranslateX(50);
    vbox.setTranslateY(50);
    CheckBox firstCheckBox = new CheckBox("Algoritme 1");
    CheckBox secondCheckBox = new CheckBox("Alogritme 2");
    CheckBox thirdCheckBox = new CheckBox("Algoritme 3");
    firstCheckBox.setSelected(false);
    secondCheckBox.setSelected(false);
    thirdCheckBox.setSelected(false); */

}
