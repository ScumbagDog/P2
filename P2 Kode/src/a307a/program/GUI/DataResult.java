package a307a.program.GUI;

import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class DataResult {

    public final String fileName;
    public final double resultValue;

    public DataResult(String fileName, double resultValue) {
        this.fileName = fileName;
        this.resultValue = resultValue;

    }

    public String getFileName() {
        return fileName;
    }

    public double getResultValue() {
        return resultValue;
    }

    public String getEntry(){
        return fileName + ": " + Double.toString(resultValue);
    }

}