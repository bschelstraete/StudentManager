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
    private Integer moduleID;
    
    public Integer getID()
    {
        return id;
    }
    
    public String getNaam()
    {
        return naam;
    }
    public Integer getModuleID()
    {
        return moduleID;
    }
    
    public Partim(Integer id, String naam, Integer moduleID)
    {
        this.id = id;
        this.naam = naam;
        this.moduleID = moduleID;
    }
}
