/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

import java.util.List;
import java.util.ArrayList;

public class User {
    private DBaseSQL SQL;
    private List<Observer> views;
    
    public User()
    {
        SQL = new DBaseSQL();
        views = new ArrayList<>();
    }
    
    public void addView(Observer view)
    {
        views.add(view);
    }
}
