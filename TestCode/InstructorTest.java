package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Instructor;
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
public class InstructorTest {
    
    public Instructor i;
    
    @Before
    public void setUp() {
        i = new Instructor("170","Adittya");
    }
    
    @Test
    public void getIDtest() {
        assertEquals("170",i.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals("Adittya",i.getName());
    }
   
}
