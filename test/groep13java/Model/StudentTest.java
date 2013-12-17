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
public class StudentTest {
    
    public StudentTest() {
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
     * Test of getID method, of class Student.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Student instance = new Student(1, "test", "foobar");
        Integer expResult = 1;
        Integer result = instance.getID();
        assertEquals(expResult, result);

    }

    /**
     * Test of getVoornaam method, of class Student.
     */
    @Test
    public void testGetVoornaam() {
        System.out.println("getVoornaam");
        Student instance = new Student(1, "test", "foobar");
        String expResult = "test";
        String result = instance.getVoornaam();
        assertEquals(expResult, result);

    }

    /**
     * Test of getFamilienaam method, of class Student.
     */
    @Test
    public void testGetFamilienaam() {
        System.out.println("getFamilienaam");
        Student instance = new Student(1, "test", "foobar");
        String expResult = "foobar";
        String result = instance.getFamilienaam();
        assertEquals(expResult, result);

    }
}