/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 *@author Adittya
 */
public class Instructor {
    private String id;
    private String name;
    public Instructor(String id,String name)
    {
        this.id=id;
        this.name=name;   
    }
    
    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String toString()
    {
        return name;
    }
}
