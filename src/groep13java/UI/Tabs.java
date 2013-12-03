/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import javax.swing.*;



/**
 *
 * @author Jellyfish
 */
public class Tabs extends JPanel {
    JTabbedPane tabbedPane;
    CompetentiePanel competentiePane;
    StudentListPanel studentPane;
    
    public Tabs()
    {
        tabbedPane = new JTabbedPane();
        competentiePane = new CompetentiePanel();
        tabbedPane.addTab("Competenties", competentiePane);
        tabbedPane.addTab("Studenten", studentPane);
    }    
}
