/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.LoginController;
import Controller.Classfx;
import Model.CreatingConnection;
import Model.ResultSubject;
import Model.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class addmarksController implements Initializable {

    @FXML
    private TextField nameText;
    @FXML
    private TextField genderText;
    @FXML
    private TextField idText;
    @FXML
    private TextField deptText;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> studentId;
    @FXML
    private TextField insertMarks;
    @FXML
    private TableView<ResultSubject> subjectTable;
    @FXML
    private TableColumn<ResultSubject, String> subjectId;
    @FXML
    private TableColumn<ResultSubject, String> courseId;
    private String resultSubjectId;
    private String resultCourseId;        
            
     private final ObservableList<ResultSubject> subjectData = FXCollections.observableArrayList();
    private final ObservableList<Student> studentData = FXCollections.observableArrayList();
    @FXML
    private Button marksUpdateButton;
    @FXML
    private Button insertMarksButton;
    @FXML
    private Button back;
    @FXML
      public void backbutton(ActionEvent event) throws IOException
  {
       Parent root = FXMLLoader.load(getClass().getResource("/View/Faculty.fxml"));
       Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
      
  }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         SubjectTableShow();
    }   
    private void SubjectTableShow() {

        try {
            subjectData.clear();
            subjectTable.refresh();
            SubjectStartup();
            subjectId.setCellValueFactory(new PropertyValueFactory<ResultSubject, String>("subjectId"));
            courseId.setCellValueFactory(new PropertyValueFactory<ResultSubject, String>("courseId"));
            subjectTable.setItems(subjectData);
            subjectTable.setRowFactory(tv -> {
                TableRow<ResultSubject> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        ResultSubject rowData = row.getItem();
                        resultSubjectId= ""+rowData.getSubjectId();
                        resultCourseId=""+rowData.getCourseId();
                        StudentTableShow();
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void SubjectStartup() {
        try {
            Statement st = CreatingConnection.con.createStatement();
            ResultSet rs = st.executeQuery("select subjectId,courseId from subject where semesterId='"+Classfx.semesterId+"' and instructorId="+LoginController.user_id);
            String[] Values = new String[2];
            
            while (rs.next()) {
                Values[1 - 1] = rs.getString("subjectId");
                Values[2 - 1] = rs.getString("courseId");
                System.out.println(Values[0]+"  "+Values[1]);
                subjectData.add(new ResultSubject((Values[0]), (Values[01])));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    private void StudentTableShow() {

        try {
            studentData.clear();
            studentTable.refresh();
            StudentStartup();
            studentId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
         studentTable.setItems(studentData);
         studentTable.setRowFactory(tv -> {
                TableRow<Student> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        Student rowData = row.getItem();
                        
                     showInfo(rowData.getId(),rowData.getName(),rowData.getGender(),rowData.getDname());
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void StudentStartup() {
        try {
  
              String sql="SELECT Distinct user.name,user.id,user.gender,Student.dname FROM user inner join  Student on user.id=student.id inner join stu_takes on user.id = stu_takes.stu_id where stu_takes.sub_id='"+resultSubjectId+"' and stu_takes.semester_id="+Classfx.semesterId;
         ResultSet rs;
        Statement st= CreatingConnection.con.createStatement();
            rs=st.executeQuery(sql);
            String stringvalue[]=new String[4];
            int intvalue;
            
                    while(rs.next())
        {
         stringvalue[0]=rs.getString("name");
         stringvalue[1]=rs.getString("id");
         stringvalue[2]=rs.getString("dname");
         if(rs.getString("gender").equalsIgnoreCase("1"))stringvalue[3]= "Male";
         else stringvalue[3]="Female";
         studentData.add(new Student((stringvalue[0]),(stringvalue[3]),Integer.parseInt(stringvalue[1]),stringvalue[2]));
            
        }
                    rs.close();
                    st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showInfo(int id, String name, String gender,String dept)
    {
    idText.setText(""+id);
    nameText.setText(name);
    genderText.setText(gender);
    deptText.setText(dept);
    }
    
    
    
    @FXML
    private void insertMarksButton(ActionEvent event) throws SQLException
    {
        try
        {PreparedStatement pre =CreatingConnection.con.prepareStatement("insert into result (std_id,f_id,c_id,gpa,semesterId) values(?,?,?,?,?)");
                    pre.setInt(1,Integer.parseInt( idText.getText()));
                    pre.setInt(2,(LoginController.user_id));
                    pre.setString(3,resultCourseId );
                    pre.setDouble(4,grade(Integer.parseInt(insertMarks.getText())));
                    pre.setInt(5, Classfx.semesterId);
                    pre.execute();
                    pre.close();
                     Alert alert = new Alert(AlertType.INFORMATION);
                     alert.setTitle("Inserted Marks Successful!");
                     alert.setHeaderText(null);
                     alert.setContentText("Good day sir, marks have been inserted ");
                     alert.showAndWait();
               
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Inserting Marks Failed !");
            alert.setContentText("Sql error , check if you have already entered the marks or not by updating the value!");
            alert.showAndWait();
        }
        showNone();
    }
    private double grade(int value)
    {
    if(value<=100&&value>=90) return 4.00;
    if(value>=85) return 3.7;
    if(value>=80) return 3.3;
    if(value>=75) return 3.0;
    if(value>=70) return 2.7;
    if(value>=65) return 2.3;
    if(value>=60) return 2.0;
    return 0.0;
    }
    @FXML
    private void updateMarksButtonAction(ActionEvent event)
    {
        try{
           
    String UpdateQuery="update result set gpa=? where std_id=? and c_id= ? and semesterId=? and f_id=?";
                 PreparedStatement   ps=CreatingConnection.con.prepareStatement(UpdateQuery);
                    ps.setDouble(1,grade(Integer.parseInt(insertMarks.getText())));
                    ps.setString(2,idText.getText());
                    ps.setString(3,resultCourseId);
                   // System.out.println("bhao");
                    ps.setInt(4, Classfx.semesterId);
                    ps.setInt(5, LoginController.user_id);
                    ps.executeUpdate();
                    //System.out.println("bhao");
                    ps.close();
                    Alert alert = new Alert(AlertType.INFORMATION);
                     alert.setTitle("Updated!");
                     alert.setHeaderText(null);
                     alert.setContentText("Good day sir, marks has been updated! ");
                     alert.showAndWait();
        }
        catch(Exception ex )
        {
            ex.printStackTrace();
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Inserting Marks Failed !");
            alert.setContentText("Sql error or something missing. check carefully ! ");
            alert.showAndWait();
        }
        showNone();
        }
    
    private void showNone()
    {
    insertMarks.setText("");
    nameText.setText("");
    idText.setText("");
    genderText.setText("");
    deptText.setText("");
    }
}
