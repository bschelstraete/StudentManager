/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Indicator;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public interface IndicatorDAO{
    public List<Indicator> getIndicators() throws SQLException;
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompID) throws SQLException;
    public Indicator getIndicator(Integer ID) throws SQLException;
    
}
