package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import logic.MyGraph;

public class input_screen implements Initializable{

    BorderPane mainBorderPane = new BorderPane();
    private int num_of_nodes = number_of_nodes.getNumOfNodes1();
    private static MyGraph graph_obj;

    @FXML
    private Label new_label = new Label();
    @FXML
    private ListView<String> branches_listview = new ListView<String>();
    @FXML
    private TextField from_textField = new TextField();
    @FXML
    private TextField to_textField = new TextField();
    @FXML
    private TextField gain_textField = new TextField();
    @FXML
    private Button add_button= new Button();
    @FXML
    private Button clear_button= new Button();
    @FXML
    private Button enter_button= new Button();
    private float[][] graph = new float[num_of_nodes][num_of_nodes];
    //ObservableList<String> branches_list = new ObservableList<String>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int row =0;row<num_of_nodes;row++) {
            for(int col =0 ; col<num_of_nodes;col++) {
                graph[row][col]=0;
            }
        }
        new_label.setText(String.valueOf(num_of_nodes));
        branches_listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void add_branch_key(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            ActionEvent eventnew = new ActionEvent();
            add_branch(eventnew);
        }

    }

    public void add_branch(ActionEvent event) {
        String from = from_textField.getText();
        String to =  to_textField.getText();
        String gain = gain_textField.getText();
        String regex = "([1-9]\\d*|[0]+\\d+)";
        String regex2 = "-?(\\d+|\\d+.\\d+)";
        Pattern pat = Pattern.compile(regex);
        Pattern pat2 = Pattern.compile(regex2);
        Matcher mat1 = pat.matcher(from);
        Matcher mat2 = pat.matcher(to);
        Matcher mat3 = pat2.matcher(gain);
        if(!mat1.matches() || !mat2.matches() || !mat3.matches()) {
            Alert alert = new Alert(AlertType.ERROR, "You entered a non digit value  or a zero number ,, "
                    + "please enter a numerical value representing number of nodes!", ButtonType.OK);

            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK) {
                from_textField.setText("");
                to_textField.setText("");
                gain_textField.setText("");
            }
        }else {

            int fromNode = Integer.parseInt(from);
            int toNode = Integer.parseInt(to);

            float gainValue = Float.parseFloat(gain);
            if(fromNode > num_of_nodes  || toNode > num_of_nodes) {
                Alert alert = new Alert(AlertType.ERROR, "You entered number of nodes larger than the number"
                        + "of nodes ,, please reenter the correct numbers", ButtonType.OK);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK) {
                    from_textField.setText("");
                    to_textField.setText("");
                }
            }else {
                branches_listview.getItems().add("Branch from node " + fromNode +
                        " to Node " + toNode + " with Gain = "+ gainValue);
                from_textField.setText("");
                to_textField.setText("");
                gain_textField.setText("");
            }
        }


    }

    public void Delete_selected(ActionEvent event) {
        ObservableList<Integer> selected= branches_listview.getSelectionModel().getSelectedIndices();
        for(int counter =0; counter<selected.size();counter++) {
            branches_listview.getItems().remove((int)selected.get(counter));
        }
    }
    public void finish(ActionEvent event) throws IOException {
        ObservableList<String> final_branches = branches_listview.getItems();
        String regex = "Branch from node (\\d+) to Node (\\d+) with Gain = (-?(\\d+\\.\\d+)|\\d+)";
        Pattern pat = Pattern.compile(regex);
        for(int counter =0 ; counter < final_branches.size() ; counter++) {
            Matcher mat = pat.matcher(final_branches.get(counter));
            mat.find();
            int row = Integer.parseInt(mat.group(1));
            int col = Integer.parseInt(mat.group(2));
            float gain = Float.parseFloat(mat.group(3));
            graph[row-1][col-1] = gain;
        }

        graph_obj = new MyGraph(num_of_nodes,graph);

        graph_obj.setLoops_and_Pathes();

        Parent rootFX = FXMLLoader.load(getClass().getResource("/application/results.fxml"));
        BorderPane main_borderPane = Main.getBorderPane();
        main_borderPane.setCenter(rootFX);

        The_Graph thegraph = new The_Graph();
        thegraph.createGraphNodes(num_of_nodes);
        thegraph.createGraphBranches(graph, num_of_nodes);
        thegraph.ShowGraph();
    }

    public float[][] getGraph(){
        return graph;

    }
    public static MyGraph getGraphobj() {
        return graph_obj;
    }


}
