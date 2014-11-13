/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import java.sql.SQLException;
import java.util.List;


public interface IndicatorOnPartimDAO {
    public List<Integer> getIndicatorenByPartimID(Integer partimID) throws SQLException;
    public Integer getPartimIDByIndicatorID(Integer indicatorID) throws SQLException;
    public void koppelIndicatorMetPartim(Integer indicatorID, Integer partimID) throws SQLException;
    public void ontkoppelIndicatorMetPartimByIndicatorID(Integer indicatorID) throws SQLException;
    public void ontkoppelPartimMetIndicatorenByPartimID(Integer partimID) throws SQLException;
}
