/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.Controller;

import groep13java.Model.Student;
import groep13java.database.Database;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public class Controller {
    private Database db;
    
    public Controller()
    {
        db = new Database();
    }
    
    public List<Student> getStudenten() throws SQLException
    {
        return db.getStudenten();
    }
}
