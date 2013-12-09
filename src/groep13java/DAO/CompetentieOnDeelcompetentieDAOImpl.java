/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

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
        prepSt = conn.prepareStatement("INSERT INTO TABLE competentie_deelcompetentie(compID, deelcompID) "
                                      + "VALUES('" + compID + ", " + deelcompID + ")");
        prepSt.executeUpdate();
    }
    
}
