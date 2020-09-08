/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.AccInfoController;
import Model.CreatingConnection;
import Model.table4;
import static Controller.AccInfoController.iduser;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class FacultyInfoController implements Initializable {

    Connection con=CreatingConnection.con;
    ObservableList<table4> data=FXCollections.observableArrayList();
    
    @FXML
    private Button bk;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<table4, String> col_name;
    @FXML
    private TableColumn<table4, Integer> col_id;
    @FXML
    private TableColumn<table4, String> col_email;
    @FXML
    private TableColumn<table4, String> col_dept;
    @FXML
    private TableColumn<table4, String> col_rm;

    /**
     * Initializes the controller class.
     */
    @FXML
        public void backbutton(ActionEvent event) throws IOException
  {
       Parent root = FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
       Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
      
  }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showintable();
        } catch (SQLException ex) {
            Logger.getLogger(FacultyInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void start() throws SQLException
    {
            String sql="SELECT user.name,user.id,user.email,faculty.dname,faculty.rm From user INNER JOIN faculty on faculty.Id=user.ID WHERE user.UserType=2 and user.app=1;";
         ResultSet rs;
        Statement st= (Statement) con.createStatement();
        try {
            rs=st.executeQuery(sql);
            String stringvalue[]=new String[4];
            int intvalue;
            
                    while(rs.next())
        {
         stringvalue[0]=rs.getString("name");
         
         System.out.println(rs.getString("name"));
         
         
         
         stringvalue[1]=rs.getString("email");
         
         System.out.println(rs.getString("email"));
         
         stringvalue[2]=rs.getString("dname");
         stringvalue[3]=rs.getString("rm");
         
         intvalue=rs.getInt("id");
         
         System.out.println(rs.getInt("id"));
         
         data.add(new table4((stringvalue[0]),(stringvalue[1]),(stringvalue[2]),(intvalue),stringvalue[3]));
            
        }
                    rs.close();
                    st.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccInfoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hoye nai");
        }
    }
    public void showintable() throws SQLException
    {
        data.clear();
        table.refresh();
        start();
         col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
         col_dept.setCellValueFactory(new PropertyValueFactory<>("dname"));
         col_rm.setCellValueFactory(new PropertyValueFactory<>("rm"));
         table.setItems(data);
         table.setRowFactory(tv -> {
                TableRow<table4> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        table4 rowData = row.getItem();
                        
                      //  table1.setText(""+rowData.getAmount());
                       iduser=rowData.getId();
                        
                    }
                });
                return row;
            });
    }
    public void blockbutton(ActionEvent e) throws SQLException
    {
        String sql="UPDATE user set app=0 WHERE ID="+iduser;
        PreparedStatement st= con.prepareStatement(sql);
        st.execute();
         showintable();
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Information Dialog");
     alert.setHeaderText(null);
     alert.setContentText("User Successfully Blocked!");
     Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
     stage.getIcons().add(new Image("img/mechaaboo.png")); 
     alert.showAndWait();
    }
    
    
}
