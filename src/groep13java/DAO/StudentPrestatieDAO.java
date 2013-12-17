/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Student;
import groep13java.Model.StudentPrestatie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public interface StudentPrestatieDAO 
{
    public void insertScoreVoorIndicatorByStudentID(Integer indicatorScore, Integer indicatorID, Integer studentID) throws SQLException;
    public List<StudentPrestatie> getPrestatieByStudent(Student student) throws SQLException;
}
