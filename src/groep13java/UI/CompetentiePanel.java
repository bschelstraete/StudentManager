/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.Observer.Observer;
import groep13java.main.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class CompetentiePanel extends JPanel implements Observer{
    private JTree competentieTree;
    private DefaultTreeModel treeModel;
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
    private User user;
    
    public CompetentiePanel(User user)
    {        
        this.user = user;
        user.addObserver(this);
            
        this.setLayout(new BorderLayout());
        
        resetTree();                 
    }
    
    private void resetTree()
    {
        this.removeAll();
        topNode = new DefaultMutableTreeNode("Competenties");    
        treeModel = new DefaultTreeModel(topNode);
        initCompetentieTree(user);
        competentieTree = new JTree(treeModel);
        competentieListPane = new JScrollPane(competentieTree);
        this.add(competentieListPane, BorderLayout.CENTER);
        
        createSouthButtonPanel();
        createEastButtonPanel();
        this.add(eastButtonPanel, BorderLayout.EAST);
        this.add(southButtonPanel, BorderLayout.SOUTH);
        
        this.validate();
        this.repaint();
    }
    
    @Override
    public void update() {
        treeModel.reload(topNode);
    }
    
    private void initCompetentieTree(User user)
    {
        vulCompetentieTreeIn(topNode, user);
    }
    
    private void vulCompetentieTreeIn(DefaultMutableTreeNode topNode, User user)
    {
        vulCompetentieLijstIn(user);    
        for(int i = 0; i < competentieList.size(); i++)
        {
            competentieNode = new DefaultMutableTreeNode(competentieList.get(i).getBeschrijving()); 
            treeModel.insertNodeInto(competentieNode, topNode, i);
            vulDeelCompetentieTreeIn(competentieNode, i, user);
        }

    }
    
    private void vulDeelCompetentieTreeIn(DefaultMutableTreeNode competentieNode, int index, User user)
    {
        
        vulDeelcompetentieLijstIn(user, index);
        for(int i = 0; i < deelcompetentieList.size(); i++)
        {
            deelcompetentieNode = new DefaultMutableTreeNode(deelcompetentieList.get(i).getBeschrijving());
            treeModel.insertNodeInto(deelcompetentieNode, competentieNode, i);
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
        toevoegButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                addNewObject();
            }
        });
        
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
    
    private void addNewObject()
    {
        Object[] keuzeStrings = {"Competentie", "Deelcompetentie", "Indicator"};

        String keuze = (String)JOptionPane.showInputDialog(this, "Kies een waarde: ", "Keuze", JOptionPane.PLAIN_MESSAGE, null, keuzeStrings, null);
        if(!keuze.equals(""))
        {
            switch(keuze)
            {
                case "Competentie":
                    String beschrijving = JOptionPane.showInputDialog(null, "Nieuwe " + keuze + " invoegen:");
                    addNewCompetentie(beschrijving);
                    break;
                case "Deelcompetentie":
                    addNewDeelcompetentie();
                    break;
                case "Indicator":
                    addNewIndicator();
                    break;
            }
        }
    }
    
    private void addNewCompetentie(String beschrijving)
    {
        try
        {
        if(beschrijving != null)
            {
                user.voegCompetentieToe(beschrijving);
                treeModel.insertNodeInto(new DefaultMutableTreeNode(beschrijving), topNode, topNode.getChildCount());
                update();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Tekstvak mag niet leeg zijn!");
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void addNewDeelcompetentie() 
    {
        try
        {    
            competentieList = user.getCompetenties();
            String[] keuzeCompetentie = new String[competentieList.size()];
            for(int i=0; i < competentieList.size();i++)
            {
                    keuzeCompetentie[i] = competentieList.get(i).getBeschrijving();
            }

            String keuze = (String)JOptionPane.showInputDialog(this, "Kies een competentie: ", 
                                                            "Keuze", JOptionPane.PLAIN_MESSAGE, null, 
                                                            keuzeCompetentie, null);

            String beschrijving = JOptionPane.showInputDialog(this, "Nieuwe deelcompetentie invoegen:");
            if(beschrijving != null)
            {
                    user.voegDeelcompetentieToe(beschrijving); 
                    koppelDeelcompetentieAanCompetentie(keuze, beschrijving);
                    resetTree();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Beschrijving van de nieuwe deelcompetentie mag niet leeg zijn!");
            }
	}
	catch(SQLException e)
	{
		JOptionPane.showMessageDialog(this, e.getMessage());
	}

	
    }

    
    private void koppelDeelcompetentieAanCompetentie(String compBeschrijving, String deelcompBeschrijving) throws SQLException
    {
		user.koppelDeelcompetentieAanCompetentie(compBeschrijving, deelcompBeschrijving);
    }
    
    private void addNewIndicator() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
