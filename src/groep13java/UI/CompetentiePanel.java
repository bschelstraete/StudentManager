/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.main.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class CompetentiePanel extends JPanel{
    private JTree competentieTree;
    private DefaultMutableTreeNode topNode;
    private DefaultMutableTreeNode competentieNode;
    private DefaultMutableTreeNode deelcompetentieNode;
    private DefaultMutableTreeNode indicatorNode;
    private JPanel southButtonPanel;
    private JButton toevoegButton;
    private JButton aanpasButton;
    private JButton verwijderButton;
    private JPanel eastButtonPanel;
    private JButton koppelCompetentieButton;
    private JButton koppelIndicatorButton;
    private JScrollPane competentieListPane;
    private List<Competentie> competentieList;
    private List<Deelcompetentie> deelcompetentieList;
    private List<Indicator> indicatorList;

    
    public CompetentiePanel(User user)
    {        
        initCompetentieTree(user);
        this.setLayout(new BorderLayout());
        competentieTree = new JTree(topNode);
        competentieListPane = new JScrollPane(competentieTree);
        
        createSouthButtonPanel();
        createEastButtonPanel();
        
        this.add(eastButtonPanel, BorderLayout.EAST);
        this.add(competentieListPane, BorderLayout.CENTER);
        this.add(southButtonPanel, BorderLayout.SOUTH);        
    }
    
    private void initCompetentieTree(User user)
    {
        topNode = new DefaultMutableTreeNode("Competenties");
        vulCompetentieTreeIn(topNode, user);
    }
    
    private void vulCompetentieTreeIn(DefaultMutableTreeNode topNode, User user)
    {
        vulCompetentieLijstIn(user);    
        for(int i = 0; i < competentieList.size(); i++)
        {
            competentieNode = new DefaultMutableTreeNode(competentieList.get(i).getBeschrijving());
            vulDeelCompetentieTreeIn(competentieNode, i, user);
            topNode.add(competentieNode);
        }

    }
    
    private void vulDeelCompetentieTreeIn(DefaultMutableTreeNode competentieNode, int index, User user)
    {
        
        vulDeelcompetentieLijstIn(user, index);
        for(int i = 0; i < deelcompetentieList.size(); i++)
        {
            deelcompetentieNode = new DefaultMutableTreeNode(deelcompetentieList.get(i).getBeschrijving());
            competentieNode.add(deelcompetentieNode);
        }
    }
    
    private void vulIndicatorTreeIn(DefaultMutableTreeNode deelcompetentieNode)
    {
    
    }
    
    private void vulCompetentieLijstIn(User user)
    {
        try
        {
            competentieList = user.getCompetenties();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void vulDeelcompetentieLijstIn(User user, int compID)
    {
        try
        {
            deelcompetentieList = user.getDeelcompetentiesByCompetentieID(compID + 1);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void vulIndicatorLijstIn(User user, int deelcompID)
    {
        try
        {
            indicatorList = user.getIndicatorsByDeelcompetentieID(deelcompID + 1);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void createSouthButtonPanel()
    {
        southButtonPanel = new JPanel();
        southButtonPanel.setLayout(new FlowLayout());
        
        toevoegButton = new JButton("Toevoegen");
        aanpasButton = new JButton("Aanpassen");
        verwijderButton = new JButton("Verwijderen");
        
        southButtonPanel.add(toevoegButton);
        southButtonPanel.add(aanpasButton);
        southButtonPanel.add(verwijderButton);
    }
    
    private void createEastButtonPanel()
    {
        eastButtonPanel = new JPanel();
        eastButtonPanel.setLayout(new BoxLayout(eastButtonPanel, BoxLayout.Y_AXIS));
        
        
        koppelCompetentieButton = new JButton("Koppel deelcompetentie aan competentie");
        koppelCompetentieButton.setSize(30, 30);
        koppelIndicatorButton = new JButton("Koppel indicator aan partim");
        koppelIndicatorButton.setSize(30,30);
        
        eastButtonPanel.add(koppelCompetentieButton);
        eastButtonPanel.add(Box.createRigidArea(new Dimension(20,20)));
        eastButtonPanel.add(koppelIndicatorButton);
    }
}
