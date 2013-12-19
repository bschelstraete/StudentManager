/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groep13java.UI;

import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.Model.Student;
import groep13java.Model.StudentPrestatie;
import groep13java.main.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Jellyfish
 */
public class DetailOpvolgPanel extends JPanel
{
    private User user;
    private Competentie competentie;
    private Student student;
    private JLabel compLabel;
    private JPanel labelPanel;
    private JScrollPane scrollPane;
    private JTree puntenTree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode competentieNode;
    private DefaultMutableTreeNode deelcompetentieNode;
    private DefaultMutableTreeNode indicatorNode;
    private List<StudentPrestatie> prestatieList;
    private List<Competentie> competentieList;
    private List<Deelcompetentie> deelcompetentieList;
    private List<Indicator> indicatorList;
    private Object[][] indicatorScoreList;
    private Object[][] deelcompetentieScoreList;
    private Integer deelcompetentieScore;
    private float deelcompetentieTotaal;
    private float deelcompetentiePercent;
    
    public DetailOpvolgPanel(Competentie competentie, User user, Student student)
    {
        this.user = user;
        this.competentie = competentie;
        this.student = student;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(450, 250));
        initComponents();
    }
    
    private void initComponents()
    {
        compLabel = new JLabel(competentie.getBeschrijving());
        initTree();
        initPanels();
        updateComponents();
    }
        
    private void initTree()
    {
        try
        {
            competentieNode = new DefaultMutableTreeNode(competentie.getBeschrijving());
            vulDeelcompetentieTreeIn(competentieNode, competentie.getID());
            treeModel = new DefaultTreeModel(competentieNode);
            puntenTree = new JTree(treeModel);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
    
    private void initPanels()
    {
        labelPanel = new JPanel();
        labelPanel.add(compLabel);
        scrollPane = new JScrollPane(puntenTree);
    }
    
    private void updateComponents()
    {
        this.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void vulDeelcompetentieTreeIn(DefaultMutableTreeNode competentieNode, Integer competentieID) throws SQLException
    {
        berekenDeelcompetentieScore(competentie.getID());
        for(int i = 0; i < deelcompetentieList.size(); i++)
        {
            deelcompetentieNode = new DefaultMutableTreeNode(deelcompetentieScoreList[i][0] + ": " + deelcompetentieScoreList[i][1] + "%");
            vulIndicatorTreeIn(deelcompetentieNode, deelcompetentieList.get(i).getID());
            competentieNode.add(deelcompetentieNode);
        }
    }
    
    private void vulIndicatorTreeIn(DefaultMutableTreeNode deelcompetentieNode, Integer deelcompetentieID) throws SQLException
    {
        berekenIndicatorScore(deelcompetentieID);
        for (int j = 0; j < indicatorList.size(); j++)
        {
            
            indicatorNode = new DefaultMutableTreeNode(indicatorScoreList[j][0] + ": " + indicatorScoreList[j][1]);
            deelcompetentieNode.add(indicatorNode);
        }
    }
    private void berekenDeelcompetentieScore(Integer competentieID) throws SQLException
    {
        deelcompetentieList = user.getDeelcompetentiesByCompetentieID(competentieID);
        Integer size = deelcompetentieList.size();
        deelcompetentieScoreList = new Object[size][2];
        
        
        for(int i = 0; i < size; i++)
        {   
            deelcompetentieScore = 0;
            berekenIndicatorScore(deelcompetentieList.get(i).getID());
            for(int j = 0; j < indicatorScoreList.length; j++)
            {
                if((Integer)indicatorScoreList[j][1] >= 10)
                {
                    deelcompetentieScore += 1;
                }
            }
            
            deelcompetentieScoreList[i][0] = deelcompetentieList.get(i).getBeschrijving();
            deelcompetentieScoreList[i][1] = (Integer)Math.round((deelcompetentieScore / size)*100);
        }
    }
    
    private void berekenIndicatorScore(Integer deelcompID) throws SQLException
    {       
        indicatorList = user.getIndicatorsByDeelcompetentieID(deelcompID);
        Integer size = indicatorList.size();
        indicatorScoreList = new Object[size][2];
        
        for(int i = 0; i < size; i++)
        {
            indicatorScoreList[i][0] = indicatorList.get(i).getBeschrijving();   
            indicatorScoreList[i][1] = user.getScoreByIndicatorAndStudent(indicatorList.get(i), student);
        }  
    }
    
    
    private void stringdis()
    {
        for(int i = 0; i < indicatorScoreList.length; i++)
        {
            System.out.println(indicatorScoreList[i][0]);
        }
    }
}
