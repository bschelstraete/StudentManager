/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Module;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ModuleDAOImpl implements ModuleDAO{
    private DAO dbConnect = DAO.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Module> getModules() throws SQLException {
        List<Module> moduleList = new ArrayList();
        st = conn.createStatement();
        stringSQL = "SELECT * FROM module";
        ResultSet rs = st.executeQuery(stringSQL);
        while(rs.next())
        {
            Integer id = rs.getInt("ID");
            String naam = rs.getString("naam");
            Integer opleidingID = rs.getInt("oplID");
            Module bufModule = new Module(id, naam, opleidingID);
            moduleList.add(bufModule);
        }
        return moduleList;
    }

    @Override
    public Module getModule(Integer ID) throws SQLException {
        st = conn.createStatement();
        stringSQL = "SELECT * FROM module WHERE ID = " + ID;
        ResultSet rs = st.executeQuery(stringSQL);
        Module module = new Module(rs.getInt("ID"), rs.getString("naam"), rs.getInt("oplID"));
        return module;
    }  
}
