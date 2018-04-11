package a307a.program.GUI;

import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;



public class CompareButton {


    public static Button AddButton() {




        //Her laves compareknappen og sættes til at skrive "Comparing..." når man klikker på den
        Button compareButton = new Button();
        compareButton.setText("Compare");
        compareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Comparing...");
            }
        });

     return compareButton;
    }

}