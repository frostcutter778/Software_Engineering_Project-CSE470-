/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Adittya
 */
public class table3 {
    private final SimpleStringProperty name;
        private final SimpleStringProperty email;
    private final SimpleStringProperty usertype;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty status;
       public table3(String name,String email,String ut, int id,String status)
{
             
          this.name=new SimpleStringProperty(name);
         this.email=new SimpleStringProperty(email);
         this.id=new SimpleIntegerProperty(id);
         usertype=new SimpleStringProperty(ut);
         this.status=new SimpleStringProperty(status);
}
           public String getName() {
        return name.get();
    }

    public String getEmail() {
        return email.get();
    }

    public int getId() {
        return id.get();
    }
     public String getUsertype() {
        return usertype.get();
    }

     public String getStatus() {
        return status.get(); 
    }
     
     
    public String setEmail() {
        return email.get();
    }

    public void setId(int i) {
       id.set(i);
    }
    public void setName(String a) {
       name.set(a);
    }
          public void setUsertype(String c) {
       usertype.set(c);
    }
     public void setStatus(String s) {
       status.set(s);
            }
          
}
