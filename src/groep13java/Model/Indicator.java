/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.Model;

/**
 *
 * @author Jellyfish
 */
public class Indicator {
    private Integer  id;
    private String naam;
    private Integer deelcompID;
    
    public Integer getID()
    {
        return id;
    }
    
    public String getBeschrijving()
    {
        return naam;
    }
    
    public Integer getDeelcompID()
    {
        return deelcompID;
    }
    
    public Indicator(Integer id, String naam, Integer deelcompID)
    {
        this.id = id;
        this.naam = naam;
        this.deelcompID = deelcompID;
    }
    
    
}
