/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Deelcompetentie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public interface DeelcompetentieDAO {
    public List<Deelcompetentie> getDeelcompetenties() throws SQLException;
    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException;
    public Deelcompetentie getDeelcompetentie(Integer ID) throws SQLException;
    public void voegDeelcompetentieToe(String beschrijving) throws SQLException;
    public void pasDeelcompetentieAan(Deelcompetentie deelcompetentie) throws SQLException;
    public void verwijderDeelcompetentie(Deelcompetentie deelcompetentie) throws SQLException;    
}
