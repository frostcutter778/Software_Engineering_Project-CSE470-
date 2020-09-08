/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Adittya
 */
public class Meeting {
    private final SimpleStringProperty meetingId;
        private final SimpleStringProperty meetingTime;
    
        public Meeting(String idx, String time)
        {
           meetingId= new SimpleStringProperty(idx);
           meetingTime= new SimpleStringProperty(time);
        }
       
        public String getMeetingId() {
            return meetingId.get();
        }
 
        public void setMeetingId(String id) {
            meetingId.set(id);
        }
 
        public String getMeetingTime() {
            return meetingTime.get();
        }
 
        public void setMeetingTime(String time) {
            meetingTime.set(time);
        }
}
