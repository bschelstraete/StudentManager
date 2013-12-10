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
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
        aanpasButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                editObject();
            }
        });
        verwijderButton = new JButton("Verwijderen");
        
        southButtonPanel.add(toevoegButton);
        southButtonPanel.add(aanpasButton);
        southButtonPanel.add(verwijderButton);
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
        vulCompetentieLijstIn();    
        for(int i = 0; i < competentieList.size(); i++)
        {
            competentieNode = new DefaultMutableTreeNode(competentieList.get(i).getBeschrijving()); 
            topNode.add(competentieNode);
            vulDeelCompetentieTreeIn(competentieNode, competentieList.get(i).getID());
        }

    }
    
    private void vulDeelCompetentieTreeIn(DefaultMutableTreeNode competentieNode, Integer competentieID)
    {
        
        vulDeelcompetentieLijstIn(competentieID);
        for(int i = 0; i < deelcompetentieList.size(); i++)
        {
            deelcompetentieNode = new DefaultMutableTreeNode(deelcompetentieList.get(i).getBeschrijving());
            competentieNode.add(deelcompetentieNode);
            vulIndicatorTreeIn(deelcompetentieNode, deelcompetentieList.get(i).getID());
        }
    }
    
    private void vulIndicatorTreeIn(DefaultMutableTreeNode deelcompetentieNode, Integer deelcompetentieID)
    {
        vulIndicatorLijstIn(deelcompetentieID);
        for (int i = 0; i < indicatorList.size(); i++)
        {
            indicatorNode = new DefaultMutableTreeNode(indicatorList.get(i).getNaam());
            deelcompetentieNode.add(indicatorNode);
        }
    }
    
    private void vulCompetentieLijstIn()
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
    
    private void vulDeelcompetentieLijstIn(int compID)
    {
        try
        {
            deelcompetentieList = user.getDeelcompetentiesByCompetentieID(compID);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void vulIndicatorLijstIn(int deelcompID)
    {
        try
        {
            indicatorList = user.getIndicatorsByDeelcompetentieID(deelcompID);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
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
            String keuze = (String)JOptionPane.showInputDialog(this, "Kies een competentie: ", 
                                                            "Keuze", JOptionPane.PLAIN_MESSAGE, null, 
                                                            initKeuzeString("Competentie"), null);
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
        try
        {
            String keuze = (String)JOptionPane.showInputDialog(this, "Kies een deelcompetentie waar je een indicator wil aan toevoegen: ", 
                                                        "Keuze", JOptionPane.PLAIN_MESSAGE, null, 
                                                         initKeuzeString("Deelcompetentie"), null);
            String beschrijving = JOptionPane.showInputDialog(this, "Nieuwe indicator invoegen:");
            
            if(beschrijving != null)
            {
                user.voegIndicatorToe(beschrijving, keuze);
                resetTree();
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Indicatorbeschrijving mag niet leeg zijn!");
            }
            
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private String[] initKeuzeString(String keuze) throws SQLException
    {
        switch (keuze) {
            case "Competentie":
                competentieList = user.getCompetenties();
                String[] compStrings = new String[competentieList.size()];
                for(int i = 0; i < compStrings.length; i++)
                {
                    compStrings[i] = competentieList.get(i).getBeschrijving();
                }
                return compStrings;

            case "Deelcompetentie":
                deelcompetentieList = user.getDeelcompetenties();
                String[] deelcompStrings = new String[deelcompetentieList.size()];
                for(int i = 0; i < deelcompetentieList.size(); i++)
                {
                    deelcompStrings[i] = deelcompetentieList.get(i).getBeschrijving();
                }
                return deelcompStrings;         
            default:
                return null;
        }
    }
    
    private void editObject()
    {
        Object[] keuzeStrings = {"Competentie", "Deelcompetentie", "Indicator"};

        String keuze = (String)JOptionPane.showInputDialog(this, "Wat wil u aanpassen? ", "Keuze", JOptionPane.PLAIN_MESSAGE, null, keuzeStrings, null);
        if(!keuze.equals(""))
        {          
            switch(keuze)
            {
                case "Competentie":
                    editCompetentie();
                    break;
                case "Deelcompetentie":
                    
                    editDeelcompetentie();
                    break;
//                case "Indicator":
//                    editIndicator(beschrijving);
//                    break;
            }
            resetTree();
        }
    }
    
    private void editCompetentie()
    {
        try
        {
            competentieList = user.getCompetenties();
            String keuze = (String)JOptionPane.showInputDialog(this, "Welke competentie wilt u aanpassen? ", "Keuze", JOptionPane.PLAIN_MESSAGE, null, initKeuzeString("Competentie"), null);
            if(keuze != null)
            {
                String beschrijving = JOptionPane.showInputDialog(null, "Oude waarde: \n" + keuze + "\n Nieuwe waarde: \n");
                user.pasCompetentieAan(keuze, beschrijving);
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }
    
    private void editDeelcompetentie()
    {
        try
        {
            deelcompetentieList = user.getDeelcompetenties();
            String keuze = (String)JOptionPane.showInputDialog(this, "Welke deelcompetentie wilt u aanpassen? ", "Keuze", JOptionPane.PLAIN_MESSAGE, null, initKeuzeString("Deelcompetentie"), null);
            if(keuze != null)
            {
                String beschrijving = JOptionPane.showInputDialog(null, "Oude waarde: \n" + keuze + "\n Nieuwe waarde: \n");
                user.pasDeelcompetentieAan(keuze, beschrijving);
            }

        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }
    
//    private void editIndicator(String beschrijving)
//    {
//        try
//        {
//            user.pasIndicatorAan(beschrijving);
//        }
//        catch(SQLException e)
//        {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        } 
//    }
}
