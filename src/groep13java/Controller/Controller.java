/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.Controller;

import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.Model.Partim;
import groep13java.Model.Student;
import groep13java.database.Database;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jellyfish
 */
public class Controller {
    private Database db;
    
    public Controller()
    {
        db = new Database();
    }
    
    public List<Student> getStudenten() throws SQLException
    {
        return db.getStudenten();
    }
    
    public Student getStudent(Integer ID) throws SQLException
    {
        return db.getStudent(ID);
    }

    public List<Competentie> getCompetenties() throws SQLException{
        return db.getCompetenties();
    }
    
    public List<Deelcompetentie> getDeelcompetenties() throws SQLException
    {
        return db.getDeelcompetenties();
    }
    
    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException
    {
        return db.getDeelcompetentiesByCompetentieID(competentieID);
    }
    public List<Indicator> getIndicatoren() throws SQLException
    {
        return db.getIndicatoren();
    }
    
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompID) throws SQLException
    {
        return db.getIndicatorsByDeelcompetentieID(deelcompID);
    }
    
    public List<Partim> getPartims() throws SQLException
    {
        return db.getPartims();
    }
    
    public Competentie getCompetentieByBeschrijving(String beschrijving) throws SQLException
    {
        return db.getCompetentieByBeschrijving(beschrijving);
    }
    
    public void voegCompetentieToe(String newCompetentie) throws SQLException
    {
        db.voegCompetentieToe(newCompetentie);
    }
    
    public Deelcompetentie getDeelcompetentieByBeschrijving(String beschrijving) throws SQLException
    {
        return db.getDeelcompetentieByBeschrijving(beschrijving);
    }
    
    public void koppelDeelcompetentieAanCompetentie(Integer compID, Integer deelcompID) throws SQLException
    {
        db.koppelDeelcompetentieAanCompetentie(compID, deelcompID);
    }

    public void voegDeelcompetentieToe(String beschrijving) throws SQLException
    {
        db.voegDeelcompetentieToe(beschrijving);
    }
    
    public void voegIndicatorToe(String beschrijving, Integer deelcompetentieID) throws SQLException
    {
        db.voegIndicatorToe(beschrijving, deelcompetentieID);
    }

    public void pasCompetentieAan(Competentie comp) throws SQLException
    {
        db.pasCompetentieAan(comp);
    }

    public void pasDeelcompetentieAan(Deelcompetentie deelcomp) throws SQLException
    {
        db.pasDeelcompetentieAan(deelcomp);
    }

    public void pasIndicatorAan(Indicator indicator) throws SQLException
    {
        db.pasIndicatorAan(indicator);
    }

    public Indicator getIndicatorByBeschrijvingAndDeelcompetentieID(String beschrijving, Integer deelcompetentieID) throws SQLException
    {
        return db.getIndicatorByBeschrijvingAndDeelcompetentieID(beschrijving, deelcompetentieID);
    }

    public void verwijderCompetentie(Competentie competentie) throws SQLException
    {
        db.verwijderCompetentie(competentie);
    }
    
    public void verwijderDeelcompetentie(Deelcompetentie deelcompetentie) throws SQLException
    {
        db.verwijderDeelcompetentie(deelcompetentie);
    }
    
    public void ontKoppelCompetentieMetDeelcompetentie(Competentie competentie) throws SQLException
    {
        db.ontKoppelCompetentieMetDeelcompetentie(competentie);
    }
    
    public void ontKoppelDeelcompetentieMetCompetentie(Deelcompetentie deelcompetentie) throws SQLException
    {
        db.ontKoppelDeelcompetentieMetCompetentie(deelcompetentie);
    }
    
    public void verwijderIndicator(Indicator indicator) throws SQLException
    {
        db.verwijderIndicator(indicator);
    }
    
    public void verwijderIndicatorByDeelcompetentieID(Deelcompetentie deelcompetentie) throws SQLException
    {
        db.verwijderIndicatorByDeelcompetentieID(deelcompetentie);
    }

    public Indicator getIndicatorByBeschrijving(String beschrijving) throws SQLException
    {
        return db.getIndicatorByBeschrijving(beschrijving);
    }
    
    public Integer getPartimIDByIndicatorID(Integer indicatorID) throws SQLException
    {
        return db.getPartimIDByIndicatorID(indicatorID);
    }
    
    public List<Integer> getIndicatorenIDByPartimID(Integer partimID) throws SQLException
    {
        return db.getIndicatorenByPartimID(partimID);
    }
    
    public void koppelIndicatorMetPartim(Integer indicatorID, Integer partimID) throws SQLException
    {
        db.koppelIndicatorMetPartim(indicatorID, partimID);
    }
    
    public void ontkoppelIndicatorMetPartimByIndicatorID(Integer indicatorID) throws SQLException
    {
        db.ontkoppelIndicatorMetPartimByIndicatorID(indicatorID);
    }
    
    public void ontkoppelPartimMetIndicatorenByPartimID(Integer partimID) throws SQLException
    {
        db.ontkoppelPartimMetIndicatorenByPartimID(partimID);
    }

    public Indicator getIndicatorByID(Integer indicatorID) throws SQLException
    {
        return db.getIndicatorByID(indicatorID);
    }

    public Partim getPartimByBeschrijving(String beschrijving) throws SQLException
            {
        return db.getPartimByBeschrijving(beschrijving);
    }

    public Student getStudentByVoornaamEnFamilienaam(String voornaam, String familienaam) throws SQLException
    {
        return db.getStudentByVoornaamEnFamilienaam(voornaam, familienaam);
    }

    public List<Partim> getPartimsByStudentID(Integer studID) throws SQLException 
    {
        return db.getPartimsByStudentID(studID);
    }

    public void insertScoreVoorIndicatorByStudentID(Integer indicatorScore, Integer indicatorID, Integer studentID) throws SQLException
    {
        db.insertScoreVoorIndicatorByStudentID(indicatorScore, indicatorID, studentID);
    }
}
