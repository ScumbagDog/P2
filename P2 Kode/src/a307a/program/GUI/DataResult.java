package a307a.program.GUI;

import javafx.beans.property.SimpleStringProperty;

public class DataResult {

    public final SimpleStringProperty fileName;
    public final double resultValue;

    public DataResult(String fName, double rValue) {
        this.fileName = new SimpleStringProperty(fName);
        this.resultValue = rValue;

    }

    public String getFileName() {
        return fileName.get();
    }

    public void setFileName(String fName) {
        fileName.set(fName);
    }

    public double getResultValue() {
        return resultValue;
    }

}