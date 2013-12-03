/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Module;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public interface ModuleDAO {
    public List<Module> getModules() throws SQLException;
    public Module getModule(Integer ID) throws SQLException;
    public void voegModuleToe(Module module) throws SQLException;
    public void pasModuleAan(Module module) throws SQLException;
    public void verwijderModule(Module module) throws SQLException;
}
