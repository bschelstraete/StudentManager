/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Deelcompetentie;
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
public class DeelcompetentieDAOImpl implements DeelcompetentieDAO{
    private DBConnect dbConnect = DBConnect.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Deelcompetentie> getDeelcompetenties() throws SQLException {
        ArrayList<Deelcompetentie> deelcompetentieList = new ArrayList();
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
    public Deelcompetentie getDeelcompetentie(Integer ID) throws SQLException{
        st = conn.createStatement();
        stringSQL = "SELECT * FROM competentie WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        Deelcompetentie deelcompetentie = new Deelcompetentie(rs.getInt("ID"), rs.getString("beschrijving"));
        return deelcompetentie;
    }

    @Override
    public void voegDeelcompetentieToe(Deelcompetentie deelcompetentie) throws SQLException {
        prepSt = conn.prepareStatement("INSERT INTO deelcompetentie (ID, beschrijving) VALUES('?', '?')");
        prepSt.setString(1, "NULL");
        prepSt.setString(2, deelcompetentie.getBeschrijving());
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
    
}
