/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import Model.*;
import Model.Class;
/**
 *
 * @author Adittya
 */
public class Driver {
    public static int id;
    public static final int POPULATION_SIZE = 9;
    public static final double MUTATION_RATE = 0.1 ;
    public static final double CROSSOVER_RATE =0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMB_OF_ELITE_SCHEDULES =1;
    private HashMap <String,Integer>sectionNoOfCourse =new  HashMap <String,Integer>();
    private Data data;
    private int classNumb=1;
    static int generationNumber =0;
    private int scheduleNumber=0;
    private int counter =0;
    public static void main (String[]args)throws Exception
    {
        Driver driver =new Driver();
        driver.data= new Data();
        driver.printAvailableData();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Population population = new Population (Driver.POPULATION_SIZE,driver.data).sortByFitness();
        driver.classNumb=1;
        while(population.getSchedules().get(8).getFitness()!=1.0)
        {
        generationNumber++;
        population = geneticAlgorithm.evolve(population).sortByFitness();
        driver.scheduleNumber =0;
        driver.classNumb=1;
        }
        driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
        
    }
    public void geneticAlgoExecute(int id) throws Exception
    {
        this.id=id;
        System.out.println("id  "+id);
        Driver driver =new Driver();
        driver.data= new Data();
        driver.printAvailableData();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Population population = new Population (Driver.POPULATION_SIZE,driver.data).sortByFitness();
        driver.classNumb=1;
        while(population.getSchedules().get(8).getFitness()!=1.0)
        {
        generationNumber++;
        population = geneticAlgorithm.evolve(population).sortByFitness();
        driver.scheduleNumber =0;
        driver.classNumb=1;
        }
        driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
        
    }
    
    private void printScheduleAsTable(Schedule schedule,int generation)
    {
       try
       {
       Statement st = Data.con.createStatement();
       String sql="delete from subject where semesterId="+id;
       st.executeUpdate(sql);
       st.close();
       String sql2="select count(*)as count from subject";
       PreparedStatement pst=Data.con.prepareStatement(sql2);
       ResultSet rs= pst.executeQuery();
       
                            while(rs.next())
                            {
                            counter= Integer.parseInt(rs.getString("count"));
                            }
       System.out.println(counter +"----------->");
       }
       catch(Exception e)
       {
       e.printStackTrace();
       }
        ArrayList<Class> classes = schedule.getClasses();
        classes.forEach(x->
        {
        counter++;
        int majorIndex =data.getDept().indexOf(x.getDept());
        int courseIndex= data.getCourses().indexOf(x.getCourse());
        int roomIndex = data.getRooms().indexOf(x.getRoom());
        int meetingTimeIndex=data.getMeetingTime().indexOf(x.getMeetingTime());
        int instructorIndex = data.getInstructors().indexOf(x.getInstructor());
        int sectionNumbers=0;
    try{
          Statement st = Data.con.createStatement();
    ResultSet rs = st.executeQuery("select course_details from course where number='"+x.getCourse().getName()+"'");
    String courseDetails="";
    while (rs.next()) {
            courseDetails= rs.getString("course_details");
            }
    rs.close();
    st.close();
    
    if(sectionNoOfCourse.containsKey(x.getCourse().getNumber()))
    {
       // System.out.println("hola buddy");
    sectionNumbers=sectionNoOfCourse.get(x.getCourse().getNumber())+1;
    sectionNoOfCourse.replace(x.getCourse().getNumber(), sectionNoOfCourse.get(x.getCourse().getNumber()), sectionNumbers);
    }
    else
    {
    sectionNoOfCourse.put(x.getCourse().getNumber(),1);
    sectionNumbers=1;
    }
    System.out.println(sectionNumbers+" "+x.getCourse().getNumber());
    PreparedStatement stmt = Data.con.prepareStatement("insert into subject values(?,?,?,?,?,?,?,?,?)");
    stmt.setString(1,"s0"+counter);//1 specifies the first parameter in the query  
    stmt.setString(2, x.getCourse().getNumber());
    stmt.setInt(3,sectionNumbers);
    stmt.setInt(4,Integer.parseInt(data.getInstructors().get(instructorIndex).getId()));
    stmt.setString(5, data.getDept().get(majorIndex).getName());
    stmt.setInt(6, id);
    stmt.setString(7,data.getRooms().get(roomIndex).getNumber());
    stmt.setString(8,x.getMeetingTime().getTime() );
    stmt.setString(9, courseDetails); 
    stmt.executeUpdate();
    stmt.close();
    System.out.println(counter + " records inserted");
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
        classNumb++;
        });
        System.out.println(); 
        if(schedule.getFitness()==1)System.out.println("> Solution found in "+(generation+1)+"generations");
        System.out.print("-------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------");
        
    }
    
    
    private void printAvailableData()
    {
    System.out.println("Available Departments ==>");
    data.getDept().forEach(x-> System.out.println("name: "+x.getName()+", course: "+x.getCourses()));
    System.out.println("\n Available Courses ==>");
    data.getCourses().forEach(x-> System.out.println("Course: "+x.getNumber()+", name: "+x.getName()+", max # of Students: "+x.getMaxNumberOfStudents()+", instructor: "+x.getInstructors()));
    System.out.println("\n Available Rooms ==>");
    data.getRooms().forEach(x-> System.out.println("Room: "+x.getNumber()+", max seating capacity: "+x.getSeatingCapacity() ));
    System.out.println("\n Available Intructors ==>");
    data.getInstructors().forEach(x-> System.out.println("ID: "+x.getId()+", name: "+x.getName() ));
    System.out.println("\n Available MeetingTimes ==>");
    data.getMeetingTime().forEach(x-> System.out.println("ID: "+x.getId()+", Meeting Time: "+x.getTime() ));
    System.out.println("-------------------------------------------------------------");
    System.out.println("-------------------------------------------------------------");
    
    }
}
