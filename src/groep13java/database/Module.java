/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.database;

/**
 *
 * @author Jellyfish
 */
public class Module {
    private Integer id;
    private String naam;
    private Integer opleidingID;
    
    public Integer getID()
    {
        return id;
    }
    public String getNaam()
    {
        return naam;
    }
    public Integer getOpleidingID()
    {
        return opleidingID;
    }
    
    public Module(Integer id, String naam, Integer opleidingID)
    {
        this.id = id;
        this.naam = naam;
        this.opleidingID = opleidingID;
    }
}
