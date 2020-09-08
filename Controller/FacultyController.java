 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CreatingConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class FacultyController implements Initializable {

    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField genderText;
    @FXML
    private TextField deptText;
    
    public int id = LoginController.user_id;
    
    @FXML
    private Button newbt;
    @FXML
    private Button back;
    @FXML
    private Button updateInfo;
    
    @FXML
    private Button notifyButton;
    
    @FXML
    public void notifyButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/NotifyAll.fxml"));
        Scene nextScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }
     @FXML
    public void attendanceButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Attendance.fxml"));
        Scene nextScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }
    
    @FXML
    public void newbb(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/addmarks.fxml"));
        Scene nextScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {

            show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        Scene nextScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();

    }

    public void show() throws SQLException {
        String sql = "select user.ID, user.Name, user.gender, faculty.dname from user inner join faculty on user.ID = faculty.ID where user.id=?";
        PreparedStatement pst = CreatingConnection.con.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        String [] Values = new String [4];
        while (rs.next()) {
            
            Values[0]=(rs.getString("ID"));
            
            Values[1]=(rs.getString("Name"));
            String gender;
            if (rs.getString("gender").equals("1")) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            Values[2]=(gender);
            Values[3]=rs.getString("dname");
        }
        idText.setText(Values[0]);
            nameText.setText(Values[1]);
          
            genderText.setText(Values[2]);
            deptText.setText(Values[3]);
    }
    @FXML
    public void updateButton(ActionEvent event)
     {
      try{
        String a=nameText.getText();
        String b=genderText.getText();
        System.out.println(a);
        System.out.println(b);
        String UpdateQuery="update user set name= ?,gender=? where id="+id;
                 PreparedStatement   ps=CreatingConnection.con.prepareStatement(UpdateQuery);
                    ps.setString(1, a);
                    String qwe =genderText.getText();
                    int ew;
                    if(qwe.equalsIgnoreCase("female"))ew=2;
                    else ew=1;
                    ps.setInt(2, ew);
                    ps.executeUpdate();
                    ps.close();
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Information Dialog");
     alert.setHeaderText(null);
     alert.setContentText("Information Successfully Updated!");
     Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
     stage.getIcons().add(new Image("img/mechaaboo.png")); 
     alert.showAndWait();
        }
        catch(Exception ex)
        {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Information Dialog");
     alert.setHeaderText(null);
     alert.setContentText("Something Went wrong Information Was Not Updated!");
     Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
     stage.getIcons().add(new Image("img/mechaaboo.png")); 
     alert.showAndWait();
        }
     }
}
