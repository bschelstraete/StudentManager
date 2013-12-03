/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Module;
import groep13java.database.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public class ModuleDAOImpl implements ModuleDAO{
    private DBConnect dbConnect = DBConnect.getInstance();
    private Connection conn = dbConnect.getConnection();
    private String stringSQL;
    private Statement st;
    private PreparedStatement prepSt;
    
    @Override
    public List<Module> getModules() throws SQLException {
        ArrayList<Module> moduleList = new ArrayList();
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

    @Override
    public void voegModuleToe(Module module) throws SQLException {
        prepSt = conn.prepareStatement("INSERT INTO module(ID, naam) VALUES(?, '?', ?)");
        prepSt.setString(1, "NULL");
        prepSt.setString(2, module.getNaam());
        prepSt.setString(3, module.getOpleidingID().toString());
        prepSt.executeUpdate();
    }

    @Override
    public void pasModuleAan(Module module) throws SQLException {
        prepSt = conn.prepareStatement("UPDATE module SET naam = '" + module.getNaam()
                 + "', oplID = " + module.getOpleidingID() 
                 + " WHERE ID = " + module.getID()) ;
        prepSt.executeUpdate();
    }

    @Override
    public void verwijderModule(Module module) throws SQLException {
         prepSt = conn.prepareStatement("DELETE FROM module WHERE ID = " 
                + module.getID());
        prepSt.executeUpdate();
    }
    
}
