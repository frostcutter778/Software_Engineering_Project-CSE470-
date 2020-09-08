
package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Subject {

    private final SimpleStringProperty subjectId;
    private final SimpleStringProperty courseId;
    private final SimpleIntegerProperty section;
    private final SimpleIntegerProperty facultyId;
    private final SimpleStringProperty deptId;
    private final SimpleIntegerProperty semesterId;
    private final SimpleStringProperty roomNo;
    private final SimpleStringProperty subjectTime;

    public Subject(String subId, String courId, int sec, int faculId, String dept, int sem, String room, String subTime) {
        subjectId = new SimpleStringProperty(subId);
        courseId = new SimpleStringProperty(courId);
        section = new SimpleIntegerProperty(sec);
        facultyId = new SimpleIntegerProperty(faculId);
        deptId = new SimpleStringProperty(dept);
        semesterId = new SimpleIntegerProperty(sem);
        roomNo = new SimpleStringProperty(room);
        subjectTime = new SimpleStringProperty(subTime);
    }
    
    public String getSubjectTime() {
        return subjectTime.get();
    }

    public void setSubjectTime(String subtime) {
        subjectTime.set(subtime);
    }
    
    
    public String getRoomNo() {
        return roomNo.get();
    }

    public void setRoomNo(String room) {
        roomNo.set(room);
    }
    
    
    
    public int getSemesterId() {
        return semesterId.get();
    }

    public void setSemesterId(int sem) {
        semesterId.set(sem);
    }
    
    
    public String getDeptId() {
        return subjectId.get();
    }

    public void setDeptId(String depId) {
        deptId.set(depId);
    }
    
    
    public int getFacultyId() {
        return facultyId.get();
    }

    public void setFacultyId(int faculId) {
        facultyId.set(faculId);
    }
    
    
    public int getSection() {
        return section.get();
    }

    public void setSection(int sec) {
        section.set(sec);
    }
    
    
    public String getCourseId() {
        return courseId.get();
    }

    public void setCourseId(String courId) {
        courseId.set(courId);
    }
    
    
    
    public String getSubjectId() {
        return subjectId.get();
    }

    public void setSubjectId(String subId) {
        subjectId.set(subId);
    }
}
