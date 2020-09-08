/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Classfx;
import Model.CreatingConnection;
import Model.ResultSubject;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class NotifyAllController implements Initializable {

    @FXML
    private TextField subjectIdText;
    @FXML
    private TextField notifySubjectText;
    @FXML
    private TextArea notifyAreaText;
    @FXML
    private Button send;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        notifyAreaText.setWrapText(true);
        SubjectTableShow();
    }

    public void backbutton(ActionEvent event) throws IOException {
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
    private void sendButtonAction(ActionEvent e) {
        try {
            Statement st = CreatingConnection.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT distinct user.email from user inner join student on student.id=user.id inner join stu_takes on student.id=stu_takes.stu_id where sub_id='" + resultSubjectId + "' and semester_id=" + Classfx.semesterId);
            Loop1:
            while (rs.next()) {
                sendEmail(rs.getString("email"));
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation Successful!");
            alert.setHeaderText(null);
            alert.setContentText("Successfully notified all sudents of " + resultCourseId);
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Could not notify students, check net Connections and other stuffs as well.");
            alert.showAndWait();
        }
        finally
                {
                    subjectIdText.setText("");
                    notifySubjectText.setText("");
                    notifyAreaText.setText("");
                }
    }

    private void sendEmail(String toEmail) {
        final String username = "rasred75@gmail.com";
        final String password = "01716404544";
        String fromEmail = username;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(notifySubjectText.getText());
            msg.setText(notifyAreaText.getText());

            Transport.send(msg);
            System.out.println("Sent message");
        } catch (MessagingException exx) {
            exx.printStackTrace();
        }
    }
}
