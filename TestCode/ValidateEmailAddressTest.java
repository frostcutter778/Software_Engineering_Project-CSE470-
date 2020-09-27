package TestCode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.ValidateEmailAddress;
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
public class ValidateEmailAddressTest {
    ValidateEmailAddress vea;
    
    @Before
    public void setUp() {
        vea = new ValidateEmailAddress();
    }
    
    @Test
    public void Test() {
        assertEquals(false,vea.isAddressValid("rasred7@gmal.com")); // expected false
        assertEquals(true,vea.isAddressValid("rasred75@gmail.com")); // expected true
    }

  
}
