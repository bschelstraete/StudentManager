/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

/**
 *
 * @author Jellyfish
 */
public class Partim {
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
    
    public Partim(Integer id, String naam)
    {
        this.id = id;
        this.naam = naam;
    }
}
