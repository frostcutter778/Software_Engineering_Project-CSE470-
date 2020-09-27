package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.table1;
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
public class table1test {
    
    table1 tt;
   
    @Before
    public void setUp() {
        tt = new table1("Sopy","sopy@gmail.com",17101123);
    }
    
    @Test
    public void getNameTest() {
        assertEquals("Sopy",tt.getName());
        
    }
    @Test
    public void setNameTest(){
        tt.setName("Doreen");
        assertEquals("Doreen",tt.getName());
    }
    @Test
    public void getEmailTest() {
        assertEquals("sopy@gmail.com",tt.getEmail());
        
    }
    @Test
    public void setEmailTest(){
        tt.setEmail();
        assertEquals("sopy@gmail.com",tt.getEmail());
    }
    @Test
    public void getIDTest() {
        assertEquals(17101123,tt.getId());
        
    }
    @Test
    public void setIDTest(){
        tt.setId(007);
        assertEquals(007,tt.getId());
    }

}
