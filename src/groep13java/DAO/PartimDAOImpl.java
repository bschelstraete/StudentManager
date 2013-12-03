/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Partim;
import groep13java.database.DBConnect;
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
public class PartimDAOImpl implements PartimDAO{
    private DBConnect dbConnect = DBConnect.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;

    @Override
    public List<Partim> getPartims() throws SQLException {
        ArrayList<Partim> partimList = new ArrayList();
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
    public void voegPartimToe(Partim partim) throws SQLException {
        prepSt = conn.prepareStatement("INSERT INTO partim(ID, naam) VALUES('?', '?')");
        prepSt.setString(1, "NULL");
        prepSt.setString(2, partim.getNaam());
        prepSt.executeUpdate();
    }

    @Override
    public void pasPartimAan(Partim partim) throws SQLException {
        prepSt = conn.prepareStatement("UPDATE module SET naam = '" + partim.getNaam()
                 + "', modID = " + partim.getModuleID() +" WHERE ID = " + partim.getID());
        prepSt.executeUpdate();
    }

    @Override
    public void verwijderPartim(Partim partim) throws SQLException {
        prepSt = conn.prepareStatement("DELETE FROM partim WHERE ID = " 
                + partim.getID());
        prepSt.executeUpdate();
    }
}
