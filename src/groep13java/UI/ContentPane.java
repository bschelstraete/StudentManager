/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Jellyfish
 */
public class ContentPane extends JPanel{
    private Tabs tabbedPane = new Tabs();
    public ContentPane()
    {
        super();
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(600,600));
        this.add(tabbedPane);
               
    }
}
