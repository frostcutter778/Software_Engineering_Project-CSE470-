/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;


public class Course {
       private String number = null;
       private String name = null;
       private int maxNumberOfStudents;
       private ArrayList <Instructor> instructors;
       
       public Course(String number,String name, ArrayList <Instructor> ins,int maxNumberOfStudents)
       {
           this.number=number;
           this.name = name;
           this.instructors=ins;
           this.maxNumberOfStudents=maxNumberOfStudents;
       }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }
       public String getNumber ()
       {
       return number;
       }
       public String getName()
       {
       return name;
       }
       public ArrayList<Instructor> getInstructors()
       {
       return instructors;
       }
       public String toString()
       {
       return name+" "+number+" "+instructors;
       }
       
}
