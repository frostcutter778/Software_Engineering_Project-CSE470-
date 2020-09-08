/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Classfx;
import Model.CreatingConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class AccountentController implements Initializable {
   Connection con=CreatingConnection.con;
   int semesterid=Classfx.semesterId;
   Statement st;
    ResultSet rs;
   public int id=LoginController.user_id;
   
    @FXML
    private TextField nametext;
    @FXML
    private TextField gendertext;
    @FXML
    private TextField idtext;
    @FXML
    private TextField studentidtext;
    @FXML
    private TextField recipttext;
    @FXML
    private TextField totalamounttext;
    @FXML
    private Button paidbutton;
    @FXML
    private TextField banktext;
    @FXML
    private TextField datetext;
    @FXML
    private Button backbutton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           // TODO
           show();
       } catch (SQLException ex) {
           Logger.getLogger(AccountentController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    

    
    
    
    public void show() throws SQLException
    {
        String sql="Select name,id,gender from user where id="+id;
        
       st = (Statement) con.createStatement();
       
       rs = st.executeQuery(sql);
       while(rs.next())
       {
        nametext.setText(rs.getString("name"));
        String gend=rs.getString("gender");
        if(gend.equals("1"))
        {
        gendertext.setText("Male");
        }
        else if(gend.equals("2")) {gendertext.setText("Female");}
        idtext.setText(rs.getString("id"));
        id=Integer.parseInt(rs.getString("id"));
       }
    }
   @FXML
    public void updateAction(ActionEvent event) 
    {
        try{
        String a=nametext.getText();
        String b=gendertext.getText();
        String UpdateQuery="update user set name= ?,gender=? where id="+id;
                 PreparedStatement   ps=con.prepareStatement(UpdateQuery);
                    ps.setString(1, a);
                    String qwe =gendertext.getText();
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
   @FXML
    public void paidButton()
    {
     
        
            try {
                PreparedStatement pre =con.prepareStatement("insert into fee (std_id,acc_id,Recipt_no,Bank_name,Date,amount,semester_id) values(?,?,?,?,?,?,? )");
              
                pre.setInt(1,Integer.parseInt( studentidtext.getText()));
              
                pre.setInt(2,id);
               
                pre.setInt(3,Integer.parseInt( recipttext.getText()));
                pre.setString(4,banktext.getText());
               
                pre.setString(5,datetext.getText());
               
                pre.setInt(6,Integer.parseInt( totalamounttext.getText()));
               
                 pre.setInt(7,semesterid);
                 
                pre.execute();
                //JOptionPane.showMessageDialog(null, "Paid");
                pre.close();
                //showNone();
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Information Dialog");
     alert.setHeaderText(null);
     alert.setContentText("Payment Successful!");
     Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
     stage.getIcons().add(new Image("img/mechaaboo.png")); 
     alert.showAndWait();
            } catch (Exception ex) {
                System.out.println(ex.fillInStackTrace());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Information Dialog");
     alert.setHeaderText(null);
     alert.setContentText("Something Went Wrong Payment not Successfull!");
     Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
     stage.getIcons().add(new Image("img/mechaaboo.png")); 
     alert.showAndWait();
            } 
            studentidtext.setText(null);
            recipttext.setText(null);
            datetext.setText(null);
            banktext.setText(null);
            totalamounttext.setText(null);
            
        
    
    }
     public void backbutton(ActionEvent event) throws IOException
  {
       Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
       Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
      
  }
    
    
    
}
