/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.database;

import groep13java.DAO.*;
import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
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
    private final IndicatorDAOImpl indicatorImp;
    private final CompetentieOnDeelcompetentieDAOImpl competentieOnDeelcompetentieImpl;
    public Database()
    {
        conn = DAO.getInstance().getConnection();
        competentieImp = new CompetentieDAOImpl();
        deelcompetentieImp = new DeelcompetentieDAOImpl();
        moduleImp = new ModuleDAOImpl();
        opleidingImp = new OpleidingDAOImpl();
        partimImp = new PartimDAOImpl();
        studentImp = new StudentDAOImpl();
        indicatorImp = new IndicatorDAOImpl();
        competentieOnDeelcompetentieImpl = new CompetentieOnDeelcompetentieDAOImpl();
    }
    
    public List<Student> getStudenten() throws SQLException
    {
        return studentImp.getStudenten();
    }

    public Student getStudent(Integer ID) throws SQLException
    {
        return studentImp.getStudent(ID);
    }

    public List<Competentie> getCompetenties() throws SQLException{
       return competentieImp.getCompetenties();
    }
    
    public Competentie getCompetentieByBeschrijving(String beschrijving) throws SQLException
    {
          return competentieImp.getCompetentieByBeschrijving(beschrijving);
    }
    
    public void voegCompetentieToe(String newCompetentie) throws SQLException
    {
        competentieImp.voegCompetentieToe(newCompetentie);
    }
    
    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException
    {
        return deelcompetentieImp.getDeelcompetentiesByCompetentieID(competentieID);
    }
    
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompID) throws SQLException
    {
        return indicatorImp.getIndicatorsByDeelcompetentieID(deelcompID);
    }
    
    public void koppelDeelcompetentieAanCompetentie(Integer compID, Integer deelcompID) throws SQLException
    {
        competentieOnDeelcompetentieImpl.koppelDeelCompetentieAanCompetentie(compID, deelcompID);
    }

    public Deelcompetentie getDeelcompetentieByBeschrijving(String beschrijving) throws SQLException
    {
        return deelcompetentieImp.getDeelcompetentieByBeschrijving(beschrijving);
    }

    public void voegDeelcompetentieToe(String beschrijving) throws SQLException
    {
        deelcompetentieImp.voegDeelcompetentieToe(beschrijving);
    }
    
    public void voegIndicatorToe(String beschrijving, Integer deelcompetentieID) throws SQLException
    {
        indicatorImp.voegIndicatortoe(beschrijving, deelcompetentieID);
    }

    public List<Deelcompetentie> getDeelcompetenties() throws SQLException
    {
        return deelcompetentieImp.getDeelcompetenties();
    }

    public void pasCompetentieAan(Competentie comp) throws SQLException
    {
       competentieImp.pasCompetentieAan(comp);
    }
    
    public void pasDeelcompetentieAan(Deelcompetentie deelcomp) throws SQLException
    {
       deelcompetentieImp.pasDeelcompetentieAan(deelcomp);
    }
    
    public void pasIndicatorAan(Indicator indicator) throws SQLException
    {
       indicatorImp.pasIndicatorAan(indicator);
    }

    public Indicator getIndicatorByBeschrijvingAndDeelcompetentieID(String beschrijving, Integer deelcompetentieID) throws SQLException
    {
       return indicatorImp.getIndicatorByBeschrijvingAndDeelcompetentieID(beschrijving, deelcompetentieID);
    }
    
    public void ontKoppelCompetentieMetDeelcompetentie(Competentie competentie) throws SQLException
    {
        competentieOnDeelcompetentieImpl.ontKoppelCompetentieMetDeelcompetentie(competentie);
    }
    
    public void ontKoppelDeelcompetentieMetCompetentie(Deelcompetentie deelcompetentie) throws SQLException
    {
        competentieOnDeelcompetentieImpl.ontKoppelDeelcompetentieMetCompetentie(deelcompetentie);
    }
    
    public void verwijderCompetentie(Competentie competentie) throws SQLException
    {
        competentieImp.verwijderCompetentie(competentie);
    }
    
    public void verwijderDeelcompetentie(Deelcompetentie deelcompetentie) throws SQLException
    {
        deelcompetentieImp.verwijderDeelcompetentie(deelcompetentie);
    }
    
    public void verwijderIndicator(Indicator indicator) throws SQLException
    {
        indicatorImp.verwijderIndicator(indicator);
    }
    
    public void verwijderIndicatorByDeelcompetentieID(Deelcompetentie deelcompetentie) throws SQLException
    {
        indicatorImp.verwijderIndicatorByDeelcompetentieID(deelcompetentie);
    }

    public Indicator getIndicatorByBeschrijving(String beschrijving) throws SQLException
    {
        return indicatorImp.getIndicatorByBeschrijving(beschrijving);
    }
}
