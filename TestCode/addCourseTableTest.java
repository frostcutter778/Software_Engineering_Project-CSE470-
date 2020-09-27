package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.addcoursetable;
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
public class addCourseTableTest {
    
    addcoursetable act;
    @Before
    public void setUp() {
        act = new addcoursetable("CSE422"); 
    }
    
    @Test
    public void GetCourseTest() {
    assertEquals("CSE422",act.getCourseid());
    }
    
    @Test
    public void SetCourseTest() {
    act.setCourseid("CSE420");
    assertEquals("CSE420",act.getCourseid());
    }
  
}
