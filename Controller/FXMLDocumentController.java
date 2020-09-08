/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Adittya
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button Login;
    @FXML
    private Button Signup;
    @FXML
    private ImageView gif;
    
    @FXML
  public void handleButton1(ActionEvent event) throws IOException
  {
       Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
       Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
      
  }
   public void handleButton2(ActionEvent event) throws IOException
  {
       Parent root = FXMLLoader.load(getClass().getResource("/View/Signup.fxml"));
       Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
      
  }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         gif.setImage(new Image(this.getClass().getResource("/img/home.gif").toExternalForm()));
         gif.setVisible(true);
    }    
    
}
