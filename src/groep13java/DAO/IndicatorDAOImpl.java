/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

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
        List<Indicator> moduleList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM module";
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("ID");
            String naam = rs.getString("beschrijving");
            Integer opleidingID = rs.getInt("deelcompID");
            Indicator bufIndicator = new Indicator(id, naam, opleidingID);
            moduleList.add(bufIndicator);
        }
        return moduleList;
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
        stringSQL = "SELECT * FROM module WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        Indicator module = new Indicator(rs.getInt("ID"), rs.getString("beschrijving"), rs.getInt("deelcompID"));
        return module;
    }
    
    @Override
    public void voegIndicatortoe(String beschrijving, Integer deelcompetentieID) throws SQLException
    {
        stringSQL = "INSERT INTO indicator(beschrijving, deelcompID) VALUES('" + beschrijving + "', " + deelcompetentieID + ")";
        prepSt = conn.prepareStatement(stringSQL);
        prepSt.executeUpdate();    
    }

    public void pasIndicatorAan(String beschrijving) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
