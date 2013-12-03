/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.database.MenuBar;
import javax.swing.JFrame;

public class UIFrame extends JFrame{
    private UICompetentieVerwijderenPanel compToevoegPanel;
    private MenuBar menubar;
    
    public UIFrame()
    {   
        compToevoegPanel = new UICompetentieVerwijderenPanel();
        setContentPane(compToevoegPanel);
        pack();
        menubar = new MenuBar();
        this.setJMenuBar(menubar);
        setVisible(true);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
