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
public class DeelcompetentieTest {
    
    public DeelcompetentieTest() {
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
     * Test of getID method, of class Deelcompetentie.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Deelcompetentie instance =new Deelcompetentie(1,"test");
        Integer expResult = 1;
        Integer result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBeschrijving method, of class Deelcompetentie.
     */
    @Test
    public void testGetBeschrijving() {
        System.out.println("getBeschrijving");
        Deelcompetentie instance =new Deelcompetentie(1,"test");
        String expResult = "test";
        String result = instance.getBeschrijving();
        assertEquals(expResult, result);
    }
}