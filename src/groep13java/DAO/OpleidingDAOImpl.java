/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Opleiding;
import groep13java.Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OpleidingDAOImpl implements OpleidingDAO{
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Opleiding> getOpleidingen() throws SQLException {
        List<Opleiding> opleidingList = new ArrayList();
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
    public Opleiding getOpleidingByStudent(Student student) throws SQLException
    {
        Integer ID = 0;
        String naam = "";
        st = conn.createStatement();
        stringSQL = "SELECT * FROM opleiding o "
                    + "JOIN module m ON m.oplID = o.ID "
                    + "JOIN student_in_module sm ON sm.modID = m.ID "
                    + "WHERE sm.studID = " + student.getID();
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            ID = rs.getInt("ID");
            naam = rs.getString("naam");
        }
        
        return new Opleiding(ID, naam);
    }
}
