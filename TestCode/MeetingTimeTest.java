package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.MeetingTime;
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
public class MeetingTimeTest {
    
    
    MeetingTime mt;
    @Before
    public void setUp() {
        mt = new MeetingTime("10","Tuesday");
    }
    
    @Test
    public void TestId() {
    assertEquals("10",mt.getId());
    }
    @Test
    public void TestTime() {
    assertEquals("Tuesday",mt.getTime()); 
    }

}
