/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Deelcompetentie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public class DeelcompetentieDAOImpl implements DeelcompetentieDAO{
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Deelcompetentie> getDeelcompetenties() throws SQLException {
        List<Deelcompetentie> deelcompetentieList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM competentie";
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("ID");
            String beschrijving = rs.getString("beschrijving");

            Deelcompetentie bufDeelcompetentie = new Deelcompetentie(id, beschrijving);
            deelcompetentieList.add(bufDeelcompetentie);
        }
        return deelcompetentieList;
    }
    
    @Override
    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException
    {
        List<Deelcompetentie> deelcompetentieList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM deelcompetentie d JOIN competentie_deelcompetentie cd ON d.ID = cd.deelcompID WHERE cd.compID = " + competentieID;
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("ID");
            String beschrijving = rs.getString("beschrijving");
            Deelcompetentie bufDeelcompetentie = new Deelcompetentie(id, beschrijving);
            deelcompetentieList.add(bufDeelcompetentie);
        }
        return deelcompetentieList;
    }
    
    @Override
    public Deelcompetentie getDeelcompetentie(Integer ID) throws SQLException{
        st = conn.createStatement();
        stringSQL = "SELECT * FROM competentie WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        String beschrijving = "";
        while(rs.next())
        {
            beschrijving = rs.getString("beschrijving");
        }
        return new Deelcompetentie(ID, beschrijving);
    }

    @Override
    public void voegDeelcompetentieToe(String beschrijving) throws SQLException {
            prepSt = conn.prepareStatement("INSERT INTO deelcompetentie(beschrijving) VALUES('" + beschrijving + "')");
            prepSt.executeUpdate();
    }

    @Override
    public void pasDeelcompetentieAan(Deelcompetentie deelcompetentie) throws SQLException {
        prepSt = conn.prepareStatement("UPDATE deelcompetentie SET beschrijving = '" 
                 + deelcompetentie.getBeschrijving()
                 + "' WHERE ID = " + deelcompetentie.getID());
        prepSt.executeUpdate();
    }

    @Override
    public void verwijderDeelcompetentie(Deelcompetentie deelcompetentie) throws SQLException {
        prepSt = conn.prepareStatement("DELETE FROM deelcompetentie WHERE ID = " 
                + deelcompetentie.getID());
        prepSt.executeUpdate();
    }

    public Deelcompetentie getDeelcompetentieByBeschrijving(String beschrijving) throws SQLException{
        st = conn.createStatement();
        stringSQL = "SELECT * FROM deelcompetentie WHERE beschrijving = '" + beschrijving + "'";
        ResultSet rs = st.executeQuery(stringSQL);
        Integer ID = 0;
        while(rs.next())
        {
            ID = rs.getInt("ID");
        }
        return new Deelcompetentie(ID, beschrijving);
    }
    
}
