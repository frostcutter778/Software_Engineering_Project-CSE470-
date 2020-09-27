package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.table2;
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
public class table2test {
    
    table2 ttt;
    @Before
    public void setUp() {
        ttt = new table2("Adittya","abc@gmail.com","CSE",1234);
    }
    
    @Test
    public void getNameTest() {
        assertEquals("Adittya",ttt.getName());
    }
    
    @Test
    public void setNameTest() {
        ttt.setName("Fox");
        assertEquals("Fox",ttt.getName());
    }

    @Test
    public void getEmailTest() {
        assertEquals("abc@gmail.com",ttt.getEmail());
    }
    
    
    @Test
    public void setEmailTest() {
        ttt.setEmail();
        assertEquals("abc@gmail.com",ttt.getEmail());
    }
    
    @Test
    public void getIdTest() {
        assertEquals(1234,ttt.getId());
    }
    
    
    @Test
    public void setIdTest() {
        ttt.setId(321);
        assertEquals(321,ttt.getId());
    }
    
     @Test
    public void getDnameTest() {
        assertEquals("CSE",ttt.getDname());
    }
    
    
     @Test
    public void setDnameTest() {
        ttt.setDname("EEE");
        assertEquals("EEE",ttt.getDname());
    }
    
   
}
