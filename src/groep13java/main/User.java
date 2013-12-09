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
    
    public void voegCompetentieToe(String newCompetentie) throws SQLException
    {
        control.voegCompetentieToe(newCompetentie);
        setChanged();
        notifyObservers();
    }
    
    public void koppelDeelcompetentieAanCompetentie(String compBeschrijving, String deelcompBeschrijving) throws SQLException
    {
        control.koppelDeelcompetentieAanCompetentie(getCompetentieByBeschrijving(compBeschrijving).getID(), getDeelcompetentieByBeschrijving(deelcompBeschrijving).getID());
    }

    private Competentie getCompetentieByBeschrijving(String compBeschrijving) throws SQLException
    {
        return control.getCompetentieByBeschrijving(compBeschrijving);
    }

    private Deelcompetentie getDeelcompetentieByBeschrijving(String deelcompBeschrijving) throws SQLException
    {
        return control.getDeelcompetentieByBeschrijving(deelcompBeschrijving);
    }
}
