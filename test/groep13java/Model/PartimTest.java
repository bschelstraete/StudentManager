/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HOWENTL
 */
public class PartimTest {
    
    public PartimTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getID method, of class Partim.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Partim instance = new Partim(1, "test", 2);
        Integer expResult = 1;
        Integer result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNaam method, of class Partim.
     */
    @Test
    public void testGetNaam() {
        System.out.println("getNaam");
        Partim instance = new Partim(1, "test", 2);
        String expResult = "test";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getModuleID method, of class Partim.
     */
    @Test
    public void testGetModuleID() {
        System.out.println("getModuleID");
        Partim instance = new Partim(1, "test", 2);
        Integer expResult = 2;
        Integer result = instance.getModuleID();
        assertEquals(expResult, result);

    }
}