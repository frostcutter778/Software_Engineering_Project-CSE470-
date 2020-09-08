/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.ClassScheduleController;
import Model.CreatingConnection;
import Model.Subject;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class GeneratedClassScheduleController implements Initializable {

    @FXML
    private TableColumn<Subject, String> subjectId;
    @FXML
    private TableColumn<Subject, String> courseId;
    @FXML
    private TableColumn<Subject, Integer> section;
    @FXML
    private TableColumn<Subject, Integer> facultyId;
    @FXML
    private TableColumn<Subject, String> deptId;
    @FXML
    private TableColumn<Subject, Integer> semesterId;
    @FXML
    private TableColumn<Subject, String> roomNo;
    @FXML
    private TableColumn<Subject, String> subjectTime;
    
    @FXML
    private ImageView img;
    @FXML
    private Button back;
    
    @FXML
    private TableView subjectTable;
    
        private final ObservableList<Subject> subjectData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        img.setImage(new Image(this.getClass().getResource("/img/genetic.gif").toExternalForm()));
        img.setVisible(true);
        SubjectTableShow();
    }    
     private void SubjectTableShow() {

        try {
            subjectData.clear();
            subjectTable.refresh();
            SubjectStartup();
            subjectId.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectId"));
            courseId.setCellValueFactory(new PropertyValueFactory<Subject, String>("courseId"));
            section.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("section"));
            facultyId.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("facultyId"));
            deptId.setCellValueFactory(new PropertyValueFactory<Subject, String>("deptId"));
            semesterId.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("semesterId"));
            roomNo.setCellValueFactory(new PropertyValueFactory<Subject, String>("roomNo"));
            subjectTime.setCellValueFactory(new PropertyValueFactory<Subject, String>("subjectTime"));
            
            
            subjectTable.setItems(subjectData);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void SubjectStartup() {
        try {
            Statement st = CreatingConnection.con.createStatement();
            System.out.println(ClassScheduleController.classScheduleSemester);
            
            ResultSet rs = st.executeQuery("select subjectId,courseId,section,instructorId,deptId,semesterId,roomNo,subjectTime from subject where semesterId="+ClassScheduleController.classScheduleSemester);
            String[] Values = new String[8];
            while (rs.next()) {
                Values[1 - 1] = rs.getString("subjectId");
                Values[2 - 1] = rs.getString("courseId");
                Values[3 - 1] = (rs.getString("section"));
                Values[4 - 1] = rs.getString("instructorId");
                Values[5 - 1] = rs.getString("deptId");
                Values[6 - 1] = (rs.getString("semesterId"));
                Values[7 - 1] = rs.getString("roomNo");
                Values[8 - 1] = rs.getString("SubjectTime");
                
                subjectData.add(new Subject((Values[0]), (Values[01]), Integer.parseInt((Values[02])),Integer.parseInt((Values[03])), (Values[04]), Integer.parseInt((Values[05])),(Values[06]), (Values[07])));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
     private void BackButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ClassSchedule.fxml"));
        Scene nextScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }
}
