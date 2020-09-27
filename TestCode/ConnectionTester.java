package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.CreatingConnection;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class ConnectionTester {
    
    static CreatingConnection CC;
    

    @Before
    public void setUp() throws Exception {
        CC = new CreatingConnection (); 
    }
    
    @Test
    public void testConnectionTest()
    {
        System.out.println("Created Connection Succesfully");
        //assertEquals(con,CC.con);
    }

}
