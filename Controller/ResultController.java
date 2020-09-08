/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CreatingConnection;
import Model.resulttable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class ResultController implements Initializable {
    Connection con=CreatingConnection.con;
    public int id=StudentController.id;
    
    ObservableList<resulttable> data=FXCollections.observableArrayList();
     String stringvalue;
        
        double doublevalue;
        double totalcredit=0.0;
        private int counter;
        int integervalue[]=new int[2];
        double cgpa;
    
    @FXML
    private TableColumn<resulttable, String> col_course;
    @FXML
    private TableColumn<resulttable, Integer> col_credit;
    @FXML
    private TableColumn<resulttable, Integer> col_gpa;
    @FXML
    private TableColumn<resulttable, Integer> col_seme;
    @FXML
    private TableView table;
    @FXML
    private Label cgpatext;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void backButton(ActionEvent event) throws IOException
    {
    Parent root = FXMLLoader.load(getClass().getResource("/View/Student.fxml"));
       Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showintable();
    }    
    public void start()
    {
        
     String sql="SELECT distinct result.c_id,result.gpa,result.semesterId,course.credit FROM result INNER JOIN course ON result.c_id=course.course_id WHERE std_id="+id;
     //System.out.println(sql);
        try {
            Statement st= (Statement)con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
            stringvalue=rs.getString("c_id");
            doublevalue=rs.getDouble("gpa");
            integervalue[0]=rs.getInt("credit");
            integervalue[1]=rs.getInt("semesterId");
             totalcredit+=integervalue[0]*doublevalue;
             counter+=integervalue[0];
             System.out.println( totalcredit+" <----> "+counter); 
            data.add(new resulttable(stringvalue,doublevalue,(integervalue[0]),(integervalue[1])));
            }
             rs.close();
             st.close();
            } 
        
        catch (SQLException ex) {
            Logger.getLogger(ResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cgpaCount();
     
    }
    public void showintable()
    {
        data.clear();
        start();
        col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        col_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        col_gpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));
        col_seme.setCellValueFactory(new PropertyValueFactory<>("semester"));
        table.setItems(data);
    }
   
    public void cgpaCount()
    {
        System.out.println( totalcredit+" <--!!!!--> "+counter); 
    cgpa= totalcredit/counter;
    String cg=cgpa+"";
    cgpatext.setText(cg);
    }
    
    

  
    
}
