package a307a.program.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Resultlist {
    private ObservableList<DataResult> data = FXCollections.observableArrayList();
    private TableView table = new TableView();
    private TableColumn fileName1 = new TableColumn("Files");
    private TableColumn result = new TableColumn("Result");


    public Resultlist() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(150);

        fileName1.setCellValueFactory(
                new PropertyValueFactory<DataResult, String>("fileName"));
        result.setCellValueFactory(
                new PropertyValueFactory<DataResult, String>("resultValue"));

        table.getColumns().addAll(fileName1, result);
    }

    public TableView getTable(){
        return table;
    }

    public void addTableEntry(String compInformation, double result){
        data.add(new DataResult(compInformation, result));
        table.setItems(data);
    }
}
