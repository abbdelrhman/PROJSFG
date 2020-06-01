package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import logic.MyGraph;

public class results implements Initializable {

    @FXML
    private ListView<String> results_listView;

    @FXML
    private Label result_label;

    private MyGraph graph_obj = input_screen.getGraphobj();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String result = "";
        results_listView.getItems().add("Forward paths");

        ArrayList<ArrayList<Integer>> list = this.graph_obj.getPath().getPaths();
        for (int i = 0; i < list.size(); i++) {
            result += "M";
            result += String.valueOf(i + 1);
            result += " => ";
            for (int j = 0; j < list.get(i).size(); j++) {
                result += "X";
                result += String.valueOf((list.get(i).get(j) + 1));
                if (j + 1 < list.get(i).size()) {
                    result += " . ";
                }
            }
            result += " = ";
            result += this.graph_obj.getPath().get_paths_gain().get(i).toString();
            this.results_listView.getItems().add(result);
            result = "";
        }


        this.results_listView.getItems().add("individual loops");
        list = this.graph_obj.getLoop().getLoops();
        for (int i = 0; i < list.size(); i++) {
            result += "L";
            result += String.valueOf(i + 1);
            result += " => ";
            for (int j = 0; j < list.get(i).size(); j++) {
                result += "X";
                result += String.valueOf((list.get(i).get(j) + 1));
                if (j + 1 < list.get(i).size()) {
                    result += " . ";
                }
            }
            result += " = ";
            result += this.graph_obj.getLoop().get_loops_gain().get(i).toString();
            this.results_listView.getItems().add(result);
            result = "";
        }


        ArrayList<ArrayList<ArrayList<Integer>>> nt = this.graph_obj.getNon_Touched_loops().getNonTouched();
        ArrayList<ArrayList<ArrayList<Integer>>> ntIndexes = this.graph_obj.getNon_Touched_loops().getNonTouchedLoopsName();
        for (int i = 1; i < nt.size(); i++) {
            String s = String.valueOf(i + 1);
            s += " non touched loops";
            this.results_listView.getItems().add(s);
            for (int j = 0; j < nt.get(i).size(); j++) {

                for (int k = 0; k < ntIndexes.get(i).get(j).size(); k++) {
                    result += "L";
                    result += String.valueOf(ntIndexes.get(i).get(j).get(k) + 1);
                    if (k + 1 < ntIndexes.get(i).get(j).size()) {
                        result += " . ";
                    }
                }
                result += " => ";
                this.results_listView.getItems().add(result);
                result = "";

                for (int k = 0; k < nt.get(i).get(j).size(); k++) {
                    result += "X";
                    result += String.valueOf(nt.get(i).get(j).get(k) + 1);
                    if (k + 1 < nt.get(i).get(j).size()) {
                        result += " . ";
                    }
                }
                result += " = ";
                result += this.graph_obj.getNon_Touched_loops().getLoopsGain().get(i).get(j).toString();
                this.results_listView.getItems().add(result);
                result = "";
            }
        }

        this.results_listView.getItems().add("delta");
        float denominator = this.graph_obj.getDelta().getDelta();
        this.results_listView.getItems().add("Delta = " + String.valueOf(denominator));

        this.results_listView.getItems().add("deltas");
        ArrayList<Float> dList = this.graph_obj.getDeltas().getDeltas();
        for (int i = 0; i < dList.size(); i++) {
            result = "Delta ";
            result += String.valueOf(i + 1) + " = ";
            result += String.valueOf(dList.get(i));
            this.results_listView.getItems().add(result);
            result = "";
        }


        this.results_listView.getItems().add("result");
        float fResult = 0;
        float Numerator = 0;
        ArrayList<Float> gains = this.graph_obj.getPath().get_paths_gain();
        for (int i = 0; i < gains.size(); i++) {
            Numerator += gains.get(i) * dList.get(i);
        }
        fResult = Numerator / denominator;
        this.results_listView.getItems().add(" = " + String.valueOf(fResult));
    }

}
