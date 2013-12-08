/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.Controller;

import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
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
    
    public Student getStudent(Integer ID) throws SQLException
    {
        return db.getStudent(ID);
    }

    public List<Competentie> getCompetenties() throws SQLException{
        return db.getCompetenties();
    }
    
    public void voegCompetentieToe(String newCompetentie) throws SQLException
    {
        db.voegCompetentieToe(newCompetentie);
    }
    
    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException
    {
        return db.getDeelcompetentiesByCompetentieID(competentieID);
    }
    
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompID) throws SQLException
    {
        return db.getIndicatorsByDeelcompetentieID(deelcompID);
    }
}
