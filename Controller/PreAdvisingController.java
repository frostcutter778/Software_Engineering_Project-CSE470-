/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Classfx;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PreAdvisingController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private TextField semesterNo;
    @FXML
    private TextField setMaxCredit;
    @FXML
    private TextField setMinCredit;

    @FXML
    private Button setSemester;
    @FXML
    private Button setMaxCreditButton;
    @FXML
    private Button setMinCreditButton;
    @FXML
    private Button preAdvisingOn;
    @FXML
    private Button preAdvisingOFF;
    @FXML
    private Button back;

    
    @FXML
    private Label labelSemester;
    @FXML
    private Label labelMaxCredit;
    @FXML
    private Label labelMinCredit;
    @FXML
    private Label labelPanel;
     
    public int max;
    public int min;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        img.setImage(new Image(this.getClass().getResource("/img/preadvising.gif").toExternalForm()));
        img.setVisible(true);
        labelSemester.setText(""+Classfx.semesterId);
        labelMaxCredit.setText(""+Classfx.maxCredit);
        labelMinCredit.setText(""+Classfx.minCredit);
        max=Classfx.maxCredit;
        min=Classfx.minCredit;
        boolean prePanel=Classfx.preTime;
        if(prePanel)labelPanel.setText("ON");
        else labelPanel.setText("OFF");
    }

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
        Scene nextScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();

    }

    @FXML
    private void SetSemesterNumberButtonAction(ActionEvent event) throws IOException, SQLException {
        int semester = Integer.parseInt(semesterNo.getText());
        if (semester <= Classfx.semesterId) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Increasing Semester Failed !");
            alert.setContentText("Ooops, You sould enter next semester id, not the previous one.Let bygone be gone.");
            alert.showAndWait();
        } else {
            Classfx.setSemesterId(Integer.parseInt(semesterNo.getText()));
        }
        semesterNo.setText("");
        labelSemester.setText(""+Classfx.semesterId);
    }

    @FXML
    private void SetMaxCreditButtonAction(ActionEvent event) throws IOException, SQLException {
       max=Integer.parseInt(setMaxCredit.getText());
        System.out.println(max+" "+min);
        if(max<min)
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Can not be changed!");
                alert.setContentText("Max Credit can not be lesser than Min");
                alert.showAndWait();
        }
        else
        {
        Classfx.setMaxCredit(Integer.parseInt(setMaxCredit.getText()));
        labelMaxCredit.setText(""+Classfx.maxCredit);
        max=Classfx.maxCredit;
        }
        setMaxCredit.setText("");
        }

    @FXML
    private void SetMinCreditButtonAction(ActionEvent event) throws IOException, SQLException {
    min=Integer.parseInt(setMinCredit.getText());
        System.out.println(max+" "+min);
        if(max<min)
        {
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Can not be changed!");
                alert.setContentText("Min Credit can not be higher than Max");
                alert.showAndWait();
        }
        else
        {
        Classfx.setMinCredit(Integer.parseInt(setMinCredit.getText()));
        
        labelMinCredit.setText(""+Classfx.minCredit);
        min=Classfx.minCredit;
        }
        setMinCredit.setText("");
    }

    @FXML
    private void SetPreAdvisingOnButtonAction(ActionEvent event) throws IOException, SQLException {
        Classfx.setPanel(1);
        if(Classfx.preTime)labelPanel.setText("ON");
        else labelPanel.setText("OFF");
    }

    @FXML
    private void SetPreAdvisingOffButtonAction(ActionEvent event) throws IOException, SQLException {
        Classfx.setPanel(0);
        if(Classfx.preTime)labelPanel.setText("ON");
        else labelPanel.setText("OFF");
    }
}
