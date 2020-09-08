
package Controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {
    private final SimpleStringProperty courseId;
    private final SimpleStringProperty courseNumber;
    private final SimpleIntegerProperty courseFacultyId;
    private final SimpleIntegerProperty courseMaxNumberOfStudents;
        public Course(String idx, String coursenumb,int facultyId,int studentAmount)
        {
           courseId= new SimpleStringProperty(idx);
           courseNumber= new SimpleStringProperty(coursenumb);
           courseFacultyId= new SimpleIntegerProperty(facultyId);
           courseMaxNumberOfStudents= new SimpleIntegerProperty(studentAmount);
        }
       
        public String getCourseId() {
            return courseId.get();
        }
 
        public void setCourseId(String id) {
            courseId.set(id);
        }
 
        public String getCourseNumber() {
            return courseNumber.get();
        }
 
        public void setCourseNumber(String CourseNumber) {
            courseNumber.set(CourseNumber);
        }
        
        public int getCourseFacultyId() {
            return courseFacultyId.get();
        }
 
        public void setCourseFacultyId(int id) {
            courseFacultyId.set(id);
        }
        public int getCourseMaxNumberOfStudents() {
            return courseMaxNumberOfStudents.get();
        }
 
        public void setCourseMaxNumberOfStudents(int studentNumber) {
            courseMaxNumberOfStudents.set(studentNumber);
        }
}
