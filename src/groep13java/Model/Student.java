/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.Model;


public class Student {
    private Integer id; 
    private String voornaam;
    private String familienaam;
    
    public Integer getID()
    {
        return id;
    }
    public String getVoornaam()
    {
        return voornaam;
    }
    public String getFamilienaam()
    {
        return familienaam;
    }
    
    public Student(Integer id, String voornaam, String familienaam)
    {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
    }
}
