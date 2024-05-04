import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable {
    public int noOfPro;

    @FXML
    private Button noOfProBtn;

    @FXML
    private TextField noOfProField;
    @FXML
    private Label noOfProVal;

    @FXML
    private Button submitTableData;
    @FXML
    private Label submitTableDataValidation;

    @FXML
    private Button addOneInputRow;
    @FXML
    private Button delOneInputRow;

    @FXML
    private Button genGanttChartBtn;

    @FXML
    private Button clearOutputsBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private TableView<Process> proDataInputTable;
    @FXML
    private TableView<ProcessCalc> outputTable;
    @FXML
    private TableView<AvgCalc> outputAvgTable;

    @FXML
    public void noOfProBtnOnAction(ActionEvent e) {
        submitTableDataValidation.setText("");

        if (!noOfProField.getText().matches("^(?!0)\\d+$")) {
            this.labelTimeout(noOfProVal, 3, "Invalid Input of processe number!", Color.RED);
            return;
        }

        int noOfPro = Integer.parseInt(noOfProField.getText());
        this.noOfPro = noOfPro;

        noOfProField.clear();

        this.addInputTableFields(noOfPro);
    }

    public void addInputTableFields(int noOfPro) {

        final ObservableList<Process> data = FXCollections.observableArrayList();

        for (int i = 0; i < noOfPro; i++)
            data.add(new Process("", "", "", i + 1));

        proDataInputTable.setItems(data);
    }

    public void addClaculationTableFields(int noOfPro) {

        final ObservableList<ProcessCalc> data = FXCollections.observableArrayList();

        for (int i = 0; i < noOfPro; i++)
            data.add(new ProcessCalc(i + 1));

        outputTable.setItems(data);
    }

    public void addAvgClaculationTableFields() {

        final ObservableList<AvgCalc> data = FXCollections.observableArrayList();

        data.add(new AvgCalc());

        outputAvgTable.setItems(data);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void initialize(URL arg0, ResourceBundle arg1) {
        TableColumn proColumn = new TableColumn("Process No.");
        TableColumn priColumn = new TableColumn("priority");
        TableColumn atColumn = new TableColumn("AT");
        TableColumn btColumn = new TableColumn("BT");

        proDataInputTable.getColumns().addAll(proColumn, priColumn, atColumn, btColumn);

        proColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("proName"));
        priColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("priority"));
        atColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("arrivalTime"));
        btColumn.setCellValueFactory(new PropertyValueFactory<Process, Integer>("burstTime"));

        proColumn.setPrefWidth(79);
        priColumn.setPrefWidth(79);
        atColumn.setPrefWidth(79);
        btColumn.setPrefWidth(79);

        /**********************************/

        TableColumn proColumnOut = new TableColumn("Process No.");
        TableColumn ctColumn = new TableColumn("Completion Time");
        TableColumn tatColumn = new TableColumn("Turnaround Time");
        TableColumn wtColumn = new TableColumn("Waiting Time");
        TableColumn rtColumn = new TableColumn("Response Time");

        outputTable.getColumns().addAll(proColumnOut, ctColumn, tatColumn, wtColumn, rtColumn);

        proColumnOut.setCellValueFactory(new PropertyValueFactory<ProcessCalc, Integer>("proNo"));
        ctColumn.setCellValueFactory(new PropertyValueFactory<ProcessCalc, Integer>("ct"));
        tatColumn.setCellValueFactory(new PropertyValueFactory<ProcessCalc, Integer>("tat"));
        wtColumn.setCellValueFactory(new PropertyValueFactory<ProcessCalc, Integer>("wt"));
        rtColumn.setCellValueFactory(new PropertyValueFactory<ProcessCalc, Integer>("rt"));

        proColumnOut.setPrefWidth(160);
        ctColumn.setPrefWidth(160);
        tatColumn.setPrefWidth(160);
        wtColumn.setPrefWidth(160);
        rtColumn.setPrefWidth(160);

        /**********************************/

        TableColumn ctColumnAvg = new TableColumn("Avg Completion Time");
        TableColumn tatColumnAvg = new TableColumn("Avg Turnaround Time");
        TableColumn wtColumnAvg = new TableColumn("Avg Waiting Time");
        TableColumn rtColumnAvg = new TableColumn("Avg Response Time");

        outputAvgTable.getColumns().addAll(ctColumnAvg, tatColumnAvg, wtColumnAvg, rtColumnAvg);

        ctColumnAvg.setCellValueFactory(new PropertyValueFactory<AvgCalc, Integer>("ct"));
        tatColumnAvg.setCellValueFactory(new PropertyValueFactory<AvgCalc, Integer>("tat"));
        wtColumnAvg.setCellValueFactory(new PropertyValueFactory<AvgCalc, Integer>("wt"));
        rtColumnAvg.setCellValueFactory(new PropertyValueFactory<AvgCalc, Integer>("rt"));

        ctColumnAvg.setPrefWidth(199);
        tatColumnAvg.setPrefWidth(199);
        wtColumnAvg.setPrefWidth(199);
        rtColumnAvg.setPrefWidth(199);

        TableColumn[] cols = {
                proColumn, priColumn, atColumn, btColumn,
                proColumnOut, ctColumn, tatColumn, wtColumn, rtColumn,
                ctColumnAvg, tatColumnAvg, wtColumnAvg, rtColumnAvg
        };

        for (TableColumn col : cols) {
            col.setStyle("-fx-alignment: CENTER;");
        }
    }

    @FXML
    public void submitProcessData(ActionEvent e) {
        boolean valid = true;

        for (Process p : proDataInputTable.getItems()) {
            if (!p.validate()) {
                valid = false;
                break;
            }
        }

        if (!valid) {
            this.labelTimeout(submitTableDataValidation, 3, "Invalid Input of process data!", Color.RED);
            return;
        }

        this.labelTimeout(submitTableDataValidation, 3, "Process data submitted successfully!", Color.GREEN);

        this.addClaculationTableFields(noOfPro);
        this.addAvgClaculationTableFields();
    }

    @FXML
    public void addOneInputRowAction(ActionEvent e) {
        proDataInputTable.getItems().add(new Process("", "", "", this.noOfPro + 1));
        this.noOfPro += 1;
    }

    @FXML
    public void delOneInputRowAction(ActionEvent e) {
        if (this.noOfPro == 0)
            return;

        proDataInputTable.getItems().remove(this.noOfPro - 1);
        this.noOfPro -= 1;
    }

    @FXML
    public void genGanttChartAction(ActionEvent e) {
        String imagePath = "/home/lordy/Pictures/2024-01-02_10-54.png";

        Image image = new Image(new File(imagePath).toURI().toString());
        ImageView imageView = new ImageView(image);
        Stage imageStage = new Stage();
        StackPane root = new StackPane();

        root.getChildren().add(imageView);

        Scene scene = new Scene(root, image.getWidth(), image.getHeight());
        imageStage.setScene(scene);
        imageStage.setTitle("Generated Gantt Chart");
        imageStage.show();
    }

    @FXML
    public void clearOutputsAction(ActionEvent e) {
        proDataInputTable.getItems().clear();
        outputTable.getItems().clear();
        outputAvgTable.getItems().clear();

        noOfProVal.setText("");
    }

    @FXML
    public void exitAction(ActionEvent e) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void labelTimeout(Label lbl, int sec, String text, Color color) {
        Timeline timeline = new Timeline();
        Duration delay = Duration.seconds(sec);

        lbl.setTextFill(color);
        lbl.setText(text);

        KeyFrame keyFrame = new KeyFrame(delay, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lbl.setText("");
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
}
