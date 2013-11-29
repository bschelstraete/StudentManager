/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;
import java.sql.*;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jellyfish
 */
public class DBaseSQL {
    private DBConnect dbConnect = DBConnect.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    
    public List<Student> getStudenten()
    {
        ArrayList<Student> studentList = new ArrayList();
        try
        {
            st = conn.createStatement();
            stringSQL = "SELECT * FROM student";
            ResultSet rs = st.executeQuery(stringSQL);
            while(rs.next())
            {
                Integer id = rs.getInt("ID");
                String voornaam = rs.getString("voornaam");
                String familienaam = rs.getString("familienaam");
                Student bufStudent = new Student(id, voornaam, familienaam);
                studentList.add(bufStudent);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        
        return studentList;
    }
    
    public List<Opleiding> getOpleidingen()
    {
        ArrayList<Opleiding> opleidingList = new ArrayList();
        try
        {
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
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return opleidingList;
    }
    
    public List<Competentie> getCompetenties()
    {
        ArrayList<Competentie> competentieList = new ArrayList();
        try
        {
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
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return competentieList;
    }
    
    public List<Deelcompetentie> getDeelcompetentie()
    {
        ArrayList<Deelcompetentie> deelcompetentieList = new ArrayList();
        try
        {
            st = conn.createStatement();
            stringSQL = "SELECT * FROM deelcompetentie";
            ResultSet rs = st.executeQuery(stringSQL);
            while(rs.next())
            {
                Integer id = rs.getInt("ID");
                String beschrijving = rs.getString("beschrijving");
                
                Deelcompetentie bufDeelcompetentie = new Deelcompetentie(id, beschrijving);
                deelcompetentieList.add(bufDeelcompetentie);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return deelcompetentieList;
    }
    
    public List<Module> getModules()
    {
        ArrayList<Module> moduleList = new ArrayList();
        try
        {
            st = conn.createStatement();
            stringSQL = "SELECT * FROM module";
            ResultSet rs = st.executeQuery(stringSQL);
            while(rs.next())
            {
                Integer id = rs.getInt("ID");
                String naam = rs.getString("naam");
                Module bufModule = new Module(id, naam);
                moduleList.add(bufModule);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return moduleList; 
    }
    
    public List<Partim> getPartims()
    {
        ArrayList<Partim> partimList = new ArrayList();
        try
        {
            st = conn.createStatement();
            stringSQL = "SELECT * FROM partim";
            ResultSet rs = st.executeQuery(stringSQL);
            while(rs.next())
            {
                Integer id = rs.getInt("ID");
                String naam = rs.getString("naam");
                Partim bufPartim = new Partim(id, naam);
                partimList.add(bufPartim);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return partimList;
    }
    
    public void voegStudentToe(String voornaam, String familienaam)
    {
        try
        {
            prepSt = conn.prepareStatement("INSERT INTO 'student('ID', 'familienaam', 'voornaam') VALUES('?', '?', '?'");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, familienaam);
            prepSt.setString(3, voornaam);
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //dosomeshit
        }
    }
    
    public void pasStudentAan(Integer id, String naam, Boolean checkVoornaam)
    {
        if(checkVoornaam)
        {
            try
            {
                prepSt = conn.prepareStatement("UPDATE beschrijving SET 'voornaam' = '" + naam + "' WHERE ID = '" + id);
                prepSt.executeUpdate();
            }
            catch(SQLException e)
            {
                //DOSOMESHIT
            }
        }
        else
        {
            try
            {
                prepSt = conn.prepareStatement("UPDATE beschrijving SET 'familienaam' = '" + naam + "' WHERE ID = '" + id);
                prepSt.executeUpdate();
            }
            catch(SQLException e)
            {
                //DOSOMESHIT
            }
        }
    }
    
    public void pasStudentAan(Integer id, String voornaam, String familienaam)
    {
         try
         {
            prepSt = conn.prepareStatement("UPDATE beschrijving SET 'voornaam' = '" + voornaam + "' WHERE ID = '" + id);
            prepSt.executeUpdate();
            prepSt = conn.prepareStatement("UPDATE beschrijving SET 'familienaam' = '" + familienaam + "' WHERE ID = '" + id);
            prepSt.executeUpdate();
         }
         catch(SQLException e)
         {
                //DOSOMESHIT
         }
    }
    
    public void verwijderStudent(Integer id)
    {
        try
        {
            prepSt = conn.prepareStatement("DELETE FROM student WHERE ID = " + id);
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT3
        }        
    }
    
    public void voegCompetentieToe(String beschrijving)
    {
        try
        {
            prepSt = conn.prepareStatement("INSERT INTO 'competentie' ('ID', 'beschrijving') VALUES('?', '?')");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, beschrijving);
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHITYO!
        }
    }
    
    public void pasCompetentieAan(Integer id, String beschrijving)
    {
        try
        {
            prepSt = conn.prepareStatement("UPDATE beschrijving SET 'beschrijving' = '" + beschrijving + "' WHERE ID = '" + id);
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT
        }
    }
    
    public void verwijderCompetentie(Integer id)
    {
        try
        {
            prepSt = conn.prepareStatement("DELETE FROM competentie WHERE ID = " + id);
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT3
        }        
    }
}
