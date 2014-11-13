/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import java.sql.SQLException;


public interface CompetentieOnDeelcompetentieDAO {
    public void koppelDeelCompetentieAanCompetentie(Integer compID, Integer deelcompID) throws SQLException;  
    public void ontKoppelCompetentieMetDeelcompetentie(Competentie competentie) throws SQLException;
    public void ontKoppelDeelcompetentieMetCompetentie(Deelcompetentie deelcompetentie) throws SQLException;
}
