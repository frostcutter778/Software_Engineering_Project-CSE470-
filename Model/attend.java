/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Adittya
 */
public class attend {
         private final SimpleStringProperty name;
    private final SimpleIntegerProperty id;
    private Button present;
    private Button absent;
       public attend(String name, int id)
{
          this.name=new SimpleStringProperty(name);
         this.id=new SimpleIntegerProperty(id);
         this.present = new Button("Present");
         this.absent = new Button ("Absent");
}
       
       public void setAbsent(Button button)
       {
       this.absent = button;
       }
       public Button getAbsent()
       {
           return absent;
       }
       public void setPresent(Button button)
       {
       this.present = button;
       }
       public Button getPresent()
       {
           return present;
       }
           public String getName() {
        return name.get();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int i) {
       id.set(i);
    }
        public void setName(String a) {
       name.set(a);
    }



}
