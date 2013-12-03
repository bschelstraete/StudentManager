/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Competentie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public interface CompetentieDAO {
    public List<Competentie> getCompetenties() throws SQLException;
    public Competentie getCompetentie(Integer ID)  throws SQLException;
    public void voegCompetentieToe(Competentie competentie)  throws SQLException;
    public void pasCompetentieAan(Competentie competentie)  throws SQLException;
    public void verwijderCompetentie(Competentie competentie)  throws SQLException;    
}
