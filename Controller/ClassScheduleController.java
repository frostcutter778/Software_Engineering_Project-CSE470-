/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Course;
import Model.CreatingConnection;
import Model.Meeting;
import Controller.Room;
import Model.Semester;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class ClassScheduleController implements Initializable {

    Connection con = CreatingConnection.con;
    public static int classScheduleSemester;
    @FXML
    private Button back;
    @FXML
    private Button refresh;
    @FXML
    private Button addSemester;
    @FXML
    private Button UpdateSemester;
    @FXML
    private Button ShowResultsSemester;
    @FXML
    private Button addRoom;
    @FXML
    private Button deleteRoom;
    @FXML
    private Button addMeeting;
    @FXML
    private Button deleteMeeting;
    @FXML
    private Button addCourse;
    @FXML
    private Button deleteCourse;
    @FXML
    private Button newGenerate;
    @FXML
    private Button oldGenerate;

    @FXML
    private TextField textSemesterId;
    @FXML
    private TextField textSemesterYear;
    @FXML
    private TextField textSemesterSession;
    @FXML
    private TextField textRoomNumber;
    @FXML
    private TextField textRoomCapacity;
    @FXML
    private TextField textMeetingId;
    @FXML
    private TextField textMeetingTime;
    @FXML
    private TextField textCourseId;
    @FXML
    private TextField textCourseNumber;
    @FXML
    private TextField textCourseFacultyId;
    @FXML
    private TextField textCourseMaxNumberOfStudents;

    @FXML
    private TableColumn<Semester, String> semesterId;
    @FXML
    private TableColumn<Semester, String> semesterYear;
    @FXML
    private TableColumn<Semester, String> semesterSession;

    @FXML
    private TableColumn<Room, String> roomNumber;
    @FXML
    private TableColumn<Room, String> roomCapacity;

    @FXML
    private TableColumn<Meeting, String> meetingId;
    @FXML
    private TableColumn<Meeting, String> meetingTime;

    @FXML
    private TableColumn<Course, String> courseId;
    @FXML
    private TableColumn<Course, String> courseNumber;
    @FXML
    private TableColumn<Course, Integer> courseFacultyId;
    @FXML
    private TableColumn<Course, Integer> courseMaxNumberOfStudents;

    @FXML
    private TableView semesterTable;
    @FXML
    private TableView roomTable;
    @FXML
    private TableView meetingTable;
    @FXML
    private TableView courseTable;
    @FXML
    private ImageView img;
    
    private final ObservableList<Semester> semesterData = FXCollections.observableArrayList();
    private final ObservableList<Room> roomData = FXCollections.observableArrayList();
    private final ObservableList<Meeting> meetingData = FXCollections.observableArrayList();
    private final ObservableList<Course> courseData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         img.setImage(new Image(this.getClass().getResource("/img/classchedule.gif").toExternalForm()));
         img.setVisible(false);
        SemesterTableShow();
    }

    @FXML
    private void BackButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
        Scene nextScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }

    @FXML
    private void AddSemesterButtonAction(ActionEvent event) throws IOException, SQLException {
        if (checkInputsOfSemester()) {
            PreparedStatement pst = con.prepareStatement("insert into semester (year,session,id) values(?,?,?)");
            pst.setString(1, (textSemesterYear.getText()));
            pst.setString(2, textSemesterSession.getText());
            pst.setInt(3, Integer.parseInt(textSemesterId.getText()));
            int result = pst.executeUpdate();
            if (result == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Adding Semester failed!");
                alert.setContentText("Recheck If you have written same semester id again!");
                alert.showAndWait();
                RefreshButtonAction();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Added Semester Successfully!");
                alert.setHeaderText(null);
                alert.setContentText("Good day sir, New Semester Table row created !");
                alert.showAndWait();
                RefreshButtonAction();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adding Semester failed!");
            alert.setContentText("Recheck If you have left something unanswered!");
            alert.showAndWait();
            RefreshButtonAction();
        }
        SemesterTableShow();
    }

    @FXML
    private void UpdateSemesterButtonAction(ActionEvent event) throws IOException, SQLException {
        if (checkInputsOfSemester()) {
            PreparedStatement pst = con.prepareStatement("update semester set year=? ,session = ? where semester.id=?");
            System.out.println(textSemesterYear.getText() + " " + textSemesterSession.getText() + " " + textSemesterId.getText());
            pst.setString(1, textSemesterYear.getText());
            pst.setString(2, textSemesterSession.getText());
            pst.setInt(3, Integer.parseInt(textSemesterId.getText()));
            int result = pst.executeUpdate();
            if (result == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Updating Semester failed!");
                alert.setContentText("Recheck If you have written same semester id again!");
                alert.showAndWait();
                RefreshButtonAction();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Updated Semester Successfully!");
                alert.setHeaderText(null);
                alert.setContentText("Good day sir, New Semester Table row created !");
                alert.showAndWait();
                RefreshButtonAction();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Updating Semester failed!");
            alert.setContentText("Recheck If you have left something unanswered!");
            alert.showAndWait();
            RefreshButtonAction();
        }
        SemesterTableShow();
    }

    @FXML
    private void ShowResultsSemesterButtonAction(ActionEvent event) throws IOException, SQLException {
        String id = textSemesterId.getText();
        if (id.equals("")) {

        } else {
            classScheduleSemester= Integer.parseInt(textSemesterId.getText());
            RoomTableShow();
            MeetingTableShow();
            CourseTableShow();
        }
    }

    private void RefreshButtonAction() {
        textSemesterId.setText("");
        textSemesterYear.setText("");
        textSemesterSession.setText("");
    }

    @FXML
    private void RefreshButtonAction(ActionEvent event) throws IOException {
        textSemesterId.setText("");
        textSemesterYear.setText("");
        textSemesterSession.setText("");
    }

    private boolean checkInputsOfSemester() {
        String id = textSemesterId.getText();
        String year = textSemesterYear.getText();
        String session = textSemesterSession.getText();
        if (id.equals("") || year.equals("") || session.equals("")) {
            return false;
        }
        return true;
    }

    private void SemesterTableShow() {

        try {
            semesterData.clear();
            semesterTable.refresh();
            SemesterStartup();
            semesterId.setCellValueFactory(new PropertyValueFactory<Semester, String>("id"));
            semesterYear.setCellValueFactory(new PropertyValueFactory<Semester, String>("year"));
            semesterSession.setCellValueFactory(new PropertyValueFactory<Semester, String>("session"));
            semesterTable.setItems(semesterData);
            semesterTable.setRowFactory(tv -> {
                TableRow<Semester> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        Semester rowData = row.getItem();
                        textSemesterId.setText("" + rowData.getId());
                        textSemesterYear.setText("" + rowData.getYear());
                        textSemesterSession.setText("" + rowData.getSession());

                    }
                });
                return row;
            });
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void SemesterStartup() {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from semester");
            String[] Values = new String[3];
            while (rs.next()) {
                Values[1 - 1] = rs.getString("id");
                Values[2 - 1] = rs.getString("year");
                Values[3 - 1] = (rs.getString("session"));
                semesterData.add(new Semester((Values[0]), (Values[01]), (Values[02])));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void RoomTableShow() {

        try {
            roomData.clear();
            roomTable.refresh();
            RoomStartup();
            roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
            roomCapacity.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
            roomTable.setItems(roomData);
            roomTable.setRowFactory(tv -> {
                TableRow<Room> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        Room rowData = row.getItem();
                        textRoomNumber.setText("" + rowData.getRoomNumber());
                        textRoomCapacity.setText("" + rowData.getRoomCapacity());
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
            String id = textSemesterId.getText();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select numb,seatingCapacity from room where semester_id=" + id);
            String[] Values = new String[2];
            while (rs.next()) {
                Values[1 - 1] = rs.getString("numb");
                Values[2 - 1] = rs.getString("seatingCapacity");

                roomData.add(new Room((Values[0]), (Values[01])));
            }
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddRoomButtonAction() throws SQLException {
        System.out.println(RoomInputsCheck());
        if (RoomInputsCheck()) {
            String id = textSemesterId.getText();
            if (!id.equals("")) {
                PreparedStatement pre = con.prepareStatement("insert into room (numb,seatingCapacity,semester_id) values(?,?,?)");
                pre.setString(1, textRoomNumber.getText());
                pre.setInt(2, Integer.parseInt(textRoomCapacity.getText()));
                pre.setInt(3, Integer.parseInt(textSemesterId.getText()));
                int result = pre.executeUpdate();
                if (result == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Adding room failed!");
                    alert.setContentText("Recheck If you have written same room id again!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Room Added Successfully!");
                    alert.setHeaderText(null);
                    alert.setContentText("Good day sir, New room table row created !");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Updating Semester failed!");
                alert.setContentText("Recheck If you have left semester id box left unanswered!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adding Room failed!");
            alert.setContentText("Recheck If you have left something unanswered!");
            alert.showAndWait();
        }
        RoomTableShow();
        cleanRoomInfos();
    }

    @FXML
    private void DeleteRoomButtonAction() {
        try {
            if (RoomInputsCheck()) {
                String id = textRoomNumber.getText();
                if (!id.equals("")) {
                    PreparedStatement pre = con.prepareStatement("delete from  room where numb=?");
                    pre.setString(1, id);
                    int result = pre.executeUpdate();
                    if (result == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("Deleting room failed!");
                        alert.setContentText("Recheck If the infos again!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Room Deleted Successfully!");
                        alert.setHeaderText(null);
                        alert.setContentText("Good day sir, New room table row deleted !");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Deleting Rooms info failed!");
                    alert.setContentText("Recheck If you have left room number box left unanswered!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Deleting Room failed!");
                alert.setContentText("Recheck If you have left something unanswered!");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            System.out.println(ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adding Room failed!");
            alert.setContentText("This room has been used for child constraints ! Remove those first to update this.");
            alert.showAndWait();
        }
        RoomTableShow();
        cleanRoomInfos();
    }

    private void cleanRoomInfos() {
        textRoomNumber.setText("");
        textRoomCapacity.setText("");
    }

    private boolean RoomInputsCheck() {
        String roomNumber = textRoomNumber.getText();
        String roomCapacity = textRoomCapacity.getText();
        if (roomNumber.equals("") || roomCapacity.equals("")) {
            return false;
        }
        return true;
    }

    private void MeetingTableShow() {

        try {
            meetingData.clear();
            meetingTable.refresh();
            MeetingStartup();
            meetingId.setCellValueFactory(new PropertyValueFactory<>("meetingId"));
            meetingTime.setCellValueFactory(new PropertyValueFactory<>("meetingTime"));
            meetingTable.setItems(meetingData);
            meetingTable.setRowFactory(tv -> {
                TableRow<Meeting> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        Meeting rowData = row.getItem();
                        textMeetingId.setText("" + rowData.getMeetingId());
                        textMeetingTime.setText("" + rowData.getMeetingTime());
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void MeetingStartup() {
        try {
            String id = textSemesterId.getText();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select id,time from meetingtime where semester_id=" + id);
            String[] Values = new String[2];
            while (rs.next()) {
                Values[1 - 1] = rs.getString("id");
                Values[2 - 1] = rs.getString("time");

                meetingData.add(new Meeting((Values[0]), (Values[01])));
            }
            st.close();
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddMeetingButtonAction() throws SQLException {
        System.out.println(MeetingInputsCheck());
        if (MeetingInputsCheck()) {
            String id = textSemesterId.getText();
            if (!id.equals("")) {
                PreparedStatement pre = con.prepareStatement("insert into meetingtime (id,time,semester_id) values(?,?,?)");
                pre.setString(1, textMeetingId.getText());
                pre.setString(2, (textMeetingTime.getText()));
                pre.setInt(3, Integer.parseInt(textSemesterId.getText()));
                int result = pre.executeUpdate();
                if (result == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Adding meetingTime failed!");
                    alert.setContentText("Recheck If you have written same meeting time id again!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Meeting time  Added Successfully!");
                    alert.setHeaderText(null);
                    alert.setContentText("Good day sir, New meeting table row created !");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("adding meeting time failed!");
                alert.setContentText("Recheck If you have left semester id box left unanswered!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adding semester failed!");
            alert.setContentText("Recheck If you have left something unanswered!");
            alert.showAndWait();
        }
        MeetingTableShow();
        cleanMeetingInfos();
    }

    @FXML
    private void DeleteMeetingButtonAction() {
        try {
            if (MeetingInputsCheck()) {
                String id = textMeetingId.getText();
                if (!id.equals("")) {
                    PreparedStatement pre = con.prepareStatement("delete from  meetingtime where id=?");
                    pre.setString(1, id);
                    int result = pre.executeUpdate();
                    if (result == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("Deleting meeting failed!");
                        alert.setContentText("Recheck the infos again!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Meetingtime Deleted Successfully!");
                        alert.setHeaderText(null);
                        alert.setContentText("Good day sir, New meetingtime table row deleted !");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Deleting Meeting info failed!");
                    alert.setContentText("Recheck If you have left meeitng id box left unanswered!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Deleting Meeting failed!");
                alert.setContentText("Recheck If you have left something unanswered!");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            System.out.println(ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adding Meeting failed!");
            alert.setContentText("This meeting time has been used for child constraints ! Remove those first to update this.");
            alert.showAndWait();
        }
        RoomTableShow();
        cleanRoomInfos();
    }

    private void cleanMeetingInfos() {
        textMeetingId.setText("");
        textMeetingTime.setText("");
    }

    private boolean MeetingInputsCheck() {
        String id = textMeetingId.getText();
        String time = textMeetingTime.getText();
        if (id.equals("") || time.equals("")) {
            return false;
        }
        return true;
    }

    private void CourseTableShow() {

        try {
            courseData.clear();
            courseTable.refresh();
            CourseStartup();
            courseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
            courseNumber.setCellValueFactory(new PropertyValueFactory<>("courseNumber"));
            courseFacultyId.setCellValueFactory(new PropertyValueFactory<>("courseFacultyId"));
            courseMaxNumberOfStudents.setCellValueFactory(new PropertyValueFactory<>("courseMaxNumberOfStudents"));
            courseTable.setItems(courseData);
            courseTable.setRowFactory(tv -> {
                TableRow<Course> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                        Course rowData = row.getItem();
                        textCourseId.setText("" + rowData.getCourseId());
                        textCourseNumber.setText("" + rowData.getCourseNumber());
                        textCourseFacultyId.setText("" + rowData.getCourseFacultyId());
                        textCourseMaxNumberOfStudents.setText("" + rowData.getCourseMaxNumberOfStudents());
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            System.out.println("kchur");
            ex.printStackTrace();
        }
    }

    private void CourseStartup() {
        try {
            String id = textSemesterId.getText();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select course_id,number,maxNumberOfStudents,instructorId from course where semester_id=" + textSemesterId.getText());

            while (rs.next()) {
                String[] Values = new String[4];
                Values[0] = rs.getString("course_id");
                Values[1] = (rs.getString("number"));
                Values[2] = rs.getString("instructorId");
                Values[3] = (rs.getString("maxNumberOfStudents"));
                courseData.add(new Course((Values[0]), (Values[01]), Integer.parseInt(Values[2]), Integer.parseInt(Values[3])));
            }
            st.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("kchur 2 ");
            e.printStackTrace();
        }
    }

    @FXML
    private void AddCourseButtonAction() throws SQLException {
        System.out.println(CourseInputsCheck());
        if (CourseInputsCheck()) {
            String id = textSemesterId.getText();
            if (!id.equals("")) {
                PreparedStatement pre = con.prepareStatement("insert into course (course_id,number,maxNumberOfStudents,instructorId,semester_id,dname,credit,course_details) values(?,?,?,?,?,?,?,?)");

                pre.setString(1, textCourseId.getText());
                pre.setString(2, (textCourseNumber.getText()));
                pre.setInt(3, Integer.parseInt(textCourseMaxNumberOfStudents.getText()));
                pre.setInt(4, Integer.parseInt(textCourseFacultyId.getText()));
                pre.setString(5, (textSemesterId.getText()));
                TextInputDialog dialog = new TextInputDialog("department");
                dialog.setTitle("Greetings!");
                dialog.setHeaderText(null);
                dialog.setContentText("This course Department:");
                Optional<String> result = dialog.showAndWait();
                String roomNumber = "";
                if (result.isPresent()) {
                    roomNumber = result.get();
                }
                pre.setString(6, roomNumber);
                dialog = new TextInputDialog("credit");
                dialog.setTitle("Course Credit!");
                dialog.setHeaderText(null);
                dialog.setContentText("Please this course Credit No:");
                result = dialog.showAndWait();
                roomNumber = "";
                if (result.isPresent()) {
                    roomNumber = result.get();
                }
                pre.setDouble(7, Double.parseDouble(roomNumber));

                dialog = new TextInputDialog("course details");
                dialog.setTitle("Course Details!");
                dialog.setHeaderText(null);
                dialog.setContentText(" course details:");
                result = dialog.showAndWait();
                roomNumber = "";
                if (result.isPresent()) {
                    roomNumber = result.get();
                }
                pre.setString(8, (roomNumber));
                int results = pre.executeUpdate();
                if (results == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Adding course failed!");
                    alert.setContentText("Recheck If you have written same course id again!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Course  Added Successfully!");
                    alert.setHeaderText(null);
                    alert.setContentText("Good day sir, New course added in the row !");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("adding course failed!");
                alert.setContentText("Recheck If you have left semester id box left unanswered!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Course semester failed!");
            alert.setContentText("Recheck If you have left something unanswered!");
            alert.showAndWait();
        }
        CourseTableShow();
        cleanCourseInfos();
    }

    @FXML
    private void DeleteCourseButtonAction() {
        try {
            if (CourseInputsCheck()) {
                String id = textCourseNumber.getText();
                if (!id.equals("")) {
                    PreparedStatement pre = con.prepareStatement("delete from  course where number=?");
                    pre.setString(1, id);
                    int result = pre.executeUpdate();
                    if (result == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("Deleting course failed!");
                        alert.setContentText("Recheck the infos again!");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Course Deleted Successfully!");
                        alert.setHeaderText(null);
                        alert.setContentText("Good day sir, New course row deleted !");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Deleting Course info failed!");
                    alert.setContentText("Recheck If you have left couse number box left unanswered!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Deleting Course failed!");
                alert.setContentText("Recheck If you have left something unanswered!");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            System.out.println(ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Adding course failed!");
            alert.setContentText("This course has been used for child constraints ! Remove those first to update this.");
            alert.showAndWait();
        }
        CourseTableShow();
        cleanCourseInfos();
    }

    private void cleanCourseInfos() {
        textCourseId.setText("");
        textCourseNumber.setText("");
        textCourseFacultyId.setText("");
        textCourseMaxNumberOfStudents.setText("");

    }

    private boolean CourseInputsCheck() {
        String id = textCourseId.getText();
        String numb = textCourseNumber.getText();
        String facultyId = textCourseFacultyId.getText();
        String amount = textCourseMaxNumberOfStudents.getText();

        if (id.equals("") || numb.equals("") || facultyId.equals("") || amount.equals("")) {
            return false;
        }
        return true;
    }

    @FXML
    private void newGenerateButtonAction(ActionEvent event) throws Exception {
        String id = textSemesterId.getText();
        if (!id.equals("")) {
            Driver driver = new Driver();
            driver.geneticAlgoExecute(Integer.parseInt(id));
            Parent root = FXMLLoader.load(getClass().getResource("/View/generatedClassSchedule.fxml"));
            Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Generating failed!");
            alert.setContentText("You left semester id section unanswered ! write the semester id and try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void oldGenerateButtonAction(ActionEvent event) throws IOException {
        String id = textSemesterId.getText();
        if (!id.equals("")) {
            Parent root = FXMLLoader.load(getClass().getResource("/View/generatedClassSchedule.fxml"));
       Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Generating failed!");
            alert.setContentText("You left semester id section unanswered ! write the semester id and try again.");
            alert.showAndWait();
        }
    }
}
