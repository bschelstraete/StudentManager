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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    }
    
    private void initTree()
    {
        this.removeAll();
        topNode = new DefaultMutableTreeNode("Partims");
        vulPartimTreeIn(topNode);
        treeModel = new DefaultTreeModel(topNode);
        partimTree = new JTree(treeModel);
        scrollPane = new JScrollPane(partimTree);
        
        initButtonPanel();
        
        this.setLayout(new BorderLayout());          
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        this.validate();
        this.repaint();
    }
    
    private void initButtonPanel()
    {
        koppelButton = new JButton("Koppel indicator met partim");
        koppelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                koppelIndicatorMetPartim();
            }
        });
        ontkoppelButton = new JButton("Ontkoppel indicator met partim");
        ontkoppelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ontkoppelIndicatorMetPartim();
            }
        });
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(koppelButton);
        buttonPanel.add(ontkoppelButton);
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
    
    
    private void koppelIndicatorMetPartim()
    {
        try
        {
            String partimKeuze = (String)JOptionPane.showInputDialog(this, "Welke partim wilt u koppelen? ", "Keuze", JOptionPane.PLAIN_MESSAGE, null, getPartimStringList(), null);
            if(partimKeuze != null)
            {
                String indicatorKeuze = (String)JOptionPane.showInputDialog(this, "Welke indicator wilt u koppelen aan " + partimKeuze, "Keuze", JOptionPane.PLAIN_MESSAGE, null, getNietGekoppeldeIndicatorStringList(), null);
                if(indicatorKeuze != null )
                {
                    user.koppelIndicatorMetPartim(user.getIndicatorByBeschrijving(indicatorKeuze).getID(), user.getPartimByBeschrijving(partimKeuze).getID());
                }
            }
            initTree();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private String[] getPartimStringList() throws SQLException
    {
        List<Partim> partimList = user.getPartims();
        String[] partimStringList = new String[partimList.size()];

        for(int i = 0; i < partimList.size(); i++)
        {
            partimStringList[i] = partimList.get(i).getNaam();
        }

        return partimStringList;
    }
    
    private String[] getNietGekoppeldeIndicatorStringList() throws SQLException
    {
        List<Indicator> indicatorList = user.getNogNietGekoppeldeIndicatoren();
        String[] indicatorStringList = new String[indicatorList.size()];
        
        for(int i = 0; i < indicatorList.size(); i++)
        {
            indicatorStringList[i] = indicatorList.get(i).getBeschrijving();
        }
        
        return indicatorStringList;
    }
    
    private String[] getGekoppeldeIndicatorStringList(Integer partimID) throws SQLException
    {
        List<Indicator> indicatorList = user.getIndicatorenIDByPartimID(partimID);
        String[] indicatorStringList = new String[indicatorList.size()];
        
        for(int i = 0; i < indicatorList.size(); i++)
        {
            indicatorStringList[i] = indicatorList.get(i).getBeschrijving();
        }
        
        return indicatorStringList;
    } 
    
    private void ontkoppelIndicatorMetPartim()
    {
        try
        {
        String partimKeuze = (String)JOptionPane.showInputDialog(this, "Van welke partim wilt u de indicator ontkoppelen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, getPartimStringList(), null);
            if(partimKeuze != null)
            {
                String indicatorKeuze = (String)JOptionPane.showInputDialog(this, "Welke indicator wilt u koppelen aan " + partimKeuze, "Keuze", JOptionPane.PLAIN_MESSAGE, null, getGekoppeldeIndicatorStringList(user.getPartimByBeschrijving(partimKeuze).getID()), null);
                if(indicatorKeuze != null )
                {
                    user.ontkoppelIndicatorMetPartimByIndicatorID(user.getIndicatorByBeschrijving(indicatorKeuze).getID());
                }
            }
            initTree();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
