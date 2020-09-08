
package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import Model.*;
import Controller.*;

/**
 *
 * @author adittya
 */
public class Data {

    public static Connection con;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Instructor> instructors = new ArrayList<Instructor>();
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Department> dept = new ArrayList<Department>();
    private ArrayList<MeetingTime> meetingTime = new ArrayList<MeetingTime>();
    private int numberOfClass = 0;

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Department> getDept() {
        return dept;
    }

    public ArrayList<MeetingTime> getMeetingTime() {
        return meetingTime;
    }

    public int getNumberOfClass() {
        return numberOfClass;
    }

    public Data() throws Exception {
        connection();
        initialize();
    }

    private void connection() throws Exception {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classch?zeroDateTimeBehavior=convertToNull", "root", "");
   }

    public Data initialize() throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select numb,seatingCapacity from room where semester_id="+Driver.id);
        while (rs.next()) {
            String[] pop = new String[2];
            pop[0] = rs.getString("numb");
            pop[1] = (rs.getString("seatingCapacity"));
            Room newRooms = new Room(pop[0], Integer.parseInt(pop[1]));
            rooms.add(newRooms);
        }
       // meetingTime = new ArrayList<MeetingTime>();
        rs = st.executeQuery("select id,time from meetingtime where semester_id="+Driver.id);
        while (rs.next()) {
            String[] pop = new String[2];
            pop[1 - 1] = rs.getString("id");
            pop[2 - 1] = (rs.getString("time"));
            MeetingTime newRooms = new MeetingTime(pop[0], pop[1]);
            meetingTime.add(newRooms);
        }
        //instructors = new ArrayList<Instructor>();
        rs = st.executeQuery("select id,name from faculty");

        while (rs.next()) {
            String[] pop = new String[2];
            pop[1 - 1] = rs.getString("id");
            pop[2 - 1] = (rs.getString("name"));
            Instructor newRooms = new Instructor(pop[0], pop[1]);
            instructors.add(newRooms);
        }
        
            rs = st.executeQuery("select course_id,number,maxNumberOfStudents,instructorId from course where semester_id="+Driver.id);

            while (rs.next()) {
                String[] pop = new String[4];
                pop[0] = rs.getString("course_id");
                pop[1] = (rs.getString("number"));
                pop[2] = rs.getString("instructorId");
                pop[3] = (rs.getString("maxNumberOfStudents"));
                ArrayList<Instructor> ins = new ArrayList<Instructor>();
                instructors.forEach(x -> {
                    if (x.getId().equals(pop[2])) {
                        ins.add(x);
                    }
                });
                Course newRooms = new Course(pop[0], pop[1], ins, Integer.parseInt(pop[3]));
                courses.add(newRooms);
            }
        
            rs = st.executeQuery("select * from department");
            ArrayList<String> dep = new ArrayList<String>();
            while (rs.next()) {
                String[] pop = new String[4];
                pop[0] = rs.getString("dept");
                dep.add(pop[0]);
            }
            
            
             for (int counter = 0; counter < dep.size(); counter++) {
                    ArrayList < Course > courses2 = new ArrayList<Course>();
            ResultSet rss = st.executeQuery("select course_id,number,maxNumberOfStudents,instructorId from course inner join department on department.dept=course.dname where department.dept=" + "'" +dep.get(counter) + "' and course.semester_id="+Driver.id);
            while (rss.next()) {
                String[] popz = new String[4];
                popz[0] = rss.getString("course_id");
                popz[1] = (rss.getString("number"));
                popz[2] = rss.getString("instructorId");
                popz[3] = (rss.getString("maxNumberOfStudents"));
                ArrayList<Instructor> ins = new ArrayList<Instructor>();
                instructors.forEach(xx -> {
                    if (xx.getId().equals(popz[2])) {
                        ins.add(xx);
                    }
                });
                Course courses = new Course(popz[0], popz[1], ins, Integer.parseInt(popz[3]));
                courses2.add(courses);
            }
            Department newRooms = new Department(dep.get(counter), courses2);
            dept.add(newRooms);    
             }                    
        

        dept.forEach(x -> numberOfClass += x.getCourses().size());
        rs.close();
        st.close();
        return this;
    }
}
