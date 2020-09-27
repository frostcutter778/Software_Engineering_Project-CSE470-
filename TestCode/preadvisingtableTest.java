package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.attend;
import Model.preadvisingtable;
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
public class preadvisingtableTest {
    
    
    preadvisingtable at;
    
    @Before
    public void setUp() {
        at = new preadvisingtable("CSE420");
    }
    
    @Test
    public void getIdTest() {
        assertEquals("CSE420",at.getCourseid());
    }
    @Test
    public void setIdTest() {
        at.setCourseid("CSE197");
        assertEquals("CSE197",at.getCourseid());
    }

}
