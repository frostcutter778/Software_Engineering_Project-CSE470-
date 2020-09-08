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
public class preadvisingtable {
    private final SimpleStringProperty courseid;
  //  private final SimpleStringProperty courseNumber;

    public preadvisingtable(String courseid) {
        this.courseid = new SimpleStringProperty (courseid);
        
    }
    public String getCourseid()
    {
    return courseid.get();
    }
    public void setCourseid(String a)
    {
    courseid.set(a);
    }
    
}
