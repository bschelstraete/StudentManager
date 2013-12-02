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
                Integer opleidingID = rs.getInt("oplID");
                Module bufModule = new Module(id, naam, opleidingID);
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
                Integer moduleID = rs.getInt("modID");
                Partim bufPartim = new Partim(id, naam, moduleID);
                partimList.add(bufPartim);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return partimList;
    }
    
    public void voegStudentToe(Student student)
    {
        try
        {
            prepSt = conn.prepareStatement("INSERT INTO student(ID, familienaam, voornaam) VALUES('?', '?', '?'");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, student.getFamilienaam());
            prepSt.setString(3, student.getVoornaam());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //dosomeshit
        }
    }
    
    public void pasStudentAan(Student student)
    {
            try
            {
                prepSt = conn.prepareStatement("UPDATE student SET voornaam = '?' WHERE ID = " + student.getID());
                prepSt.setString(1, student.getVoornaam());
                prepSt.executeUpdate();
                 prepSt = conn.prepareStatement("UPDATE student SET familienaam = '?' WHERE ID = " + student.getID());
                prepSt.setString(1, student.getFamilienaam());
            }
            catch(SQLException e)
            {
                //DOSOMESHIT
            }
    }
    
    public void verwijderStudent(Student student)
    {
        try
        {
            prepSt = conn.prepareStatement("DELETE FROM student WHERE ID = " + student.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT3
        }        
    }
    
    public void voegCompetentieToe(Competentie competentie)
    {
        try
        {
            prepSt = conn.prepareStatement("INSERT INTO competentie(ID, beschrijving) VALUES('?', '?')");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, competentie.getBeschrijving());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHITYO!
        }
    }
    
    public void pasCompetentieAan(Competentie competentie)
    {
        try
        {
            prepSt = conn.prepareStatement("UPDATE competentie SET beschrijving = '" + competentie.getBeschrijving()
                     + "' WHERE ID = " + competentie.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT
        }
    }
    
    public void verwijderCompetentie(Competentie competentie)
    {
        try
        {
            prepSt = conn.prepareStatement("DELETE FROM competentie WHERE ID = " 
                    + competentie.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT3
        }        
    }
    
    public void voegDeelcompetentieToe(Deelcompetentie deelcompetentie)
    {
        try
        {
            prepSt = conn.prepareStatement("INSERT INTO deelcompetentie (ID, beschrijving) VALUES('?', '?')");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, deelcompetentie.getBeschrijving());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHITYO!
        }
    }
    
    public void pasDeelcompetentieAan(Deelcompetentie deelcompetentie)
    {
        try
        {
            prepSt = conn.prepareStatement("UPDATE deelcompetentie SET beschrijving = '" 
                     + deelcompetentie.getBeschrijving()
                     + "' WHERE ID = " + deelcompetentie.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT
        }
    }
    
    public void verwijderDeelcompetentie(Deelcompetentie deelcompetentie)
    {
        try
        {
            prepSt = conn.prepareStatement("DELETE FROM deelcompetentie WHERE ID = " 
                    + deelcompetentie.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT3
        }   
    }
    
    public void voegOpleidingToe(Opleiding opleiding)
    {
        try
        {
            prepSt = conn.prepareStatement("INSERT INTO opleiding(ID, naam) VALUES('?', '?')");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, opleiding.getNaam());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHITYO!
        }
    }
    
    public void pasOpleidingAan(Opleiding opleiding)
    {
        try
        {
            prepSt = conn.prepareStatement("UPDATE opleiding SET naam = '" + opleiding.getNaam()
                     + "' WHERE ID = " + opleiding.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT
        }
    }
    
    public void verwijderOpleiding(Opleiding opleiding)
    {
        try
        {
            prepSt = conn.prepareStatement("DELETE FROM opleiding WHERE ID = " 
                    + opleiding.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT3
        }   
    }
    
    public void voegModuleToe(Module module)
    {
        try
        {
            prepSt = conn.prepareStatement("INSERT INTO module(ID, naam) VALUES(?, '?', ?)");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, module.getNaam());
            prepSt.setString(3, module.getOpleidingID().toString());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHITYO!
        }
    }
    
    public void pasModuleAan(Module module)
    {
        try
        {
            prepSt = conn.prepareStatement("UPDATE module SET naam = '" + module.getNaam()
                     + "', oplID = " + module.getOpleidingID() 
                     + " WHERE ID = " + module.getID()) ;
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT
        }
    }
    
    public void verwijderModule(Module module)
    {
        try
        {
            prepSt = conn.prepareStatement("DELETE FROM module WHERE ID = " 
                    + module.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT3
        }   
    }
    
    public void voegPartimToe(Partim partim)
    {
        try
        {
            prepSt = conn.prepareStatement("INSERT INTO partim(ID, naam) VALUES('?', '?')");
            prepSt.setString(1, "NULL");
            prepSt.setString(2, partim.getNaam());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHITYO!
        }
    }
    
    public void pasPartimAan(Partim partim)
    {
        try
        {
            prepSt = conn.prepareStatement("UPDATE module SET naam = '" + partim.getNaam()
                     + "', modID = " + partim.getModuleID() +" WHERE ID = " + partim.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT
        }
    }
    
    public void verwijderPartim(Partim partim)
    {
        try
        {
            prepSt = conn.prepareStatement("DELETE FROM partim WHERE ID = " 
                    + partim.getID());
            prepSt.executeUpdate();
        }
        catch(SQLException e)
        {
            //DOSOMESHIT3
        }  
    }
}
