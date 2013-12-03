/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.database;

import groep13java.DAO.*;
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
    
    public List<Student> getStudenten() throws SQLException
    {
        return studentImp.getStudenten();
    }
}
