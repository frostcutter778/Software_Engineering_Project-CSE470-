package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adittya
 */
public class table1 {
         private final SimpleStringProperty name;
        private final SimpleStringProperty email;
    private final SimpleIntegerProperty id;
       public table1(String name,String email, int id)
{
             
          this.name=new SimpleStringProperty(name);
         this.email=new SimpleStringProperty(email);
         this.id=new SimpleIntegerProperty(id);
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
    

    public String setEmail() {
        return email.get();
    }

    public void setId(int i) {
       id.set(i);
    }
        public void setName(String a) {
       name.set(a);
    }

}
