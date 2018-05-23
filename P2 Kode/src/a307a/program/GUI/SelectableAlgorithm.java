package a307a.program.GUI;

import a307a.algorithm.IAlgorithm;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SelectableAlgorithm {
    private String name;
    private CheckBox box;
    private TextField textField;
    private IAlgorithm algorithm;

    public SelectableAlgorithm(String name, IAlgorithm algorithm) {
        this.name = name;
        box = new CheckBox(name);
        textField = new TextField();
        box.setSelected(false);
        box.setIndeterminate(false);
        this.algorithm = algorithm;
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

    public IAlgorithm getAlgorithm() {
        return algorithm;
    }
}
