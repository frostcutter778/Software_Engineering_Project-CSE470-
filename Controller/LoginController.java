/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CreatingConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class LoginController implements Initializable {

    public static int user_id;
    
    @FXML
    private Button login;
    @FXML
    private Button forgotPassword;
    @FXML
    private TextField id;
    @FXML
    private PasswordField pass;
    @FXML
    private ImageView loginGif;
    @FXML
    private ImageView loginGif2;
    @FXML
    private Button back;
    private static String password = "1234";
    Connection con = CreatingConnection.con;

    @FXML
    public void handle1(ActionEvent event) throws IOException {
        String a = id.getText();
        String b = pass.getText();
        if (a.equals("admin") && b.equals("admin")) {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
            Scene nextScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nextScene);
            window.show();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginGif.setImage(new Image(this.getClass().getResource("/img/login.gif").toExternalForm()));
        loginGif.setVisible(true);
        loginGif2.setImage(new Image(this.getClass().getResource("/img/login2.gif").toExternalForm()));
        loginGif2.setVisible(true);

        // TODO
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

    public void ForgotPasswordButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/ForgotPassword.fxml"));
            Scene nextScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(nextScene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginButton(ActionEvent event) {
        try {
            
            boolean loginAction = true;
            
            String password = (String) pass.getText();
            if (id.getText().equalsIgnoreCase("admin")) {
                if (password.equals(this.password)) {
                    
                    loginAction = false;
                    showNone();
                    Parent root = FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
                    Scene nextScene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(nextScene);
                    window.show();
                }
            }
            if (loginAction) {
                user_id=Integer.parseInt(id.getText());
                int ID = Integer.parseInt(id.getText());
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select password,UserType,app from user where id='" + ID + "'");
                boolean passwordChecking = true; //adi replaced with passwordChecking 
                String[] values = new String[3];
                Loop1:
                while (rs.next()) {
                    values[1 - 1] = rs.getString("password");
                    values[2 - 1] = (rs.getString("UserType"));
                    values[3 - 1] = (rs.getString("app"));
                }
                st.close();
                rs.close();
                int d = 0;
                String passwordDB = values[0]; // b replaced with passwordDB
                int userType = Integer.parseInt(values[1]); //d replaced with userTYpe
                int approval = Integer.parseInt(values[2]); // appz replaced with approval
                password = pass.getText();
                if (password.equals(passwordDB)) {
                    passwordChecking = false;
                    if (approval == 1) {
                        if (userType == 1) {
                            Parent root = FXMLLoader.load(getClass().getResource("/View/Student.fxml"));
                            Scene nextScene = new Scene(root);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(nextScene);
                            window.show();
                            
                        }
                        if (userType == 2) {
                            Parent root = FXMLLoader.load(getClass().getResource("/View/Faculty.fxml"));
                            Scene nextScene = new Scene(root);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(nextScene);
                            window.show();
                            
                        }
                        if (userType == 3) {
                            Parent root = FXMLLoader.load(getClass().getResource("/View/Accountent.fxml"));
                            Scene nextScene = new Scene(root);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(nextScene);
                            window.show();
                            
                            
                        }
                    }
                    if (approval == 2) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error!");
                        alert.setHeaderText(null);
                        alert.setContentText("Your account has been rejected by Admin.");
                        showNone();
                        alert.showAndWait();
                    }
                    if (approval == 3) {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Ooops, your account has been blocked by admin!\n Contact to admin asap.");
                        showNone();
                        alert.showAndWait();
                    }
                    if (approval == 0) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Your account has not been reviewed by Admin yet!");
                        alert.showAndWait();
                        showNone();
                    }
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Something went wrong with your ID or Password!");
                    alert.showAndWait();
                    showNone();
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
       }
    }
    
    public void showNone()
    {
    id.setText("");
    pass.setText("");
    }
}
