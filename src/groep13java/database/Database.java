/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.database;

import groep13java.DAO.*;
import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.Model.Partim;
import groep13java.Model.Student;
import groep13java.Model.StudentPrestatie;
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
    private final CompetentieOnDeelcompetentieDAOImpl competentieOnDeelcompetentieImp;
    private final IndicatorOnPartimDAOImpl indicatorOnPartimImp;
    private final StudentPrestatieDAOImpl studentPrestatieImp;
            
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
        competentieOnDeelcompetentieImp = new CompetentieOnDeelcompetentieDAOImpl();
        indicatorOnPartimImp = new IndicatorOnPartimDAOImpl();
        studentPrestatieImp = new StudentPrestatieDAOImpl();
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
    
    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException
    {
        return deelcompetentieImp.getDeelcompetentiesByCompetentieID(competentieID);
    }
    
    public List<Indicator> getIndicatoren() throws SQLException
    {
        return indicatorImp.getIndicatoren();
    }
    
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompID) throws SQLException
    {
        return indicatorImp.getIndicatorsByDeelcompetentieID(deelcompID);
    }
    
    public List<Partim> getPartims() throws SQLException
    {
        return partimImp.getPartims();
    }
    
    public Competentie getCompetentieByBeschrijving(String beschrijving) throws SQLException
    {
          return competentieImp.getCompetentieByBeschrijving(beschrijving);
    }
    
    public void voegCompetentieToe(String newCompetentie) throws SQLException
    {
        competentieImp.voegCompetentieToe(newCompetentie);
    }
    
    public void koppelDeelcompetentieAanCompetentie(Integer compID, Integer deelcompID) throws SQLException
    {
        competentieOnDeelcompetentieImp.koppelDeelCompetentieAanCompetentie(compID, deelcompID);
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
        competentieOnDeelcompetentieImp.ontKoppelCompetentieMetDeelcompetentie(competentie);
    }
    
    public void ontKoppelDeelcompetentieMetCompetentie(Deelcompetentie deelcompetentie) throws SQLException
    {
        competentieOnDeelcompetentieImp.ontKoppelDeelcompetentieMetCompetentie(deelcompetentie);
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
    
    public List<Integer> getIndicatorenByPartimID(Integer partimID) throws SQLException
    {
        return indicatorOnPartimImp.getIndicatorenByPartimID(partimID);
    }
    
    public Integer getPartimIDByIndicatorID(Integer indicatorID) throws SQLException
    {
        return indicatorOnPartimImp.getPartimIDByIndicatorID(indicatorID);
    }
    
    public void koppelIndicatorMetPartim(Integer indicatorID, Integer partimID) throws SQLException
    {
        indicatorOnPartimImp.koppelIndicatorMetPartim(indicatorID, partimID);
    }
    
    public void ontkoppelIndicatorMetPartimByIndicatorID(Integer indicatorID) throws SQLException
    {
        indicatorOnPartimImp.ontkoppelIndicatorMetPartimByIndicatorID(indicatorID);
    }
    
    public void ontkoppelPartimMetIndicatorenByPartimID(Integer partimID) throws SQLException
    {
        indicatorOnPartimImp.ontkoppelPartimMetIndicatorenByPartimID(partimID);
    }

    public Indicator getIndicatorByID(Integer indicatorID) throws SQLException
    {
       return indicatorImp.getIndicator(indicatorID);
    }

    public Partim getPartimByBeschrijving(String beschrijving) throws SQLException
    {
        return partimImp.getPartimByBeschrijving(beschrijving);
    }

    public Student getStudentByVoornaamEnFamilienaam(String voornaam, String familienaam) throws SQLException
    {
        return studentImp.getStudentByVoornaamEnFamilienaam(voornaam, familienaam);
    }

    public List<Partim> getPartimsByStudentID(Integer studID) throws SQLException
    {
        return partimImp.getPartimListByStudID(studID);
    }

    public void insertScoreVoorIndicatorByStudentID(Integer indicatorScore, Integer indicatorID, Integer studentID) throws SQLException
    {
        studentPrestatieImp.insertScoreVoorIndicatorByStudentID(indicatorScore, indicatorID, studentID);
    }
    
    public List<StudentPrestatie> getPrestatieByStudent(Student student) throws SQLException
    {
        return studentPrestatieImp.getPrestatieByStudent(student);
    }
    
    public List<StudentPrestatie> getPrestatieByStudentAndDeelcompetentieID(Student student, Integer deelcompID) throws SQLException
    {
        return studentPrestatieImp.getPrestatieByStudentAndDeelcompetentieID(student, deelcompID);
    }
}
