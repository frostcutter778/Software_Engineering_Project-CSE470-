/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CreatingConnection;
import Model.ValidateEmailAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class SignupController implements Initializable {

    @FXML
    private ChoiceBox gender;
    @FXML
    private ChoiceBox position;
    @FXML
    private ChoiceBox securedQuestion;
    @FXML
    private TextField name;
    @FXML
    private TextField id;
    @FXML
    private TextField email;
    @FXML
    private TextField ans;
    @FXML
    private TextField dept;
    @FXML
    private PasswordField pass;
    @FXML
    private ImageView waitingGif;
    Connection con;
    @FXML
    private Button signup;
    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con=CreatingConnection.con;
        waitingGif.setImage(new Image(this.getClass().getResource("/img/waiting.gif").toExternalForm()));
        waitingGif.setVisible(false);
        gender.getItems().add("Male");
        gender.getItems().add("Female");
        securedQuestion.getItems().add("What is your facourite color ?");
        securedQuestion.getItems().add("What is your hobby ?");
        securedQuestion.getItems().add("What is your grand-father name?");
        securedQuestion.getItems().add("What is your nick name?");
        position.getItems().add("Student");
        position.getItems().add("Faculty");
        position.getItems().add("Accountant");
    }

    @FXML
    public void backButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/FXMLDocument.fxml"));
            Scene nextScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nextScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void SignUpButton(ActionEvent event) {
        try {
            waitingGif.setImage(new Image(this.getClass().getResource("/img/waiting.gif").toExternalForm()));
            waitingGif.setVisible(true);
            if(checkInputs())
            {
                 try {
                String values[] = new String[7];
                values[0] = ((id.getText()));
                values[01] = (name.getText());
                values[02] = ((pass.getText()));
                if (gender.getValue().equals("Male")) {
                    values[03] = ("" +1);
                }
                else
                {
                values[03] = ("" +2);
                }
                
                if (position.getValue().equals("Student")) {
                   values[4]=(""+1);
                }
                else if (position.getValue().equals("Faculty")) {
                   values[4]=(""+2);
                }
                else
                {
                values[4]=(""+3);
                }
                values[05] = (ans.getText());
                values[06] = (email.getText());
                PreparedStatement pre =con.prepareStatement("insert into user (ID,Name,Password,Gender,UserType,Ans,email) values(?,?,?,?,?,?,? )");
                    pre.setInt(1,Integer.parseInt( values[0]));
                    pre.setString(2, values[01]);
                    pre.setString(3,(values[02]));
                    pre.setInt(4, Integer.parseInt(values[03]));
                    int b;
                    pre.setInt(5,b=Integer.parseInt(values[04]));
                    pre.setString(6,values[5]);
                    pre.setString(7, values[6]);
              //      System.out.println("ji");
                    pre.execute();
                    pre.close();
                if (values[4].equals("1")) {
                    pre =con.prepareStatement("insert into student (ID,Dname) values(?,?)");
                    pre.setInt(1,Integer.parseInt(values[0]));
                    pre.setString(2,dept.getText());
                    pre.execute();
                    pre.close();
                }
                if (values[4].equals("2")) {
                    pre =con.prepareStatement("insert into faculty (ID,name,Dname,rm) values(?,?,?,?)");
                    pre.setInt(1,Integer.parseInt(((id.getText()))));
                    pre.setString(2,name.getText());
                    pre.setString(3,dept.getText());
                    TextInputDialog dialog = new TextInputDialog("UB00000");
                    dialog.setTitle("Greetings!");
                    dialog.setHeaderText(name.getText()+" , Nice to meeet you");
                    dialog.setContentText("Please enter your consultation room number:");
                    Optional<String> result = dialog.showAndWait();
                    String roomNumber="";
                    if (result.isPresent()) {
                        roomNumber = result.get();
                    }
                    pre.setString(4,roomNumber);
                    pre.execute();
                    pre.close();
                }
                if (values[4].equals("3")) {
                   pre =con.prepareStatement("insert into accountent (ID) values(?)");
                    pre.setInt(1,Integer.parseInt((id.getText())));
                    pre.execute();
                    pre.close();
                }
                     Alert alert = new Alert(AlertType.INFORMATION);
                     alert.setTitle("Sign Up Successful!");
                     alert.setHeaderText(null);
                     alert.setContentText("Good day mate sir, Now wait for confirmation from admin");
                     alert.showAndWait();
                    showNone();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
               showNone();
            }
           
            waitingGif.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public boolean checkInputs() {
        if (name.getText() == null || id.getText() == null || email.getText() == null || securedQuestion.getValue() == null || position.getValue() == null || gender.getValue() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Sign Up Failed !");
            alert.setContentText("Ooops, You left something unanswered!");
            alert.showAndWait();
            return false;
        } else {
            try {
                Integer.parseInt(id.getText());
                ValidateEmailAddress emailEnteredByUserWillBeVerified = new ValidateEmailAddress();
                String[] emailAddress = {this.email.getText()};
                boolean verification = emailEnteredByUserWillBeVerified.EmailAddress(emailAddress);
                if(!verification)
                {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Sign Up Failed !");
                alert.setContentText("Ooops, email seems worng!");
                alert.showAndWait();   
                }
                return verification;
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Sign Up Failed !");
                alert.setContentText("Ooops, Your ID has to contain only numbers.");
                alert.showAndWait();
            }
        }
        return false;
    }
    
    void showNone() {
        id.setText("");
        name.setText("");
        pass.setText("");
        ans.setText("");
        gender.setValue(null);
        position.setValue(null);
        securedQuestion.setValue(null);
        dept.setText("");
        email.setText("");
    }
}
