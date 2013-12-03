/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.database;

import groep13java.DAO.*;
import groep13java.Model.Student;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public class Database {
    private Connection conn;
    private CompetentieDAOImpl competentieImp;
    private DeelcompetentieDAOImpl deelcompetentieImp;
    private ModuleDAOImpl moduleImp;
    private OpleidingDAOImpl opleidingImp;
    private PartimDAOImpl partimImp;
    private StudentDAOImpl studentImp; 
    
    public Database()
    {
        conn = DAO.getInstance().getConnection();
        competentieImp = new CompetentieDAOImpl();
        deelcompetentieImp = new DeelcompetentieDAOImpl();
        moduleImp = new ModuleDAOImpl();
        opleidingImp = new OpleidingDAOImpl();
        partimImp = new PartimDAOImpl();
        studentImp = new StudentDAOImpl();
    }
    
    public List<Student> getStudenten()
    {
        return studentImp.getStudenten();
    }
}
