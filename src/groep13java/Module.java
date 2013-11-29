/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

/**
 *
 * @author Jellyfish
 */
public class Module {
    private Integer id;
    private String naam;
    
    public Integer getID()
    {
        return id;
    }
    public String naam()
    {
        return naam;
    }
    
    public Module(Integer id, String naam)
    {
        this.id = id;
        this.naam = naam;
    }
}
