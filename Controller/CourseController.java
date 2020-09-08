/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Classfx;
import Model.CreatingConnection;
import Model.addcoursetable;
import Model.preadvisingtable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adittya
 */
public class CourseController implements Initializable
{

    /**
     * Initializes the controller class.
     * 
     */
    Connection con=CreatingConnection.con;
     ObservableList<preadvisingtable> data=FXCollections.observableArrayList();
     ObservableList<addcoursetable> data1=FXCollections.observableArrayList();
  public String subjectid;
   public String courseid;
   public String addcourseid;
   public Date date=new Date(2017-02-21);
   public int id=StudentController.id;
   public int semester=Classfx.semesterId;
    @FXML
    private TableView pretable;
    @FXML
    private TableColumn<preadvisingtable, String> col_course;
    @FXML
    private Label courseidtext;
    @FXML
    private Label coursedetailstext;
    @FXML
    private Label depttext;
    @FXML
    private TableView addedtable;
    @FXML
    private TableColumn<addcoursetable, String> col_addcourse;
    @FXML
    private Label facultytext;
    @FXML
    private Label timetext;
    @FXML
    private Label sectiontext;
    
    /* public CourseController(Socket s, ObjectInputStream dis, ObjectOutputStream dos)
    {
    socket=s;
    inputstream=dis;
    outputstream=dos;
    
    }*/
            public void backbutton(ActionEvent event) throws IOException
  {
       Parent root = FXMLLoader.load(getClass().getResource("/View/Student.fxml"));
       Scene nextScene = new Scene(root);
             Stage window= (Stage) ((Node) event.getSource()).getScene().getWindow() ;
             window.setScene(nextScene);
             window.show();
      
  }
    public void addButton(ActionEvent event)
    {
        try{
    String sql="Insert into stu_takes (stu_id,sub_id,semester_id,date)value(?,?,?,?) ";
    PreparedStatement st=con.prepareStatement(sql);
    st.setInt(1,id);
    st.setString(2,subjectid);
    st.setInt(3,semester);
    st.setDate(4,date);
    System.out.println(st);
    st.execute();
     
    /*while(rs.next())
    {
    
    }*/
   showintable2();
    
            }
        catch(SQLException ex)
            {
            System.out.println(ex.fillInStackTrace());
            }
    }
    
    public void dropButton(ActionEvent event)
    {
    try
    {
        String sql="Delete from stu_takes where sub_id=?";
        PreparedStatement st=con.prepareStatement(sql);
        System.out.println(st);
        st.setString(1,subjectid);
        st.execute();
        showintable2();
    
    }
    catch(SQLException ex)
    {
    System.out.println(ex.fillInStackTrace());
    }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
             /*   ip = InetAddress.getByName("localhost");
             socket = new Socket(ip, 5056);
             inputstream = new ObjectInputStream(socket.getInputStream());
             outputstream = new ObjectOutputStream(socket.getOutputStream());*/
                   // new Home(s, dis, dos).setVisible(true);
                   /*new CourseController(socket,inputstream,outputstream);*/
                   showintbale();
                   showintable2();
                } 
         catch (Exception ex) {
                     System.out.println(ex.fillInStackTrace());

                }
    }    
            public void starting()
            {
               
        try {
            String sql="Select courseId from subject where semesterId="+Classfx.semesterId;
            Statement st=(Statement)con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
            data.add(new preadvisingtable(rs.getString("courseId")));
            }
        } catch (SQLException ex) {
          //  Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println(ex.fillInStackTrace());
        }
            
            }
            public void showintbale()
            {
            data.clear();
            pretable.refresh();
            starting();
            col_course.setCellValueFactory(new PropertyValueFactory<>("courseid"));
            pretable.setItems(data);
            pretable.setRowFactory(tv -> {
                TableRow<preadvisingtable> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                         preadvisingtable rowData = row.getItem();
                        
                      //  table1.setText(""+rowData.getAmount());
                       //iduser=rowData.getId();
                        courseid=rowData.getCourseid();
                        courseinfo();
                    }
                });
                return row;
            });
            
            }
            public void courseinfo()
            {
            courseidtext.setText(courseid);
            try{
            String sql="Select subject.subjectId, subject.deptId,subject.subjectDetails,subject.courseId,subject.subjectTime,faculty.name,subject.section from subject INNER JOIN faculty ON subject.instructorId=faculty.id WHERE courseId=? and subject.semesterId="+Classfx.semesterId;
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,courseid);
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
                subjectid=rs.getString("subjectId");
            courseidtext.setText(rs.getString("courseId"));
            coursedetailstext.setText(rs.getString("subjectDetails"));
            depttext.setText(rs.getString("deptId"));
            facultytext.setText(rs.getString("name"));
            timetext.setText(rs.getString("subjectTime"));
            sectiontext.setText(rs.getString("section"));
            
            
            }
            }
            catch(SQLException ex)
            {
            System.out.println(ex.fillInStackTrace());
            }
            }
            public void starting2()
            {
                try{
            String sql="SELECT distinct  subject.courseId FROM subject INNER JOIN stu_takes ON subject.subjectId=stu_takes.sub_id WHERE stu_id="+id+" and subject.semesterId="+Classfx.semesterId;
               Statement st=(Statement)con.createStatement();
               ResultSet rs=st.executeQuery(sql);
               while(rs.next())
               {
               addcourseid=rs.getString("courseId");
               data1.add(new addcoursetable(addcourseid));
               }
                }
                catch(SQLException ex)
                {
                 System.out.println(ex.fillInStackTrace());
                }
            }
           public void showintable2() 
           {
           
                data1.clear();
            addedtable.refresh();
            starting2();
            col_addcourse.setCellValueFactory(new PropertyValueFactory<>("courseid"));
            addedtable.setItems(data1);
            addedtable.setRowFactory(tv -> {
                TableRow<addcoursetable> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 1 && (!row.isEmpty())) {
                         addcoursetable rowData = row.getItem();
                        
                      //  table1.setText(""+rowData.getAmount());
                       //iduser=rowData.getId();
                        courseid=rowData.getCourseid();;
                        courseinfo();
                    }
                });
                return row;
            });
               
           }
   
    
    
}
