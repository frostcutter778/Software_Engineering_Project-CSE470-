/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.beans.property.SimpleStringProperty;

public class Room {
    private final SimpleStringProperty roomNumber;
        private final SimpleStringProperty roomCapacity;
    
        public Room(String idx, String capactiy)
        {
           roomNumber = new SimpleStringProperty(idx);
           roomCapacity= new SimpleStringProperty(capactiy);
        }
       
        public String getRoomNumber() {
            return roomNumber.get();
        }
 
        public void setRoomNumber(String id) {
            roomNumber.set(id);
        }
 
        public String getRoomCapacity() {
            return roomCapacity.get();
        }
 
        public void setRoomCapacity(String year) {
            roomCapacity.set(year);
        }
        
}
