/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;
import groep13java.Model.Student;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Jellyfish
 */
public interface StudentDAO {
    public List<Student> getStudenten() throws SQLException;
    public Student getStudent(Integer ID) throws SQLException;
    public void voegStudentToe(Student student) throws SQLException;
    public void pasStudentAan(Student student) throws SQLException;
    public void verwijderStudent(Student student) throws SQLException;
}
