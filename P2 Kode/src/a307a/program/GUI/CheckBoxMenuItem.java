package a307a.program.GUI;


import a307a.algorithm.Ukkonen;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;

public class CheckBoxMenuItem extends CheckBox {

    public VBox ListAlgorithm() {
         final int SizeOf = 5;
         Ukkonen algo1 = new Ukkonen();
         VBox vbox = new VBox();
         vbox.setTranslateX(50);
         vbox.setTranslateY(50);
         String findAlgorithm = algo1.getName();
         /*for (int i = 0; i <= SizeOf ; i++) {

         } */
         CheckBox cb1 = new CheckBox(findAlgorithm);
         cb1.setSelected(false);
         cb1.setIndeterminate(false);
         vbox.getChildren().addAll(cb1);

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
