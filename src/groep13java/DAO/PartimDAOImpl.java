/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Partim;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PartimDAOImpl implements PartimDAO{
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;

    @Override
    public List<Partim> getPartims() throws SQLException {
        List<Partim> partimList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM partim";
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("ID");
            String naam = rs.getString("naam");
            Integer moduleID = rs.getInt("modID");
            Partim bufPartim = new Partim(id, naam, moduleID);
            partimList.add(bufPartim);
        }    
        return partimList;
    }

    @Override
    public Partim getPartim(Integer ID) throws SQLException {
        st = conn.createStatement();
        stringSQL = "SELECT * FROM partim WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        Partim partim = new Partim(rs.getInt("ID"), rs.getString("naam"), rs.getInt("modID"));
        return partim;
    }

    @Override
    public Partim getPartimByBeschrijving(String beschrijving) throws SQLException
    {
       st = conn.createStatement();
       stringSQL = "SELECt * FROM partim WHERE naam = '" + beschrijving + "'";
       ResultSet rs = st.executeQuery(stringSQL);
       Integer ID = 0;
       Integer modID = 0;
       while(rs.next())
       {
           ID = rs.getInt("ID");
           modID = rs.getInt("modID");
       }
       
       return new Partim(ID, beschrijving, modID);
    }
    
    @Override
    public List<Partim> getPartimListByStudID(Integer studID) throws SQLException
    {
        List<Partim> partimList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM partim p\n" +
                    "JOIN module m ON m.ID = p.modID \n" +
                    "JOIN student_in_module sm ON sm.modID = m.ID \n" +
                    "WHERE sm.studID = " + studID;
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Partim partim = new Partim(rs.getInt("ID"), rs.getString("naam"), rs.getInt("modID"));
            partimList.add(partim);
        }
        
        return partimList;
    }
}
