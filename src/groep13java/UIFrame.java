/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

import javax.swing.JFrame;

public class UIFrame extends JFrame{
    private UICompetentieVerwijderenPanel compToevoegPanel = new UICompetentieVerwijderenPanel();
    private MenuBar menubar;
    
    public UIFrame()
    {
        setContentPane(compToevoegPanel);
        pack();
        menubar = new MenuBar();
        this.setJMenuBar(menubar);
        setVisible(true);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
