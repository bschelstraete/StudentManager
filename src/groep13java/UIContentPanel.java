/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class UIContentPanel extends JPanel{
    
    public UIContentPanel()
    {
        super();
        setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(500, 500));
    }
}
