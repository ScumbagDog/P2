package a307a.algorithm;

import java.io.File;

public class Algorithm implements IAlgorithm {
    private String name;
    Algorithm (File inputFile) {
        this.name = inputFile.getName();
    }
    public String getName(){
        return name;
    }

}
