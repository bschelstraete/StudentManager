/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Opleiding;
import groep13java.Model.Student;
import java.sql.SQLException;
import java.util.List;


public interface OpleidingDAO {
    public List<Opleiding> getOpleidingen() throws SQLException;
    public Opleiding getOpleiding(Integer ID) throws SQLException;
    public Opleiding getOpleidingByStudent(Student student) throws SQLException;
}
