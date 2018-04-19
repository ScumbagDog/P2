package GUI;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Resultlist extends Application {

    public TableView table = new TableView();


    public final ObservableList<DataResult> data = FXCollections.observableArrayList(
            new DataResult("Midifile1", 0.5),
            new DataResult("Midifile2", 0.7)
    );


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());


        TableColumn fileName1 = new TableColumn("Files");
        fileName1.setCellValueFactory(
                new PropertyValueFactory<DataResult, String>("fileName"));

        TableColumn result = new TableColumn("Result");
        result.setCellValueFactory(
                new PropertyValueFactory<DataResult, String>("resultValue"));


        table.setItems(data);
        table.getColumns().addAll(fileName1, result);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
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