/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Opleiding;
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
public class OpleidingDAOImpl implements OpleidingDAO{
    private DBConnect dbConnect = DBConnect.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Opleiding> getOpleidingen() throws SQLException {
        ArrayList<Opleiding> opleidingList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM opleiding";
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("ID");
            String naam = rs.getString("naam");

            Opleiding bufOpleiding = new Opleiding(id, naam);
            opleidingList.add(bufOpleiding);
        }
        return opleidingList;
    }

    @Override
    public Opleiding getOpleiding(Integer ID) throws SQLException {
        ArrayList<Opleiding> opleidingList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM opleiding WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        Opleiding opleiding = new Opleiding(rs.getInt("ID"), rs.getString("naam"));
        return opleiding;
    }

    @Override
    public void voegOpleidingToe(Opleiding opleiding) throws SQLException {
        prepSt = conn.prepareStatement("INSERT INTO opleiding(ID, naam) VALUES('?', '?')");
        prepSt.setString(1, "NULL");
        prepSt.setString(2, opleiding.getNaam());
        prepSt.executeUpdate();
    }

    @Override
    public void pasOpleidingAan(Opleiding opleiding) throws SQLException {
        prepSt = conn.prepareStatement("UPDATE opleiding SET naam = '" + opleiding.getNaam()
                 + "' WHERE ID = " + opleiding.getID());
        prepSt.executeUpdate();
    }

    @Override
    public void verwijderOpleiding(Opleiding opleiding) throws SQLException {
        prepSt = conn.prepareStatement("DELETE FROM opleiding WHERE ID = " 
                + opleiding.getID());
        prepSt.executeUpdate();
    }
    
}
