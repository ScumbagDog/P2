package a307a.program.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Resultlist {
    public TableView AddResultTable() {
     final ObservableList<DataResult> data = FXCollections.observableArrayList(
            new DataResult("Midifile1", 0.5),
            new DataResult("Midifile2", 0.7)
    );


        TableView table = new TableView();
        Pane pane = new Pane();


        TableColumn fileName1 = new TableColumn("Files");
        fileName1.setCellValueFactory(
                new PropertyValueFactory<DataResult, String>("fileName"));

        TableColumn result = new TableColumn("Result");
        result.setCellValueFactory(
                new PropertyValueFactory<DataResult, String>("resultValue"));


        table.setItems(data);
        table.getColumns().addAll(fileName1, result);


        final VBox vbox = new VBox();
        vbox.getChildren().add(table);

        pane.getChildren().add(vbox);
        return table;
    }

    public static class DataResult {

        private final SimpleStringProperty fileName;
        private final double resultValue;

        private DataResult(String fName, double rValue) {
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

}
