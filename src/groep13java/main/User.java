/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.main;

import groep13java.Controller.Controller;
import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.Model.Student;
import groep13java.Observer.Observer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class User extends Observable{
    private Controller control;
    private ArrayList<Observer> observerList;
    
    public User()
    {
        control = new Controller();
        observerList = new ArrayList<>();
    }
    
    public void addObserver(Observer newObserver)
    {
        observerList.add(newObserver);
    }

    public List<Student> getStudenten() throws SQLException
    {
        return control.getStudenten();
    }
    public List<Competentie> getCompetentiesByOpleidingID(Integer opleidingID) throws SQLException
    {
        return control.getCompetentiesByOpleidingID(opleidingID);
    }

    public List<Deelcompetentie> getDeelcompetentiesByCompetentieID(Integer competentieID) throws SQLException
    {
        return control.getDeelcompetentiesByCompetentieID(competentieID);
    }
    
    public List<Indicator> getIndicatorsByDeelcompetentieID(Integer deelcompetentieID) throws SQLException
    {
        return control.getIndicatorsByDeelcompetentieID(deelcompetentieID);
    }
    
    public void voegCompetentieToe(String newCompetentie) throws SQLException
    {
        control.voegCompetentieToe(newCompetentie);
        setChanged();
        notifyObservers();
    }
    
    public void koppelDeelcompetentieAanCompetentie(String compBeschrijving, String deelcompBeschrijving) throws SQLException
    {
        Competentie comp = getCompetentieByBeschrijving(compBeschrijving);
        Deelcompetentie deelcomp = getDeelcompetentieByBeschrijving(deelcompBeschrijving);
        control.koppelDeelcompetentieAanCompetentie(comp.getID(), deelcomp.getID());
    }

    private Competentie getCompetentieByBeschrijving(String compBeschrijving) throws SQLException
    {
        return control.getCompetentieByBeschrijving(compBeschrijving);
    }

    private Deelcompetentie getDeelcompetentieByBeschrijving(String deelcompBeschrijving) throws SQLException
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

//    public void pasIndicatorAan(String beschrijving) throws SQLException
//    {
//        control.pasIndicatorAan(beschrijving);
//    }
}
