/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.database;

import groep13java.DAO.*;
import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.Model.Student;
import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Jellyfish
 */
public class Database {
    private final Connection conn;
    private final CompetentieDAOImpl competentieImp;
    private final DeelcompetentieDAOImpl deelcompetentieImp;
    private final ModuleDAOImpl moduleImp;
    private final OpleidingDAOImpl opleidingImp;
    private final PartimDAOImpl partimImp;
    private final StudentDAOImpl studentImp; 
    private final IndicatorDAOImpl indicatorImp;
    
    public Database()
    {
        conn = DAO.getInstance().getConnection();
        competentieImp = new CompetentieDAOImpl();
        deelcompetentieImp = new DeelcompetentieDAOImpl();
        moduleImp = new ModuleDAOImpl();
        opleidingImp = new OpleidingDAOImpl();
        partimImp = new PartimDAOImpl();
        studentImp = new StudentDAOImpl();
        indicatorImp = new IndicatorDAOImpl();
    }
    
    public List<Student> getStudenten() throws SQLException
    {
        return studentImp.getStudenten();
    }

    public Student getStudent(Integer ID) throws SQLException
    {
        return studentImp.getStudent(ID);
    }

    public List<Competentie> getCompetenties() throws SQLException{
       return competentieImp.getCompetenties();
    }
    
    public void voegCompetentieToe(String newCompetentie) throws SQLException
    {
        competentieImp.voegCompetentieToe(newCompetentie);
    }
    
    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException
    {
        return deelcompetentieImp.getDeelcompetentiesByCompetentieID(competentieID);
    }
    
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompID) throws SQLException
    {
        return indicatorImp.getIndicatorsByDeelcompetentieID(deelcompID);
    }
    

}
