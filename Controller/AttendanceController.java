/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Classfx;
import Model.CreatingConnection;
import Model.ResultSubject;
import Model.attend;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class AttendanceController implements Initializable {
    @FXML
    private TextField subjectIdText;
    @FXML
    private Button back;
    
    @FXML
    private TableView<ResultSubject> subjectTable;
    @FXML
    private TableColumn<ResultSubject, String> subjectId;
    @FXML
    private TableColumn<ResultSubject, String> courseId;
    private String resultSubjectId;
    private String resultCourseId;
    
    private final ObservableList<ResultSubject> subjectData = FXCollections.observableArrayList();

    
    @FXML
    private TableColumn<attend, String> name;
    @FXML
    private TableColumn<attend, Integer> id;
    @FXML
    private TableColumn<attend, String> present;
    @FXML
    private TableColumn<attend, String> absent;
    @FXML
    private TableView<attend> attendTable;
    private final ObservableList<attend> attendData = FXCollections.observableArrayList();

    @FXML
    private DatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        StringConverter <LocalDate> converter= new StringConverter<LocalDate>()
                {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public String toString(LocalDate object) {
            if(object!=null)
                {
                    return dateFormatter.format(object);
                }
            else return "";
            }

            @Override
            public LocalDate fromString(String string) {
            if(string!=null&&!string.isEmpty())
                {
                return LocalDate.parse(string, dateFormatter);
                }
            else return null;
            }
                
                };
        datePicker.setConverter(converter);
        datePicker.setPromptText("yyyy-MM-dd");
        SubjectTableShow();
    }    

    @FXML
    private void backbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Faculty.fxml"));
        Scene nextScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
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
                        resultSubjectId = "" + rowData.getSubjectId();
                        resultCourseId = "" + rowData.getCourseId();
                        subjectIdText.setText(resultSubjectId);
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
            ResultSet rs = st.executeQuery("select subjectId,courseId from subject where semesterId='" + Classfx.semesterId + "' and instructorId=" + LoginController.user_id);
            String[] Values = new String[2];

            while (rs.next()) {
                Values[1 - 1] = rs.getString("subjectId");
                Values[2 - 1] = rs.getString("courseId");
                System.out.println(Values[0] + "  " + Values[1]);
                subjectData.add(new ResultSubject((Values[0]), (Values[01])));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void generatingButtonAction(ActionEvent event)
    {
       AttendTableShow();
    }
    
    private void AttendTableShow() {

        try {
            attendData.clear();
            attendTable.refresh();
            RoomStartup();
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            present.setCellValueFactory(new PropertyValueFactory<>("present"));
            absent.setCellValueFactory(new PropertyValueFactory<>("absent"));
            attendTable.setItems(attendData);
            attendTable.setRowFactory(tv -> {
                TableRow<attend> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        attend rowData = row.getItem();
                        //textRoomNumber.setText("" + rowData.getRoomNumber());
                       // textRoomCapacity.setText("" + rowData.getRoomCapacity());
                        rowData.getPresent().setOnAction(new EventHandler <ActionEvent>(){
                            boolean adi=false;
                            public void handle(ActionEvent event){
                            try{
                        PreparedStatement pre =CreatingConnection.con.prepareStatement("INSERT INTO stu_takes (stu_id, sub_id, semester_id, Date, Present, Absent)  values(?,?,?,?,?,? )");
                        System.out.println(rowData.getId()+" "+resultSubjectId+" "+Classfx.semesterId+" ");
                        pre.setInt(1,rowData.getId());
                    pre.setString(2,resultSubjectId);
                        pre.setInt(3, Classfx.semesterId);
                         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedValue = (datePicker.getValue()).format(formatter);
                        pre.setString(4,formattedValue);
                        System.out.println(formattedValue);
                        pre.setInt(5,1);
                        pre.setInt(6,0);
                        pre.execute();
                        adi=true;
                        pre.close();
                            }
                            catch(Exception ex)
                            {
                            ex.printStackTrace();
                            }
                            rowData.getPresent().setDisable(adi);
                            rowData.getAbsent().setDisable(adi);
                        }
                        });
                        
                        rowData.getAbsent().setOnAction(new EventHandler <ActionEvent>(){
                            boolean adi=false;
                            public void handle(ActionEvent event){
                            try{
                                
                        PreparedStatement pre =CreatingConnection.con.prepareStatement("INSERT INTO stu_takes (stu_id, sub_id, semester_id, Date, Present, Absent) values(?,?,?,?,?,? )");
                    pre.setInt(1,rowData.getId());
                    pre.setString(2,resultSubjectId);
                        pre.setInt(3, Classfx.semesterId);
                         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedValue = (datePicker.getValue()).format(formatter);
                        pre.setString(4,formattedValue);
                        pre.setInt(5,0);
                        pre.setInt(6,1);
                        pre.execute();
                        adi=true;
                        pre.close();
                            }
                            catch(Exception ex)
                            {
                            ex.printStackTrace();
                            }
                             rowData.getPresent().setDisable(adi);
                            rowData.getAbsent().setDisable(adi);
                        }
                        });
                        
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void RoomStartup() {
        try {
         Statement st = CreatingConnection.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT user.name,user.id from user inner join student on student.id=user.id inner join stu_takes on student.id=stu_takes.stu_id where sub_id='" + resultSubjectId + "' and semester_id=" + Classfx.semesterId);
            String[] Values = new String[2];
            while (rs.next()) {
                Values[1 - 1] = rs.getString("name");
                Values[2 - 1] = rs.getString("id");

                attendData.add(new attend((Values[0]), Integer.parseInt(Values[01])));
            }
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
