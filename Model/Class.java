/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Adittya
 */
public class Class {
 private int id;
 private Department dept;
 private Course course;
 private Instructor instructor;
 private MeetingTime meetingTime;
 private Room room;
 public Class(int id,Department dept,Course course)
 {
     this.id=id;
     this.course=course;
     this.dept=dept;
 }

    public int getId() {
        return id;
    }

    public Department getDept() {
        return dept;
    }

    public Course getCourse() {
        return course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    public String toString()
    {
        return "["+dept.getName()+","+course.getNumber()+","+instructor.getId()+","+meetingTime.getId()+"]";
    }
}
