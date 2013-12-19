/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.Model;


public class Competentie {
    private Integer id;
    private String beschrijving;
    
    public Integer getID()
    {
        return id;
    }
    public String getBeschrijving()
    {
        return beschrijving;
    }
    
    public Competentie(Integer id, String beschrijving)
    {
        this.id = id;
        this.beschrijving = beschrijving;
    }
}
