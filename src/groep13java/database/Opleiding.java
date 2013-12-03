/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.database;

/**
 *
 * @author Jellyfish
 */
public class Opleiding {
    private Integer id;
    private String naam;
    
    public Integer getID()
    {
        return id;
    }
    
    public String getNaam()
    {
        return naam;
    }
    
    public Opleiding(Integer id, String naam)
    {
        this.id = id;
        this.naam = naam;
    }
}
