package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Semester;
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
public class SemesterTest {
    
    public Semester s;
    @Before
    public void setUp() {
        s = new Semester ("15","2020","Spring");
    }
    
    @Test
    public void getIdTest() {
        assertEquals("15",s.getId());
    }
    
    @Test
    public void setIdTest() {
        s.setId("1");
        assertEquals("1",s.getId());
    }
    
    @Test
    public void getYearTest(){
        assertEquals("2020",s.getYear());
    }
    
    @Test
    public void setYearTest(){
        s.setYear("2021");
        assertEquals("2021",s.getYear());
    }
    
    @Test
    public void getSessionTest(){
        assertEquals("Spring",s.getSession());
    }
    
    @Test
    public void setSessionTest(){
        s.setSession("Fall");
        assertEquals("Fall",s.getSession());
    }
}
