/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Student;
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
public class StudentDAOImpl implements StudentDAO{
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Student> getStudenten() throws SQLException {
      ArrayList<Student> studentList = new ArrayList();
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
        return studentList;
    }

    @Override
    public Student getStudent(Integer ID) throws SQLException {
        st = conn.createStatement();
        stringSQL = "SELECT * FROM student WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        Student student  = new Student(rs.getInt("ID"), rs.getString("familienaam"), rs.getString("voornaam"));
        return student;
    }

    @Override
    public void voegStudentToe(Student student) throws SQLException {
        prepSt = conn.prepareStatement("INSERT INTO student(ID, familienaam, voornaam) VALUES('?', '?', '?'");
        prepSt.setString(1, "NULL");
        prepSt.setString(2, student.getFamilienaam());
        prepSt.setString(3, student.getVoornaam());
        prepSt.executeUpdate();
    }

    @Override
    public void pasStudentAan(Student student) throws SQLException {
        prepSt = conn.prepareStatement("UPDATE student SET voornaam = '?' WHERE ID = " + student.getID());
        prepSt.setString(1, student.getVoornaam());
        prepSt.executeUpdate();
         prepSt = conn.prepareStatement("UPDATE student SET familienaam = '?' WHERE ID = " + student.getID());
        prepSt.setString(1, student.getFamilienaam());
    }

    @Override
    public void verwijderStudent(Student student) throws SQLException {
        prepSt = conn.prepareStatement("DELETE FROM student WHERE ID = " + student.getID());
        prepSt.executeUpdate();
    }
}
