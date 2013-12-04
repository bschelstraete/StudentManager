/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.main.User;
import javax.swing.*;



/**
 *
 * @author Jellyfish
 */
public class Tabs extends JTabbedPane {
    CompetentiePanel competentiePane;
    StudentListPanel studentPane;
    
    public Tabs(User user)
    {
        studentPane = new StudentListPanel(user);
        competentiePane = new CompetentiePanel(user);
        this.add(studentPane, "Studenten");
        this.add(competentiePane, "Competenties");
        this.setSize(750, 350);
        this.setVisible(true);
    }    
}
