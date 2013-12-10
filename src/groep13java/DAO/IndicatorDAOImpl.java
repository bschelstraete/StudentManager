/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
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
public class IndicatorDAOImpl implements IndicatorDAO{

    
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Indicator> getIndicators() throws SQLException
    {
        List<Indicator> indicatorList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM indicator";
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("ID");
            String naam = rs.getString("beschrijving");
            Integer opleidingID = rs.getInt("deelcompID");
            Indicator bufIndicator = new Indicator(id, naam, opleidingID);
            indicatorList.add(bufIndicator);
        }
        return indicatorList;
    }
    
    @Override
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompID) throws SQLException
    {
        List<Indicator> indicatorList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM indicator WHERE deelcompID = " + deelcompID;
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("ID");
            String beschrijving = rs.getString("beschrijving");
            Indicator bufIndicator = new Indicator(id, beschrijving, deelcompID);
            indicatorList.add(bufIndicator);
        }
        return indicatorList;
    }
    
    @Override
    public Indicator getIndicator(Integer ID) throws SQLException {
        st = conn.createStatement();
        stringSQL = "SELECT * FROM indicator WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        Indicator indicator = new Indicator(rs.getInt("ID"), rs.getString("beschrijving"), rs.getInt("deelcompID"));
        return indicator;
    }
    
    @Override
    public void voegIndicatortoe(String beschrijving, Integer deelcompetentieID) throws SQLException
    {
        stringSQL = "INSERT INTO indicator(beschrijving, deelcompID) VALUES('" + beschrijving + "', " + deelcompetentieID + ")";
        prepSt = conn.prepareStatement(stringSQL);
        prepSt.executeUpdate();    
    }

    public void pasIndicatorAan(Indicator indicator) throws SQLException
    {
        prepSt = conn.prepareStatement("UPDATE indicator SET beschrijving = '" + indicator.getBeschrijving()
         + "' WHERE ID = " + indicator.getID());
        prepSt.executeUpdate();
    }    

    public Indicator getIndicatorByBeschrijvingAndDeelcompetentieID(String beschrijving, Integer deelcompetentieID) throws SQLException
    {
        st = conn.createStatement();
        stringSQL = "SELECT * FROM indicator WHERE beschrijving = '" + beschrijving + "' AND deelcompID = " + deelcompetentieID;
        ResultSet rs = st.executeQuery(stringSQL);
        Integer ID = 0;
        String indBeschrijving = "";
        Integer deelcompID = 0;
        
        while(rs.next())
        {
            ID = rs.getInt("ID");
            indBeschrijving = rs.getString("beschrijving");
            deelcompID = rs.getInt("deelcompID");
        }
        return new Indicator(ID, indBeschrijving, deelcompID);
    }

    @Override
    public void verwijderIndicator(Indicator indicator) throws SQLException {
        prepSt = conn.prepareStatement("DELETE FROM indicator WHERE ID = " + indicator.getID());
        prepSt.executeUpdate();
    }

    @Override
    public void verwijderIndicatorByDeelcompetentieID(Deelcompetentie deelcompetentie) throws SQLException {
        prepSt = conn.prepareStatement("DELETE FROM indicator WHERE deelcompID = " + deelcompetentie.getID());
        prepSt.executeUpdate();
    }

    public Indicator getIndicatorByBeschrijving(String beschrijving) throws SQLException
    {
        st = conn.createStatement();
        stringSQL = "SELECT * FROM indicator WHERE beschrijving = '" + beschrijving + "'";
        ResultSet rs = st.executeQuery(stringSQL);
        Integer ID = 0;
        String indBeschrijving = "";
        Integer deelcompID = 0;
        
        while(rs.next())
        {
            ID = rs.getInt("ID");
            indBeschrijving = rs.getString("beschrijving");
            deelcompID = rs.getInt("deelcompID");
        }
        return new Indicator(ID, indBeschrijving, deelcompID);
    }
}
