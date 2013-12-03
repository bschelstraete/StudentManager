/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.main;

import groep13java.UI.UIFrame;
import groep13java.database.DBaseSQL;
import groep13java.Model.Student;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBaseSQL dbase;
        dbase = new DBaseSQL();
        List<Student> lijstje;
        new UIFrame();
    }
}