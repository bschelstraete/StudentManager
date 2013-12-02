/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

import javax.swing.JFrame;

public class UIFrame extends JFrame{
    private UIContentPanel contentPanel = new UIContentPanel();
    private MenuBar menubar;
    
    public UIFrame()
    {
        setContentPane(contentPanel);
        pack();
        menubar = new MenuBar();
        this.setJMenuBar(menubar);
        setVisible(true);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
