package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Room;
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
public class roomtest {
    
    Room r;
    @Before
    public void setUp() {
        r = new Room("UB30303",45);
    }
    
    @Test
    public void getNumberTest() {
        assertEquals("UB30303",r.getNumber());
    }
     @Test
    public void getSeatingCapacityTest() {
        assertEquals(45,r.getSeatingCapacity());
        
    }

}
