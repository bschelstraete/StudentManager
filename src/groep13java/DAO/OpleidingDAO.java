/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Opleiding;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public interface OpleidingDAO {
    public List<Opleiding> getOpleidingen() throws SQLException;
    public Opleiding getOpleiding(Integer ID) throws SQLException;
}
