/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CreatingConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private Button insert;
    @FXML
    private Button back;
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField ans;
    @FXML
    private PasswordField pass;

    @FXML
    private ChoiceBox securedQuestion;

    Connection con = CreatingConnection.con;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        securedQuestion.getItems().add("What is your facourite color ?");
        securedQuestion.getItems().add("What is your hobby ?");
        securedQuestion.getItems().add("What is your grand-father name?");
        securedQuestion.getItems().add("What is your nick name?");

    }

    public void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            Scene nextScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nextScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updatePasswordButton(ActionEvent eveent) {

        try {
            // TODO add your handling code here: 2
            String[] values = new String[4];
            values[0] = pass.getText();
            values[01] = name.getText();
            values[02] = id.getText();
            values[03] = ans.getText();
            PreparedStatement pst = con.prepareStatement("update user set password = ? where Name=? and ID=? and ans=?");
            pst.setString(1, values[0]);
            pst.setString(2, values[1]);
            pst.setInt(3, Integer.parseInt(values[02]));
            pst.setString(4, values[3]);
            int result = pst.executeUpdate();
            pst.close();
            if (result==0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Updating password failed!");
                alert.setContentText("Recheck the answer and ID !");
                alert.showAndWait();
            } else {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Updating password Successfully!");
                alert.setHeaderText(null);
                alert.setContentText("Good day mate sir, Now you use your new password to log on!");

                alert.showAndWait();
                showNone();
            }

        } catch (Exception ex) {

        }
    }

    void showNone() {
        id.setText("");
        name.setText("");
        pass.setText("");
        ans.setText("");
        securedQuestion.setValue(null);
    }

}
