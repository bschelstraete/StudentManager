/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import java.sql.*;

/**
 *
 * @author Jellyfish
 */
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
    
}
