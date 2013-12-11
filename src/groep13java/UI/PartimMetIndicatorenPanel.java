/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Model.Indicator;
import groep13java.Model.Partim;
import groep13java.main.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.*;

/**
 *
 * @author Jellyfish
 */
public class PartimMetIndicatorenPanel extends JPanel{
    private JButton koppelButton;
    private JButton ontkoppelButton;
    private JTree partimTree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode topNode;
    private DefaultMutableTreeNode partimNode;
    private DefaultMutableTreeNode indicatorNode;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private User user;
    
    public PartimMetIndicatorenPanel(User user)
    {
        this.user = user;
        initTree();
        koppelButton = new JButton("Koppel partim met indicator");
        ontkoppelButton = new JButton("Ontkoppel partim met indicator");
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(koppelButton);
        buttonPanel.add(ontkoppelButton);
        
        scrollPane = new JScrollPane(partimTree);

        
        this.setLayout(new BorderLayout());          
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void initTree()
    {
        topNode = new DefaultMutableTreeNode("Partims");
        vulPartimTreeIn(topNode);
        treeModel = new DefaultTreeModel(topNode);
        partimTree = new JTree(treeModel);
    }
    
    private void vulPartimTreeIn(DefaultMutableTreeNode topNode)
    {
        try
        {
            List<Partim> partimList = user.getPartims();
            for(int i = 0; i < partimList.size(); i++)
            {
                partimNode = new DefaultMutableTreeNode(partimList.get(i).getNaam());
                topNode.add(partimNode);
                vulIndicatorNodeIn(partimNode, partimList.get(i).getID());
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }   
    }
    
    private void vulIndicatorNodeIn(DefaultMutableTreeNode partimNode, Integer partimID) throws SQLException{
        List<Indicator> indicatorList = getIndicatorenByPartimID(partimID);
        for(int i = 0; i < indicatorList.size(); i++)
        {
            indicatorNode = new DefaultMutableTreeNode(indicatorList.get(i).getBeschrijving());
            partimNode.add(indicatorNode);           
        }

    }
    
    private List<Indicator> getIndicatorenByPartimID(Integer partimID) throws SQLException
    {
        return user.getIndicatorenIDByPartimID(partimID);
    }


}
