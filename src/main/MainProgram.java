/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import groep13java.database.DBaseSQL;
import groep13java.UI.UIFrame;
import groep13java.database.DBaseSQL;
import groep13java.database.Student;
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
        lijstje = dbase.getStudenten();

        for(int i = 0; i < lijstje.size(); i++)
        {
            System.out.println(lijstje.get(i).getFamilienaam());
        }
        new UIFrame();
    }
}