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

public class CompetentiePanel extends JPanel{
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
        verwijderButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                verwijderObject();
            }
        });
        
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
            indicatorNode = new DefaultMutableTreeNode(indicatorList.get(i).getBeschrijving());
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

        String keuze = (String)JOptionPane.showInputDialog(this, "Wat wilt u toevoegen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, keuzeStrings, null);
        if(keuze != null)
        {
            switch(keuze)
            {
                case "Competentie":
                    String beschrijving = JOptionPane.showInputDialog(this, "Nieuwe " + keuze + " invoegen:");
                    addNewCompetentie(beschrijving);
                    break;
                case "Deelcompetentie":
                    addNewDeelcompetentie();
                    break;
                case "Indicator":
                    addNewIndicator();
                    break;
                case "":
                    JOptionPane.showMessageDialog(this, "U moet een keuze maken!");
                    break;
            }
        }
        resetTree();
    }
    
    
    private void addNewCompetentie(String beschrijving)
    {
        try
        {
            if(beschrijving != null)
            {
                if(!beschrijving.equals(""))
                {
                    user.voegCompetentieToe(beschrijving);
                    treeModel.insertNodeInto(new DefaultMutableTreeNode(beschrijving), topNode, topNode.getChildCount());
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "De beschrijving mag niet leeg zijn!");
                }

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
            String beschrijving = null;
            
            if(keuze!=null)
            {
                beschrijving = JOptionPane.showInputDialog(this, "Nieuwe deelcompetentie invoegen:");
            }
            
            if(beschrijving != null)
            {
                if(!beschrijving.equals(""))
                {
                    user.voegDeelcompetentieToe(beschrijving); 
                    koppelDeelcompetentieAanCompetentie(keuze, beschrijving);
                }
                else
                {
                JOptionPane.showMessageDialog(this, "Beschrijving van de nieuwe deelcompetentie mag niet leeg zijn!");
                }
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
            String beschrijving = null;
            
            if(keuze != null)
            {
                beschrijving = JOptionPane.showInputDialog(this, "Nieuwe indicator invoegen:");
            }

            if(beschrijving != null)
            {
                if(!beschrijving.equals(""))
                {
                    user.voegIndicatorToe(beschrijving, keuze);
                    resetTree();
                }
                else 
                {
                JOptionPane.showMessageDialog(this, "Indicatorbeschrijving mag niet leeg zijn!");
                }
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
        if(keuze != null)
        {          
            switch(keuze)
            {
                case "Competentie":
                    editCompetentie();
                    break;
                case "Deelcompetentie":
                    
                    editDeelcompetentie();
                    break;
                case "Indicator":
                    editIndicator();
                    break;
                case "":
                    JOptionPane.showMessageDialog(this, "U moet een keuze maken!");
                    break;
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
                String beschrijving = JOptionPane.showInputDialog(this, "Oude waarde: \n" + keuze + "\nNieuwe waarde: \n");
                if(beschrijving != null) 
                {
                    if(!beschrijving.equals(""))
                    {
                        user.pasCompetentieAan(keuze, beschrijving);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "De beschrijving mag niet leeg zijn!");
                    }
                }
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
            String keuze = (String)JOptionPane.showInputDialog(this, "Welke deelcompetentie wilt u aanpassen? ", "Keuze", JOptionPane.PLAIN_MESSAGE, null, initKeuzeString("Deelcompetentie"), null);
            if(keuze != null)
            {
                String beschrijving = JOptionPane.showInputDialog(this, "Oude waarde: \n" + keuze + "\nNieuwe waarde: \n");
                if(beschrijving != null)
                {
                    if(!beschrijving.equals(""))
                    {
                        user.pasDeelcompetentieAan(keuze, beschrijving);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "De beschrijving mag niet leeg zijn!");
                    }
                }
            }

        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }
    
    private void editIndicator()
    {
        try
        {
            String keuze = (String)JOptionPane.showInputDialog(this, "Van welke deelcompetentie wilt u de indicator aanpassen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, initKeuzeString("Deelcompetentie"), null);
            if(keuze != null)
            {
                indicatorList = user.getIndicatorsByDeelcompetentieID(user.getDeelcompetentieByBeschrijving(keuze).getID());
                
                String[] indicatorString = new String[indicatorList.size()];
                
                for(int i = 0; i < indicatorList.size(); i++)
                {
                    indicatorString[i] = indicatorList.get(i).getBeschrijving();
                }
                String oudeBeschrijving = (String)JOptionPane.showInputDialog(this, "Welke indicator wilt u aanpassen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, indicatorString, null);
                if(oudeBeschrijving != null)
                {
                    String newBeschrijving = JOptionPane.showInputDialog(this, "Oude waarde: \n" + oudeBeschrijving + "\nNieuwe waarde: \n");
                    if(newBeschrijving != null) 
                        if(!newBeschrijving.equals(""))
                        {
                            user.pasIndicatorAan(oudeBeschrijving, newBeschrijving, keuze);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "De bschrijving mag niet leeg zijn!");
                        }            
                }
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }
    
    private void verwijderObject()
    {
        Object[] keuzeStrings = {"Competentie", "Deelcompetentie", "Indicator"};

        String keuze = (String)JOptionPane.showInputDialog(this, "Wat wilt u verwijderen? ", "Keuze", JOptionPane.PLAIN_MESSAGE, null, keuzeStrings, null);
        if(keuze != null)
        {          
            switch(keuze)
            {
                case "Competentie":
                    verwijderCompetentie();
                    break;
                case "Deelcompetentie":
                    
                    verwijderDeelcompetentie();
                    break;
                case "Indicator":
                    verwijderIndicator();
                    break;
                case "":
                    JOptionPane.showMessageDialog(this, "U moet een keuze maken!");
            }
        }
        resetTree();
       
    }
    
    private void verwijderCompetentie()
    {
        try
        {
            String keuze = (String)JOptionPane.showInputDialog(this, "Welke competentie wilt u verwijderen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, initKeuzeString("Competentie"), null);
            if(keuze != null)
            {
                int resp = JOptionPane.showConfirmDialog(this, "Indien u deze competentie verwijdert zullen alle deelcompetenties, indicatoren en scores hieraan gelinked ook verwijderd worden, wilt u hiermee doorgaan?");
                if (resp == JOptionPane.OK_OPTION) user.verwijderCompetentie(keuze);
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void verwijderDeelcompetentie()
    {
        try
        {
            String keuze = (String)JOptionPane.showInputDialog(this, "Welke deelcompetentie wilt u verwijderen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, initKeuzeString("Deelcompetentie"), null);
            if(keuze != null)
            {
                int resp = JOptionPane.showConfirmDialog(this, "Indien u deze deelcompetentie verwijdert zullen alle indicatoren en scores hieraan gelinked ook verwijderd worden, wilt u hiermee doorgaan?");
                if (resp == JOptionPane.OK_OPTION) user.verwijderCompetentie(keuze);                
                user.verwijderDeelcompetentie(keuze);
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void verwijderIndicator()
    {
        try
        {
            String keuze = (String)JOptionPane.showInputDialog(this, "Van welke deelcompetentie wilt u een indicator verwijderen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, initKeuzeString("Deelcompetentie"), null);
            if(keuze != null)
            {             
                indicatorList = user.getIndicatorsByDeelcompetentieID(user.getDeelcompetentieByBeschrijving(keuze).getID());

                String[] indicatorString = new String[indicatorList.size()];

                for(int i = 0; i < indicatorList.size(); i++)
                {
                    indicatorString[i] = indicatorList.get(i).getBeschrijving();
                }
                String indicator = (String)JOptionPane.showInputDialog(this, "Welke indicator wilt u verwijderen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, indicatorString, null);
                if(indicator != null) 
                {
                    int resp = JOptionPane.showConfirmDialog(this, "Indien u deze indicator verwijdert zullen alle scores hieraan gelinked ook verwijderd worden, wilt u hiermee doorgaan?");
                    if (resp == JOptionPane.OK_OPTION) user.verwijderIndicatorByBeschrijving(indicator);
                }
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


}
