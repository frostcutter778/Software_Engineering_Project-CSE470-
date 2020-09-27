package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adittya
 */
public class studenttest {
    
    Student st;
    @Before
    public void setUp() {
        st = new Student("Zarif","Male",16100000,"EEE");
    }
    
     @Test
    public void getNameTest() {
        assertEquals("Zarif",st.getName());
    }
    
    @Test
    public void setNameTest() {
        st.setName("farhin");
        assertEquals("farhin",st.getName());
    }
    
    @Test
    public void getGenderTest(){
        assertEquals("Male",st.getGender());
    }
    
    
    @Test
    public void setGenderTest(){
        st.setGender("Female");
        assertEquals("Female",st.getGender());
    }
    
     
    @Test
    public void getIdTest() {
        assertEquals(16100000,st.getId());
    }
    
    
    @Test
    public void setIdTest() {
        st.setId(565);
        assertEquals(565,st.getId());
    }
    
      @Test
    public void getDnameTest() {
        assertEquals("EEE",st.getDname());
    }
    
    
     @Test
    public void setDnameTest() {
        st.setDname("ESS");
        assertEquals("ESS",st.getDname());
    }
}
