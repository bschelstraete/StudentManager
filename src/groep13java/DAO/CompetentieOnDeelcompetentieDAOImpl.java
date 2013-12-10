/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jellyfish
 */
public class CompetentieOnDeelcompetentieDAOImpl implements CompetentieOnDeelcompetentieDAO{
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public void koppelDeelCompetentieAanCompetentie(Integer compID, Integer deelcompID) throws SQLException {
        prepSt = conn.prepareStatement("INSERT INTO competentie_deelcompetentie(compID, deelcompID) VALUES(" + compID + ", " + deelcompID + ")");
        prepSt.executeUpdate();
    }
    
    @Override
    public void ontKoppelCompetentieMetDeelcompetentie(Competentie competentie) throws SQLException
    {
        prepSt = conn.prepareStatement("DELETE FROM competentie_deelcompetentie WHERE compID = " + competentie.getID());
        prepSt.executeUpdate();
    }
    
    @Override
    public void ontKoppelDeelcompetentieMetCompetentie(Deelcompetentie deelcompetentie) throws SQLException
    {
        prepSt = conn.prepareStatement("DELETE FROM competentie_deelcompetentie WHERE deelcompID = " + deelcompetentie.getID());
        prepSt.executeUpdate();    
    }
    
}
