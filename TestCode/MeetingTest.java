package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Meeting;
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
public class MeetingTest {
    
    Meeting m;
    @Before
    public void setUp() {
        m = new Meeting("MT01","09:30-11:00");
    }
    
    @Test
    public void getMeetingIdTest() {
        assertEquals("MT01",m.getMeetingId());
    }
    
    @Test
    public void setMeetingIdTest() {
        m.setMeetingId("MT02");
        assertEquals("MT02",m.getMeetingId());
    }
    
    @Test
    public void getMettingTimeTest(){
        assertEquals("09:30-11:00",m.getMeetingTime());
    }

    
    @Test
    public void setMettingTimeTest(){
        m.setMeetingTime("11:00-12:30");
        assertEquals("11:00-12:30",m.getMeetingTime());
    }

   
}
