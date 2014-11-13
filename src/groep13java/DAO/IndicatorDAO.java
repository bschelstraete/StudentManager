/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.DAO;

import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import java.sql.SQLException;
import java.util.List;


public interface IndicatorDAO{
    public List<Indicator> getIndicatoren() throws SQLException;
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompID) throws SQLException;
    public Indicator getIndicator(Integer ID) throws SQLException;
    public void voegIndicatortoe(String beschrijving, Integer deelcompetentieID) throws SQLException;
    public void verwijderIndicator(Indicator indicator) throws SQLException;
    public void verwijderIndicatorByDeelcompetentieID(Deelcompetentie deelcompetentie) throws SQLException;
}
