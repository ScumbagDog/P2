package a307a.program.GUI;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SelectableAlgorithm {
    private String name;
    private CheckBox box;
    private TextField textField;

    public SelectableAlgorithm(String name) {
        this.name = name;
        box = new CheckBox(name);
        textField = new TextField();
        box.setSelected(false);
        box.setIndeterminate(false);
    }

    public String getName() {
        return name;
    }

    public CheckBox getBox() {
        return box;
    }

    public TextField getTextField() {
        return textField;
    }
}
