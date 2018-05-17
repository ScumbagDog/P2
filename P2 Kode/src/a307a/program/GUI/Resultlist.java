package a307a.program.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.stream.Collectors;

public class Resultlist {
    private ObservableList<DataResult> data = FXCollections.observableArrayList();
    private TableView table = new TableView();
    private TableColumn fileName1 = new TableColumn("Files");
    private TableColumn result = new TableColumn("Result");
    private Button compareMelodies = new Button();
    private Button saveResults = new Button();
    private HBox buttons = new HBox(compareMelodies, saveResults);
    private StackPane resultStack1 = new StackPane(this.getTable());
    private StackPane resultStack2 = new StackPane(buttons);
    private Comparison comparison = new Comparison();
    private GraphicsManager graphicsManager;


    public Resultlist(GraphicsManager graphicsManager) {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(150);

        fileName1.setCellValueFactory(new PropertyValueFactory<DataResult, String>
                ("fileName"));
        result.setCellValueFactory(new PropertyValueFactory<DataResult, String>
                ("resultValue"));

        table.getColumns()
                .addAll(fileName1, result);

        this.graphicsManager = graphicsManager;
        initiateResultList();
        setCompareButtonFunctionality();
        saveResultsToFile();
    }

    public TableView getTable() {
        return table;
    }

    public void addTableEntry(String compInformation, double result) {
        data.add(new DataResult(compInformation, result));
        table.setItems(data);
    }

    private void initiateResultList() {
        graphicsManager.getResultSplit()
                .setOrientation(Orientation.VERTICAL);
        graphicsManager.getResultSplit()
                .setDividerPositions(0.9);
        //resultStack2.setMaxSize(100, 100);
        graphicsManager.getResultSplit()
                .getItems()
                .addAll(resultStack1, resultStack2);
    }

    public void saveResultsToFile(){
        saveResults.setText("Save Results");
        saveResults.setOnAction(event -> {
            try{
                PrintWriter writer = new PrintWriter(getDate() + ".txt", "UTF-8");
                data.stream().forEach(dataResult -> writer.println(dataResult.getEntry()));
                writer.close();
            }
            catch(FileNotFoundException e){
                System.out.println("The file you made does not exist");
            }
            catch(UnsupportedEncodingException e){
                System.out.println("Invalid encoding, fix it.");
            } });
    }

    private String getDate(){
        ZonedDateTime date = ZonedDateTime.now();
        String dash = "-";
        return Integer.toString(date.getHour())
                + Integer.toString(date.getMinute())
                + Integer.toString(date.getDayOfMonth())
                + dash + Integer.toString(date.getMonthValue())
                + dash + Integer.toString(date.getYear());
    }

    public void setCompareButtonFunctionality() {
        compareMelodies.setStyle("-fx-font-size: 10pt;");
        compareMelodies.setText("Compare");
        compareMelodies.setOnAction(event -> {
            openConfirmationWindow();
        });
    }

    private void openConfirmationWindow(){
        Stage confirmAction = new Stage();
        Text confirmationText = new Text(setConfirmationText());
        Button startComparison = new Button("Start");
        startComparison.setOnAction(event2 -> {
            comparison.useUkonnen(this,
                    graphicsManager.getSelectedFiles()
                            .getSrcMidiFiles(),
                    graphicsManager.getSelectedFiles()
                            .getCompMidiFiles()
            );
            confirmAction.close();
        });
        Button cancelComparison = new Button("Cancel");
        cancelComparison.setOnAction(event3 -> {
            confirmAction.close();
        });
        BorderPane content = new BorderPane();
        content.setTop(confirmationText);
        content.setLeft(startComparison);
        content.setRight(cancelComparison);
        confirmAction.setScene(new Scene(content));
        confirmAction.show();
    }

    private String setConfirmationText(){
        return "A total of "
                + graphicsManager.getSelectedFiles()
                .getSrcMidiFiles()
                .size()
                + " source files and "
                + graphicsManager.getSelectedFiles()
                .getCompMidiFiles()
                .size()
                + " comparison files have been selected.\n"
                + "Do you want to begin the comparison sequence?";
    }
}
