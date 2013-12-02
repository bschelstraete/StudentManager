/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

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
        studentList = db.getStudenten();
    }
    
    public void addView(Observer view)
    {
        views.add(view);
    }
}
