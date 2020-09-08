    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package Model;

import javafx.beans.property.SimpleDoubleProperty;
    import javafx.beans.property.SimpleIntegerProperty;
    import javafx.beans.property.SimpleStringProperty;

    /**
     *
     * @author Adittya
     */
    public class resulttable {
         private final SimpleStringProperty course;
         private final SimpleDoubleProperty gpa;
         private final SimpleIntegerProperty credit;
         private final SimpleIntegerProperty semester;

         public resulttable(String course,double gpa,int credit,int semester)
         {
         this.course= new SimpleStringProperty(course);
         this.gpa= new SimpleDoubleProperty(gpa);
         this.credit=new SimpleIntegerProperty(credit);
         this.semester=new SimpleIntegerProperty(semester);
         }
        public String getCourse()
        {
        return course.get();
        }
        public double getGpa()
        {
        return gpa.get();
        }
        public int getCredit()
        {
        return credit.get();
        }
        public int getSemester()
        {
        return semester.get();
        }



        public void setCourse(String course)
        {
         this.course.set(course);
        }
        public void setGpa(double gpa)
        {
        this.gpa.set(gpa);
        }
        public void setCredit(int credit)
        {
        this.credit.set(credit);
        }
        public void setSemester(int semester)
        {
        this.semester.set(semester);
        }
    }
