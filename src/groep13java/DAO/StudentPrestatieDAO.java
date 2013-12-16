/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import java.sql.SQLException;

/**
 *
 * @author Jellyfish
 */
public interface StudentPrestatieDAO 
{
    public void insertScoreVoorIndicatorByStudentID(Integer indicatorScore, Integer indicatorID, Integer studentID) throws SQLException;
}
