/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.main;

import groep13java.Model.Student;
import groep13java.Observer.Observer;
import groep13java.database.DBaseSQL;
import java.util.List;
import java.util.ArrayList;

public class User {
    private DBaseSQL SQL;
    private List<Student> studentList;
    private List<Observer> views;
    
    public List<Student> getStudenten()
    {
        return studentList;
    }
    
    public User()
    {
        DBaseSQL db = new DBaseSQL();
        views = new ArrayList<>();
    }
    
    public void addView(Observer view)
    {
        views.add(view);
    }
}
