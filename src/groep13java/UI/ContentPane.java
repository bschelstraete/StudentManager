/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Controller.Controller;
import javax.swing.JPanel;

/**
 *
 * @author Jellyfish
 */
public class ContentPane extends JPanel{
    private StudentListPanel studentListPane;
    
    public ContentPane(Controller control)
    {
        super();
        studentListPane = new StudentListPanel(control);
        this.setSize(studentListPane.getSize());
        this.add(studentListPane);     
    }
}
