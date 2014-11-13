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
public class CompetentieTest {
    
    public CompetentieTest() {
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
     * Test of getID method, of class Competentie.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Competentie instance = new Competentie(1,"test");
        Integer expResult = 1;
        Integer result = instance.getID();
        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getBeschrijving method, of class Competentie.
     */
    @Test
    public void testGetBeschrijving() {
        System.out.println("getBeschrijving");
        Competentie instance = new Competentie(1,"test");
        String expResult = "test";
        String result = instance.getBeschrijving();
        assertEquals(expResult, result);
    }
}