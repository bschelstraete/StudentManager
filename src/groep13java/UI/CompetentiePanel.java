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
import java.sql.SQLException;
import java.util.List;
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
    private JPanel eastButtonPanel;
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
        this.add(competentieListPane, BorderLayout.CENTER);
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
}
