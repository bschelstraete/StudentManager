/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Controller.Controller;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private ContentPane contentPane;
    StudentListPanel studentlistPane;
    private Controller control;
    
    public MainFrame()
    {   
        pack();
        control = new Controller();
        contentPane = new ContentPane(control);
        studentlistPane = new StudentListPanel(control);
        this.setSize(contentPane.getSize());
        setVisible(true);   
        this.add(studentlistPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
