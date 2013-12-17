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
public class OpleidingTest {
    
    public OpleidingTest() {
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
     * Test of getID method, of class Opleiding.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Opleiding instance = new Opleiding(1, "test");
        Integer expResult = 1;
        Integer result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNaam method, of class Opleiding.
     */
    @Test
    public void testGetNaam() {
        System.out.println("getNaam");
        Opleiding instance = new Opleiding(1, "test");
        String expResult = "test";
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }
}