/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Partim;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public interface PartimDAO {
    public List<Partim> getPartims() throws SQLException;
    public Partim getPartim(Integer ID) throws SQLException;
    public Partim getPartimByBeschrijving(String beschrijving) throws SQLException;
}
