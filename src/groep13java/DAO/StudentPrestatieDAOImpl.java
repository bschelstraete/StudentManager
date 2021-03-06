/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Indicator;
import groep13java.Model.Student;
import groep13java.Model.StudentPrestatie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentPrestatieDAOImpl implements StudentPrestatieDAO{
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
     
    @Override
    public void insertScoreVoorIndicatorByStudentID(Integer indicatorScore, Integer indicatorID, Integer studentID) throws SQLException 
    {
        stringSQL = "INSERT INTO studentprestatie(studID, indID, score) VALUES(" + studentID + ", " + indicatorID + ", " + indicatorScore + ")";
        prepSt = conn.prepareStatement(stringSQL);
        prepSt.executeUpdate();
    }
    
    @Override
    public Integer getScoreByIndicatorAndStudent(Indicator indicator, Student student) throws SQLException
    {
        Integer score = 0;
        st = conn.createStatement();
        stringSQL = "SELECT * FROM studentprestatie WHERE indID = " + indicator.getID() + " AND studID = " + student.getID();
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            score = rs.getInt("score");
        }
        
        return score;
    }
    
    @Override
    public List<StudentPrestatie> getPrestatieByStudent(Student student) throws SQLException
    {
        List<StudentPrestatie> prestatieList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM studentprestatie WHERE studID = " + student.getID();
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            StudentPrestatie prestatie = new StudentPrestatie(rs.getInt("studID"), rs.getInt("indID"), rs.getInt("score"));
            prestatieList.add(prestatie);
        }
        
        return prestatieList;        
    }
    
    @Override
    public List<StudentPrestatie> getPrestatieByStudentAndDeelcompetentieID(Student student, Integer deelcompID) throws SQLException
    {
        List<StudentPrestatie> prestatieList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM studentprestatie sp "
                    + "JOIN indicator i ON i.ID = sp.indID "
                    + "WHERE i.deelcompID = " + deelcompID + "AND sp.studID = " + student.getID();
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            StudentPrestatie prestatie = new StudentPrestatie(rs.getInt("studID"), rs.getInt("indID"), rs.getInt("score"));
            prestatieList.add(prestatie);
        }
        
        return prestatieList;
    }
}
