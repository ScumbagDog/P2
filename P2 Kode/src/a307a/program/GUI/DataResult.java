package a307a.program.GUI;

import javafx.beans.property.SimpleStringProperty;

public class DataResult{

	public final SimpleStringProperty fileName;
	public final double resultValue;

	public DataResult(String fileName, double resultValue){
		this.fileName = new SimpleStringProperty(fileName);
		this.resultValue = resultValue;

	}

	public String getFileName(){
		return fileName.get();
	}

	public double getResultValue(){
		return resultValue;
	}

}