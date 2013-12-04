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
      List<Student> studentList = new ArrayList();
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
}
