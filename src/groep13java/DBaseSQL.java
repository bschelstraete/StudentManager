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
    private DBConnect dbConnect;
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    
    
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
    
    
    public void voegCompetentieToe()
    {

    }
    public void pasCompetentieAan()
    {

    }
    public void verwijderCompetentie()
    {

    }
    
    public void koppelIndactorAanPartim()
    {
 
    }
    
    public void voerIndactorInStudent()
    {

    }
    
    public void volgStudentOp()
    {

    }
}
