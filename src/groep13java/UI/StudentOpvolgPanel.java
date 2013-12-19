/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groep13java.UI;

import groep13java.Model.Competentie;
import groep13java.Model.Deelcompetentie;
import groep13java.Model.Indicator;
import groep13java.Model.Opleiding;
import groep13java.Model.Student;
import groep13java.Model.StudentPrestatie;
import groep13java.main.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class StudentOpvolgPanel extends JPanel{
    private List<StudentPrestatie> prestatieList;
    private List<Competentie> competentieList;
    private List<Deelcompetentie> deelcompetentieList;
    private List<Indicator> indicatorList;
    private Object[][] indicatorScoreList;
    private Object[][] deelcompetentieScoreList;
    private Object[][] competentieScoreList;
    private Integer deelcompetentieScore;
    private float deelcompetentieTotaal;
    private float deelcompetentiePercent;
    private Integer competentieScore;
    private Integer competentieTotaal;
    private float competentiePercent;
    private JLabel studNaamLabel;
    private JLabel opleidingLabel;
    private JPanel labelPanel;
    private JButton detailButton;
    private JPanel buttonPanel;
    private Student student;
    private Opleiding opleiding;
    private User user;
    private JTable puntenTabel;
    private DefaultTableModel tableModel;
    private String[] columnNames = {"Competentie", "Progress"}; 
    private JScrollPane scrollPane;
    
    
    public StudentOpvolgPanel(Student student, User user)
    {
        this.student = student;
        this.user = user;
        this.setLayout(new BorderLayout());
        initComponents();
        updateComponents();  
        this.setPreferredSize(new Dimension(400, 250));
    }
    
    private void initComponents()
    {
        detailButton = new JButton("Details");
        detailButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getDetailOpvolging();
            }
        });
        
        setLabels(student);
        initLists();
        initTable();
        initPanels();
    }
    
    private void initPanels()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(detailButton);
        scrollPane = new JScrollPane(puntenTabel);
        labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());
    }
    
    private void updateComponents()
    {
        
        labelPanel.add(studNaamLabel);
        labelPanel.add(opleidingLabel);
        this.add(labelPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void initLists()
    {
        try
        {
            prestatieList = user.getPrestatieByStudent(student);
            competentieList = user.getCompetentieByStudent(student);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void initTable()
    {
        tableModel = new DefaultTableModel(setDataForTable(), columnNames);
        puntenTabel = new JTable(tableModel);
        puntenTabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(e.getClickCount() == 2)
                {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = 0;
                    String competentie = (String)getData(target, row, column);
                    getDetailOpvolging(competentie);
                }
            }
        });
        setTableNonEditable(puntenTabel);
        TableColumn column = puntenTabel.getColumnModel().getColumn(1);
        column.setCellRenderer(new CellProgressRenderer());
    }
    
    public Object getData(JTable table, int row_index, int col_index)
    {
        return table.getModel().getValueAt(row_index, col_index);
    } 
    
    private Object[][] setDataForTable()
    {
        Object[][] data = null;
        try
        {
            berekenCompetentieScore();
            data = competentieScoreList;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        return data;
    }
    
    private void setTableNonEditable(JTable table)
    {
        for (int c = 0; c < table.getColumnCount(); c++)
        {
            Class<?> col_class = table.getColumnClass(c);
            table.setDefaultEditor(col_class, null); 
        }
    }
    
    private void setLabels(Student student)
    {
        try
        {
            studNaamLabel = new JLabel(student.getVoornaam() + " " + student.getFamilienaam() + " -");
            opleidingLabel = new JLabel(user.getOpleidingByStudent(student).getNaam());
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    
    private void berekenCompetentieScore() throws SQLException
    {
        competentieList = user.getCompetenties();
        Integer size = competentieList.size();
        competentieScoreList = new Object[size][2];
        
        for(int i = 0; i < size; i++)
        {  
            competentieScore = 0;
            competentieTotaal = 0;
            berekenDeelcompetentieScore(competentieList.get(i).getID());
            for(int j = 0; j < deelcompetentieScoreList.length; j++)
            {
                if((Integer)deelcompetentieScoreList[j][1] == 100)
                {
                    competentieScore += 1;
                }
                competentieTotaal += 1;
            }

            
            competentieScoreList[i][0] = competentieList.get(i).getBeschrijving();
            if(competentieTotaal != 0)
            {
                competentieScoreList[i][1] = (Integer)Math.round((competentieScore*100 / competentieTotaal));
            }
            else
            {
                competentieScoreList[i][1] = 0;
            }
            
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
            deelcompetentieTotaal=0;
            berekenIndicatorScore(deelcompetentieList.get(i).getID());
            for(int j = 0; j < indicatorScoreList.length; j++)
            {
                if((Integer)indicatorScoreList[j][1] >= 10)
                {
                    deelcompetentieScore += 1;
                }
                deelcompetentieTotaal += 1;
            }
            
            deelcompetentieScoreList[i][0] = deelcompetentieList.get(i).getBeschrijving();
            deelcompetentieScoreList[i][1] = (Integer)Math.round((deelcompetentieScore / deelcompetentieTotaal)*100);
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
    
    private void getDetailOpvolging(String competentieBeschrijving)
    {
        try
        {
            Competentie competentie = user.getCompetentieByBeschrijving(competentieBeschrijving);
            DetailOpvolgPanel opvolgPanel = new DetailOpvolgPanel(competentie, user, student);
            JOptionPane.showMessageDialog(this, opvolgPanel, "Details van " + competentie.getBeschrijving() + ":", JOptionPane.PLAIN_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }  
    }
    private void getDetailOpvolging()
    {
        try
        {
            String competentieBeschrijving = (String)JOptionPane.showInputDialog(this, "Voor welke student wilt u een score toevoegen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, getCompetentieString(), null);
            Competentie competentie = user.getCompetentieByBeschrijving(competentieBeschrijving);
            DetailOpvolgPanel opvolgPanel = new DetailOpvolgPanel(competentie, user, student);
            JOptionPane.showMessageDialog(this, opvolgPanel, "Details van " + competentie.getBeschrijving() + ":", JOptionPane.PLAIN_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }  
    }
    
    private String[] getCompetentieString()
    {
        String[] competentieStringList = new String[competentieList.size()];
        for(int i = 0; i < competentieList.size(); i++)
        {
            competentieStringList[i] = competentieList.get(i).getBeschrijving();
        }
        
        return competentieStringList;
    }
}
