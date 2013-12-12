/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Model.Partim;
import groep13java.Model.Student;
import groep13java.main.User;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author Jellyfish
 */
public class StudentListPanel extends JPanel{
    private JScrollPane scrollPane;
    private JTable studentTable;
    private String[] columnNames = {"ID", "familienaam", "voornaam"};;
    private Object[][] studentObject;
    private List<Student> studentList;
    private JPanel buttonPanel;
    private JButton competentieButton;
    private JButton invoerenIndicator;
    private User user;
    
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
        invoerenIndicator = new JButton("Indicators invoeren");
        
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
            studentObject = new Object[studentList.size()][3];

            for(int i = 0; i < user.getStudenten().size(); i++)
            {
                studentObject[i][0] = studentList.get(i).getID();
                studentObject[i][1] = studentList.get(i).getFamilienaam();
                studentObject[i][2] = studentList.get(i).getVoornaam();
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return new JTable(studentObject, columnNames);
    }
    
    private void koppelIndicatorMetPartim()
    {
        try
        {
            String partimKeuze = (String)JOptionPane.showInputDialog(this, "Welke partim wilt u koppelen? ", "Keuze", JOptionPane.PLAIN_MESSAGE, null, getPartimStringList(), null);
            String indicatorKeuze = (String)JOptionPane.showInputDialog();
            
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
    
    private String[] getIndicatorStringList() throws SQLException
    {
        List<Indicator> indicatorList = user.
    }
}
