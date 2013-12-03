/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private ContentPane contentPane = new ContentPane();
    public MainFrame()
    {   
        pack();
        
        this.setLayout(new BorderLayout());
        this.setSize(contentPane.getSize());
        setVisible(true);   
        this.add(contentPane, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
