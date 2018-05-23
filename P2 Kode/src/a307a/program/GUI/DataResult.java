package a307a.program.GUI;

public class DataResult {

    private final String fileName;
    private final double resultValue;

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