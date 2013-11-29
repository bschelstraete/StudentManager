/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

import java.util.List;

/**
 *
 * @author Jellyfish
 */
public class Groep13Java {

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
            System.out.println(lijstje.get(i).familienaam());
        }
    }
}