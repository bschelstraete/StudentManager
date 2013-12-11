/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Indicator;
import groep13java.Model.Partim;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public class IndicatorOnPartimDAOImpl implements IndicatorOnPartimDAO{
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Integer> getIndicatorenByPartimID(Integer partimID) throws SQLException
    {
        List<Integer> indicatorIDList = new ArrayList<>();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM indicatorpartim WHERE partID = " + partimID;
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("indID");
            indicatorIDList.add(id);
        }
        return indicatorIDList;
    }

    @Override
    public Integer getPartimIDByIndicatorID(Integer indicatorID) throws SQLException
    {
        Integer ID = 0;
        st = conn.createStatement();
        stringSQL = "SELECT * FROM indicatorpartim WHERE indID = " + indicatorID;
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            ID = rs.getInt("indID");
        }
        
        return ID;
    }
    
    @Override
    public void koppelIndicatorMetPartim(Integer indicatorID, Integer partimID) throws SQLException
    {
        stringSQL = "INSERT INTO indicatorpartim(indID, partID) VALUES('" + indicatorID + "', " + partimID + ")";
        prepSt = conn.prepareStatement(stringSQL);
        prepSt.executeUpdate();  
    }
    
    @Override
    public void ontkoppelIndicatorMetPartimByIndicatorID(Integer indicatorID) throws SQLException
    {
        prepSt = conn.prepareStatement("DELETE FROM indicatorpartim WHERE indID = " + indicatorID);
        prepSt.executeUpdate();
    }
    
    @Override
    public void ontkoppelPartimMetIndicatorenByPartimID(Integer partimID) throws SQLException
    {
        prepSt = conn.prepareStatement("DELETE FROM indicatorpartim WHERE partID = " + partimID);
        prepSt.executeUpdate();
    }
}
