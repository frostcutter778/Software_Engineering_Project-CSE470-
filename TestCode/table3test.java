package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.table3;
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
public class table3test {
    
    table3 tttt;
   
    @Before
    public void setUp() {
        tttt = new table3("Adi","def@gmail.com","faculty",456,"waiting");
    }
    
    @Test
    public void getNameTest() {
        assertEquals("Adi",tttt.getName());
    }
    
    @Test
    public void setNameTest() {
        tttt.setName("Vox");
        assertEquals("Vox",tttt.getName());
    }
    
    @Test
    public void getEmailTest() {
        assertEquals("def@gmail.com",tttt.getEmail());
    }
    
    
    @Test
    public void setEmailTest() {
        tttt.setEmail();
        assertEquals("def@gmail.com",tttt.getEmail());
    }
 
    @Test
    public void getUserTypeTest() {
        assertEquals("faculty",tttt.getUsertype());
    } 
    
    
    @Test
    public void setUserTypeTest() {
        tttt.setUsertype("Student");
        assertEquals("Student",tttt.getUsertype());
    } 
    
    @Test
    public void getIdTest() {
        assertEquals(456,tttt.getId());
    }
    
    
    @Test
    public void setIdTest() {
        tttt.setId(213);
        assertEquals(213,tttt.getId());
    }
    
    @Test
    public void getStatusTest(){
        assertEquals("waiting",tttt.getStatus());
    }
    
     @Test
    public void setStatusTest(){
        tttt.setStatus("Approved");
        assertEquals("Approved",tttt.getStatus());
    }
}
