/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.main;

import groep13java.UI.MainFrame;
import groep13java.database.Database;
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
        Database dbase;
        dbase = new Database();
        List<Student> lijstje;
        new MainFrame();
    }
}