package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.ResultSubject;
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
public class ResultSubjectTest {
    
    ResultSubject rs;
    @Before
    public void setUp() {
       rs = new ResultSubject("CSE110","C01");
    }
    
    @Test
    public void getSubjectIdTest() {
        assertEquals("CSE110",rs.getSubjectId());
        
    }
    
    @Test
    public void setSubjectIdTest() {
        rs.setSubjectId("CSE422");
        assertEquals("CSE422",rs.getSubjectId());
        
    }
    
    @Test
    public void getCourseIdTest(){
        assertEquals("C01",rs.getCourseId());
    }
    
    
    @Test
    public void setCourseIdTest(){
        rs.setCourseId("C03");
        assertEquals("C03",rs.getCourseId());
    }
    

   
}
