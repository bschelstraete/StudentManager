/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Model.Indicator;
import groep13java.Model.Partim;
import groep13java.main.User;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Jellyfish
 */
public class IndicatorPanel extends JPanel{
    private JButton koppelIndicatorButton;
    private JPanel southButtonPanel;
    private JTree indicatorTree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode topNode;
    private DefaultMutableTreeNode partimNode;
    private DefaultMutableTreeNode indicatorNode;
    
    private List<Indicator> indicatorList;
    private List<Partim> partimList;
    
    public IndicatorPanel(User user)
    {
        
    }
}
