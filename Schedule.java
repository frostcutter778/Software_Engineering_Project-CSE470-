/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.*;
import Controller.*;
import java.util.ArrayList;

/**
 *
 * @author adittya
 */
public class Schedule {
    
    private ArrayList <Class>classes;
    private Data data;
    private int classNumb =0;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private int numberOfConflicts=0;
    
    public Data getData()
    {
    return data;
    }
    
    public Schedule(Data data)
    {
    this.data=data;
    classes =  new ArrayList <Class>(data.getNumberOfClass());
    }
    
    public Schedule initialize()
    {
        new ArrayList <Department>(data.getDept()).forEach(dept ->
        {
        dept.getCourses().forEach(course ->
        {
            Class newClass = new Class (classNumb++,dept,course);
            newClass.setMeetingTime(data.getMeetingTime().get((int)(data.getMeetingTime().size() * Math.random())));
            newClass.setRoom(data.getRooms().get((int)(data.getRooms().size() * Math.random())));
            newClass.setInstructor(course.getInstructors().get((int)(course.getInstructors().size() *Math.random())));
            classes.add(newClass);
        }
        );
        });
    return this;
    }
    public ArrayList <Class> getClasses()
    {
        isFitnessChanged = true;
        return classes;
    }
    public int getNumberOfConflicts() {
        return numberOfConflicts;
    }
    
    public double getFitness()
    {
        if(isFitnessChanged==true)
        {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }
    
    private double calculateFitness()
    {
    numberOfConflicts = 0;
    classes.forEach(x -> {
    if(x.getRoom().getSeatingCapacity()< x.getCourse().getMaxNumberOfStudents())
    {
    numberOfConflicts++;
    }
    classes.stream().filter(y -> classes.indexOf(y)>= classes.indexOf(x)).forEach(y ->{
    if(x.getMeetingTime()==  y.getMeetingTime() && x.getId()!=y.getId())
    {
    if(x.getRoom()==y.getRoom()) numberOfConflicts++;
    if(x.getInstructor() == y.getInstructor()) numberOfConflicts++;
    }
    });
    
    });
    return 1/(double)(numberOfConflicts + 1);
    }
    
    public String toString()
    {
    String returnValue = new String();
    for(int x =0 ;x< classes.size()-1;x++)
        returnValue += classes.get(x)+",";
    returnValue += classes.get(classes.size()-1); 
    return returnValue;
    
    }
}
