package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class number_of_nodes {

    private static int  num_of_nodes ;
    @FXML
    private TextField nodes_textField = new TextField();
    @FXML
    private Button nodes_button = new Button();


    BorderPane mainBorderPane = new BorderPane();

    public static int getNumOfNodes1() {
        return num_of_nodes;
    }

    public void getNumOfNodes(ActionEvent event) throws IOException {
        String value = nodes_textField.getText();
        boolean flag = false;
        String regex = "([1-9]\\d*|[0]+\\d+)";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(value);
        if(!mat.matches()) {
            Alert alert = new Alert(AlertType.ERROR, "You entered a non digit value  or a zero number ,, "
                    + "please enter a numerical value representing number of nodes!", ButtonType.OK);

            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK) {
                nodes_textField.setText("");
            }
        }else {// matches
            num_of_nodes= Integer.parseInt(value);
            if(num_of_nodes == 1 ) {
                Alert alert = new Alert(AlertType.ERROR, "number of nodes must be at lest 2 ", ButtonType.OK);
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK) {
                    nodes_textField.setText("");
                }
            }else {
                mainBorderPane = Main.getBorderPane();
                Parent newRoot = FXMLLoader.load(getClass().getResource("/application/Branches_screen.fxml"));
                mainBorderPane.setCenter(newRoot);
            }

        }
    }

    public void getNumOfNodesKey(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            ActionEvent eventnew = new ActionEvent();
            getNumOfNodes(eventnew );
        }
    }

}
