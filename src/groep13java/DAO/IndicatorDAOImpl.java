/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Indicator;
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
            String naam = rs.getString("naam");
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
            Indicator bufDeelcompetentie = new Indicator(id, beschrijving, deelcompID);
            indicatorList.add(bufDeelcompetentie);
        }
        return indicatorList;
    }
    
    @Override
    public Indicator getIndicator(Integer ID) throws SQLException {
        st = conn.createStatement();
        stringSQL = "SELECT * FROM module WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        Indicator module = new Indicator(rs.getInt("ID"), rs.getString("naam"), rs.getInt("deelcompID"));
        return module;
    }
    

}
