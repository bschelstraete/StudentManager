/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.main;

import groep13java.Controller.Controller;
import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.Model.Opleiding;
import groep13java.Model.Partim;
import groep13java.Model.Student;
import groep13java.Model.StudentPrestatie;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class User{
    private Controller control;
    
    public User()
    {
        control = new Controller();
    }

    public List<Student> getStudenten() throws SQLException
    {
        return control.getStudenten();
    }
    public List<Competentie> getCompetenties() throws SQLException
    {
        return control.getCompetenties();
    }

    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException
    {
        return control.getDeelcompetentiesByCompetentieID(competentieID);
    }
    
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompetentieID) throws SQLException
    {
        return control.getIndicatorsByDeelcompetentieID(deelcompetentieID);
    }
    
    public List<Partim> getPartims() throws SQLException
    {
        return control.getPartims();
    }
    
    public Indicator getIndicatorByID(Integer indicatorID) throws SQLException
    {
        return control.getIndicatorByID(indicatorID);
    }
    
    public void voegCompetentieToe(String newCompetentie) throws SQLException
    {
        control.voegCompetentieToe(newCompetentie);
    }
    
    public void koppelDeelcompetentieAanCompetentie(String compBeschrijving, String deelcompBeschrijving) throws SQLException
    {
        Competentie comp = getCompetentieByBeschrijving(compBeschrijving);
        Deelcompetentie deelcomp = getDeelcompetentieByBeschrijving(deelcompBeschrijving);
        control.koppelDeelcompetentieAanCompetentie(comp.getID(), deelcomp.getID());
    }

    public Competentie getCompetentieByBeschrijving(String compBeschrijving) throws SQLException
    {
        return control.getCompetentieByBeschrijving(compBeschrijving);
    }

    public Deelcompetentie getDeelcompetentieByBeschrijving(String deelcompBeschrijving) throws SQLException
    {
        return control.getDeelcompetentieByBeschrijving(deelcompBeschrijving);
    }

    public void voegDeelcompetentieToe(String beschrijving) throws SQLException
    {
        control.voegDeelcompetentieToe(beschrijving);
    }
    
    public void voegIndicatorToe(String beschrijving, String deelcompetentieBeschrijving) throws SQLException
    {
        Deelcompetentie deelcomp = getDeelcompetentieByBeschrijving(deelcompetentieBeschrijving);
        control.voegIndicatorToe(beschrijving, deelcomp.getID());
    }

    public List<Deelcompetentie> getDeelcompetenties() throws SQLException
    {
        return control.getDeelcompetenties();
    }

    public void pasCompetentieAan(String oudeBeschrijving, String newBeschrijving) throws SQLException
    {
       Competentie comp = new Competentie(getCompetentieByBeschrijving(oudeBeschrijving).getID(), newBeschrijving);
       control.pasCompetentieAan(comp);
    }

    public void pasDeelcompetentieAan(String oudeBeschrijving, String newBeschrijving) throws SQLException
    {
        Deelcompetentie deelcomp = new Deelcompetentie(getDeelcompetentieByBeschrijving(oudeBeschrijving).getID(), newBeschrijving);
        control.pasDeelcompetentieAan(deelcomp);
    }
    
    
    public void pasIndicatorAan(String oudeBeschrijving, String newBeschrijving, String deelcompetentieBeschrijving) throws SQLException
    {
        Deelcompetentie deelcomp = getDeelcompetentieByBeschrijving(deelcompetentieBeschrijving);
        Indicator oudeIndicator = getIndicatorsByBeschrijvingAndDeelcompetentieID(oudeBeschrijving, deelcomp.getID());
        Indicator indicator = new Indicator(oudeIndicator.getID(), newBeschrijving, oudeIndicator.getDeelcompID());
        control.pasIndicatorAan(indicator);
    }

    private Indicator getIndicatorsByBeschrijvingAndDeelcompetentieID(String beschrijving, Integer deelcompetentieID) throws SQLException
    {
       return control.getIndicatorByBeschrijvingAndDeelcompetentieID(beschrijving, deelcompetentieID);
    }

    public void verwijderCompetentie(String beschrijving) throws SQLException
    {
        Competentie comp = getCompetentieByBeschrijving(beschrijving);
        List<Deelcompetentie> deelcompetentieList = getDeelcompetentiesByCompetentieID(comp.getID());
        ontkoppelCompetentieMetDeelcompetenties(comp);
        for (int i = 0; i < deelcompetentieList.size(); i++)
        {
            verwijderIndicatorByDeelcompetentieID(deelcompetentieList.get(i));
            control.verwijderDeelcompetentie(deelcompetentieList.get(i));
        }
        
        control.verwijderCompetentie(comp);
    }
    
    public void verwijderDeelcompetentie(String beschrijving) throws SQLException
    {
        Deelcompetentie deelcomp = getDeelcompetentieByBeschrijving(beschrijving);
        List<Indicator> indicatorList = getIndicatorsByDeelcompetentieID(deelcomp.getID());
        for(int i = 0; i < indicatorList.size(); i++)
        {
            verwijderIndicator(indicatorList.get(i));
        }
        control.ontKoppelDeelcompetentieMetCompetentie(deelcomp);
        control.verwijderDeelcompetentie(deelcomp);
    }
    
    private void ontkoppelCompetentieMetDeelcompetenties(Competentie competentie) throws SQLException
    {
        control.ontKoppelCompetentieMetDeelcompetentie(competentie);
    }
    
    private void ontkoppelDeelcompetentieMetCompetentie(Deelcompetentie deelcompetentie) throws SQLException
    {
        control.ontKoppelDeelcompetentieMetCompetentie(deelcompetentie);
    }
    
    private void verwijderIndicator(Indicator indicator) throws SQLException
    {
        control.verwijderIndicator(indicator);
    }
    
    public void verwijderIndicatorByBeschrijving(String beschrijving) throws SQLException
    {
        Indicator indicator = getIndicatorByBeschrijving(beschrijving);
        control.verwijderIndicator(indicator);
    }
    
    public Indicator getIndicatorByBeschrijving(String beschrijving) throws SQLException
    {
        return control.getIndicatorByBeschrijving(beschrijving);
    }
    
    public void verwijderIndicatorByDeelcompetentieID(Deelcompetentie deelcompetentie) throws SQLException
    {
        control.verwijderIndicatorByDeelcompetentieID(deelcompetentie);
    }
    
    public List<Indicator> getIndicatorenIDByPartimID(Integer partimID) throws SQLException
    {
        List<Integer> indicatorenIDList = control.getIndicatorenIDByPartimID(partimID);
        List<Indicator> indicatorList = new ArrayList<>();
        
        for(int i = 0; i < indicatorenIDList.size(); i++)
        {
            indicatorList.add(getIndicatorByID(indicatorenIDList.get(i)));
        }
        
        return indicatorList;
    }
    
    public Integer getPartimIDByIndicatorID(Integer indicatorID) throws SQLException
    {
        return control.getPartimIDByIndicatorID(indicatorID);
    }
    
    public void koppelIndicatorMetPartim(Integer indicatorID, Integer partimID) throws SQLException
    {
        control.koppelIndicatorMetPartim(indicatorID, partimID);
    }
    
    public void ontkoppelIndicatorMetPartimByIndicatorID(Integer indicatorID) throws SQLException
    {
        control.ontkoppelIndicatorMetPartimByIndicatorID(indicatorID);
    }
    
    public void ontkoppelPartimMetIndicatorenByPartimID(Integer partimID) throws SQLException
    {
        control.ontkoppelPartimMetIndicatorenByPartimID(partimID);
    }
    
    private List<Indicator> getIndicatoren() throws SQLException
    {
        return control.getIndicatoren();
    }
    
    public Partim getPartimByBeschrijving(String beschrijving) throws SQLException
    {
        return control.getPartimByBeschrijving(beschrijving);
    }
    
    public List<Indicator> getNogNietGekoppeldeIndicatoren() throws SQLException
    {
        List<Indicator> nietGekoppeldeIndicatorList = new ArrayList<>();
        List<Indicator> alleIndicatoren = getIndicatoren();
        List<Indicator> gekoppeldeIndicatorList = new ArrayList<>();
        List<Indicator> indicatorByPartimList;
        List<Partim> partimList = getPartims();
        boolean checkKoppel;
        
        for(int i = 0; i < partimList.size(); i++)
        {
            indicatorByPartimList = getIndicatorenIDByPartimID(i);
            for(int j = 0; j < indicatorByPartimList.size(); j++)
            {
                gekoppeldeIndicatorList.add(indicatorByPartimList.get(j));
                
            }
        }
        
        for(int k = 0; k < alleIndicatoren.size(); k++)
        {
           checkKoppel = false;
           for(int l = 0; l < gekoppeldeIndicatorList.size(); l++)
           {
               if(alleIndicatoren.get(k).getBeschrijving().equals(gekoppeldeIndicatorList.get(l).getBeschrijving()))
               {
                   checkKoppel = true;
               }
           }
           
           if(!checkKoppel)
           {
               nietGekoppeldeIndicatorList.add(alleIndicatoren.get(k));
           }
        }
        
        return nietGekoppeldeIndicatorList;
    }

    public List<Partim> getPartimStringByStudentNaam(String naam) throws SQLException
    {
        Student student = getStudentByNaam(naam);
        return control.getPartimsByStudentID(student.getID());
    }

    public Student getStudentByNaam(String naam) throws SQLException
    {
        String[] naamArray = naam.split(" ", 2);
        Student student = control.getStudentByVoornaamEnFamilienaam(naamArray[0], naamArray[1]);
        return student;
    }

    public void insertScoreVoorIndicatorByStudentID(Integer indicatorScore, String indicatorKeuze, String studentKeuze) throws SQLException
    {
        Student student = getStudentByNaam(studentKeuze);
        Indicator indicator = getIndicatorByBeschrijving(indicatorKeuze);
        control.insertScoreVoorIndicatorByStudentID(indicatorScore, indicator.getID(), student.getID());
    }
    
    public List<StudentPrestatie> getPrestatieByStudent(Student student) throws SQLException
    {
        return control.getPrestatieByStudent(student);
    }
    
    public Opleiding getOpleidingByStudent(Student student) throws SQLException
    {
        return control.getOpleidingByStudent(student);
    }
    
    public List<Competentie> getCompetentieByStudent(Student student) throws SQLException
    {
        return control.getCompetentieByStudent(student);
    }
    
    public List<Deelcompetentie> getDeelcompetentieByStudentAndCompetentieID(Student student, Integer competentieID) throws SQLException
    {
        return control.getDeelcompetentieByStudentAndCompetentieID(student, competentieID);
    }
    
    public Integer getScoreByIndicatorAndStudent(Indicator indicator, Student student) throws SQLException
    {
        return control.getScoreByIndicatorAndStudent(indicator, student);
    }
}
