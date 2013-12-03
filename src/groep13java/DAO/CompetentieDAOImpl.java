/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Competentie;
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
public class CompetentieDAOImpl implements CompetentieDAO {
    private DBConnect dbConnect = DBConnect.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Competentie> getCompetenties() throws SQLException{
        ArrayList<Competentie> competentieList = new ArrayList();
            st = conn.createStatement();
            stringSQL = "SELECT * FROM competentie";
            ResultSet rs = st.executeQuery(stringSQL);
            while(rs.next())
            {
                Integer id = rs.getInt("ID");
                String beschrijving = rs.getString("beschrijving");
                
                Competentie bufCompetentie = new Competentie(id, beschrijving);
                competentieList.add(bufCompetentie);
            }
        return competentieList;
    }

    @Override
    public Competentie getCompetentie(Integer ID) throws SQLException{
            st = conn.createStatement();
            stringSQL = "SELECT * FROM competentie WHERE ID = " + ID;
            ResultSet rs = st.executeQuery(stringSQL);
            Competentie competentie = new Competentie(rs.getInt("ID"), rs.getString("beschrijving"));
        return competentie;
    }

    @Override
    public void voegCompetentieToe(Competentie competentie)  throws SQLException{
            prepSt = conn.prepareStatement("INSERT INTO competentie(ID, beschrijving) VALUES('?', '?')");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, competentie.getBeschrijving());
            prepSt.executeUpdate();
    }

    @Override
    public void pasCompetentieAan(Competentie competentie)  throws SQLException{
            prepSt = conn.prepareStatement("UPDATE competentie SET beschrijving = '" + competentie.getBeschrijving()
                     + "' WHERE ID = " + competentie.getID());
            prepSt.executeUpdate();
    }

    @Override
    public void verwijderCompetentie(Competentie competentie)  throws SQLException{
            prepSt = conn.prepareStatement("DELETE FROM competentie WHERE ID = " 
                    + competentie.getID());
            prepSt.executeUpdate();
    }
    
}
