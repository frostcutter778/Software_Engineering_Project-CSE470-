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
public class Student {
    private final SimpleStringProperty name;
    private final SimpleStringProperty gender;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty dname;
    
    public Student(String name, String gender, int id, String dname) {
        this.name=new SimpleStringProperty(name);
        this.gender=new SimpleStringProperty(gender);
        this.id= new SimpleIntegerProperty(id);
        this.dname=new SimpleStringProperty(dname);
    }
    public String getName()
    {
    return name.get();
    }
    public String getGender()
    {
    return gender.get();
    }
    public int getId()
    {
        return id.get();
    }
    public String getDname()
    {
    return dname.get();
    }
    //public amount
    public void setName(String a)
    {
    name.set(a);
    }
    public void setGender(String b)
    {
    gender.set(b);
    }
    public void setId(int id)
    {
    this.id.set(id);
    }
    public void setDname(String dept)
    {
    dname.set(dept);
    }
    
}
