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
public class table2 {
         private final SimpleStringProperty name;
        private final SimpleStringProperty email;
    private final SimpleStringProperty dname;
    private final SimpleIntegerProperty id;
       public table2(String name,String email,String dept, int id)
{
             
          this.name=new SimpleStringProperty(name);
         this.email=new SimpleStringProperty(email);
         this.id=new SimpleIntegerProperty(id);
         dname=new SimpleStringProperty(dept);
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
     public String getDname() {
        return dname.get();
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
          public void setDname(String c) {
       dname.set(c);
    }
}
