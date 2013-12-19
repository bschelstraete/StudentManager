/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Model.Indicator;
import groep13java.Model.Partim;
import groep13java.Model.Student;
import groep13java.main.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentListPanel extends JPanel{
    private JScrollPane scrollPane;
    private JTable studentTable;
    private String[] columnNames = {"ID", "Naam"};
    private Object[][] studentObject;
    private List<Student> studentList;
    private List<Partim> partimList;
    private JPanel buttonPanel;
    private JButton competentieButton;
    private JButton invoerenIndicator;
    private User user;
    private StudentOpvolgPanel studentOpvolgPanel;   
    
    public StudentListPanel(User user)
    {
        this.user = user;
        this.setLayout(new BorderLayout());
        studentTable = vulTabelStudentIn();
        scrollPane = new JScrollPane(studentTable);
        this.add(scrollPane, BorderLayout.CENTER);
        
        buttonPanel = createButtonPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createButtonPanel()
    {
        JPanel buffer = new JPanel();
        competentieButton = new JButton("progressie");
        competentieButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                getPrestatiesByStudent();
            }
        });
        
        invoerenIndicator = new JButton("Indicators invoeren");
        invoerenIndicator.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                vulIndicatorInVoorStudent();
            }
        });
        
        buffer.setLayout(new FlowLayout());
        buffer.add(competentieButton);
        buffer.add(invoerenIndicator);
        
        return buffer;     
    }
    
    private JTable vulTabelStudentIn()
    {
        try
        {
            studentList = user.getStudenten();
            studentObject = new Object[studentList.size()][2];

            for(int i = 0; i < user.getStudenten().size(); i++)
            {
                studentObject[i][0] = studentList.get(i).getID();
                studentObject[i][1] = studentList.get(i).getVoornaam() + " " + studentList.get(i).getFamilienaam();
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        JTable table = new JTable(studentObject, columnNames);
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(e.getClickCount() == 2)
                {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = 1;
                    String naam = (String)getData(target, row, column);
                    getPrestatiesByStudent(naam);
                }
            }
        });
        setTableNonEditable(table);
        return table;
    }
    
    public Object getData(JTable table, int row_index, int col_index)
    {
        return table.getModel().getValueAt(row_index, col_index);
    } 
    
    private void setTableNonEditable(JTable table)
    {
        for (int c = 0; c < table.getColumnCount(); c++)
        {
            Class<?> col_class = table.getColumnClass(c);
            table.setDefaultEditor(col_class, null); 
        }
    }
    
    private void vulIndicatorInVoorStudent()
    {
        try
        {
            String studentKeuze = (String)JOptionPane.showInputDialog(this, "Voor welke student wilt u een score toevoegen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, getStudentenString(), null);
            if(studentKeuze != null)
            {
                String partimKeuze = (String)JOptionPane.showInputDialog(this, "Voor welke partim wilt u een score toevoegen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, getPartimStringByStudentNaam(studentKeuze), null);
                if(partimKeuze != null)
                {
                    String indicatorKeuze = (String)JOptionPane.showInputDialog(this, "Voor welke indicator wilt u een score(0 tot 20) toevoegen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, getIndicatorStringListByPartimID(user.getPartimByBeschrijving(partimKeuze).getID()), null);
                    if(indicatorKeuze != null)
                    {
                        String score = (String)JOptionPane.showInputDialog(this, "Voor een score in voor " + indicatorKeuze);
                        try
                        {
                            Integer indicatorScore = Integer.parseInt(score);
                            if(indicatorScore > 0 && indicatorScore <= 20)
                            {
                                user.insertScoreVoorIndicatorByStudentID(indicatorScore, indicatorKeuze, studentKeuze);
                            }
                            else
                            {
                                throw new NumberFormatException();
                            }
                        }
                       catch(NumberFormatException e)
                       {
                           JOptionPane.showMessageDialog(this, "Geen geldig formaat, u moet een cijfer ingeven tussen 0 en 20!");
                       }
                    }
                }
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private String[] getStudentenString() throws SQLException
    {
        studentList = user.getStudenten();
        String[] studentStringList = new String[studentList.size()];
        for(int i = 0; i < studentList.size(); i++)
        {
            studentStringList[i] = studentList.get(i).getVoornaam() + " " + studentList.get(i).getFamilienaam();
        }
        
        return studentStringList;
    }
    
    private String[] getPartimStringByStudentNaam(String naam) throws SQLException
    {
        partimList = user.getPartimStringByStudentNaam(naam);
        String[] partimStringList = new String[partimList.size()];
        
        for(int i = 0; i < partimList.size(); i++)
        {
            partimStringList[i] = partimList.get(i).getNaam();
        }
        
        
        return partimStringList;
    }
    
    private String[] getIndicatorStringListByPartimID(Integer partID) throws SQLException
    {
        List <Indicator> indicatorList = user.getIndicatorenIDByPartimID(partID);
        String[] indicatorStringList = new String[indicatorList.size()];
        
        for(int i = 0; i < indicatorList.size(); i++)
        {
            indicatorStringList[i] = indicatorList.get(i).getBeschrijving();
        }
        
        return indicatorStringList;
    }
    
    private void getPrestatiesByStudent()
    {
        try
        {
            String naam = (String)JOptionPane.showInputDialog(this, "Voor welke student wilt u een score toevoegen?", "Keuze", JOptionPane.PLAIN_MESSAGE, null, getStudentenString(), null);
            if(naam != null)
            {
                Student student = user.getStudentByNaam(naam);
                studentOpvolgPanel = new StudentOpvolgPanel(student, user);
                JOptionPane.showMessageDialog(this, studentOpvolgPanel, "Opvolging van " + student.getVoornaam() + " " + student.getFamilienaam(), JOptionPane.PLAIN_MESSAGE);
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void getPrestatiesByStudent(String naam) 
    {
        try
        {
            Student student = user.getStudentByNaam(naam);
            studentOpvolgPanel = new StudentOpvolgPanel(student, user);
            JOptionPane.showMessageDialog(this, studentOpvolgPanel, "Opvolging van " + student.getVoornaam() + " " + student.getFamilienaam(), JOptionPane.PLAIN_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
}
