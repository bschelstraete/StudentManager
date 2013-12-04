/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.main.User;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private Tabs tabbedPane;
    StudentListPanel studentlistPane;
    private User user;
    
    public MainFrame()
    {   
        pack();
        user = new User();
        tabbedPane = new Tabs(user);
        this.setSize(tabbedPane.getSize());
        setVisible(true);   
        this.add(tabbedPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
