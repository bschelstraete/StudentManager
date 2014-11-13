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
public class IndicatorTest {
    
    public IndicatorTest() {
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
     * Test of getID method, of class Indicator.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Indicator instance = new Indicator(1, "test", 2);
        Integer expResult =1;
        Integer result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBeschrijving method, of class Indicator.
     */
    @Test
    public void testGetBeschrijving() {
        System.out.println("getBeschrijving");
        Indicator instance = new Indicator(1, "test", 2);
        String expResult = "test";
        String result = instance.getBeschrijving();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDeelcompID method, of class Indicator.
     */
    @Test
    public void testGetDeelcompID() {
        System.out.println("getDeelcompID");
        Indicator instance = new Indicator(1, "test", 2);
        Integer expResult = 2;
        Integer result = instance.getDeelcompID();
        assertEquals(expResult, result);

    }
}