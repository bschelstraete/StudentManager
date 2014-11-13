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
public class ModuleTest {
    
    public ModuleTest() {
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
     * Test of getID method, of class Module.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Module instance = new Module(1, "test", 2);
        Integer expResult = 1;
        Integer result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNaam method, of class Module.
     */
    @Test
    public void testGetNaam() {
        System.out.println("getNaam");
        Module instance = new Module(1, "test", 2);
        String expResult = "test";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOpleidingID method, of class Module.
     */
    @Test
    public void testGetOpleidingID() {
        System.out.println("getOpleidingID");
        Module instance = new Module(1, "test", 2);
        Integer expResult = 2;
        Integer result = instance.getOpleidingID();
        assertEquals(expResult, result);
    }
}