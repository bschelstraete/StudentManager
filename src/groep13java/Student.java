/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

/**
 *
 * @author Jellyfish
 */
public class Student {
    private int id; 
    private String voornaam;
    private String familienaam;
    
    public int getID()
    {
        return id;
    }
    public String getVoornaam()
    {
        return voornaam;
    }
    public String familienaam()
    {
        return familienaam;
    }
    
    public Student(int id, String voornaam, String familienaam)
    {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
    }
}
