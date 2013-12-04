/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Controller.Controller;
import groep13java.Model.Student;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author Jellyfish
 */
public class StudentListPanel extends JPanel{
    private JScrollPane scrollPane;
    private JLabel label;
    private JTable studentTable;
    private String[] columnNames = {"ID", "familienaam", "voornaam"};;
    private Object[][] studentObject;
    private List<Student> studentList;
    
    public StudentListPanel(Controller control)
    {
        scrollPane = new JScrollPane();
        this.setLayout(new BorderLayout());
        
        this.setSize(new Dimension(600,600));
        try
        {
            label = new JLabel("Lijst van studenten: ");
            
            studentList = control.getStudenten();
            studentObject = new Object[studentList.size()][3];

            for(int i = 0; i < control.getStudenten().size(); i++)
            {
                studentObject[i][0] = studentList.get(i).getID();
                studentObject[i][1] = studentList.get(i).getFamilienaam();
                studentObject[i][2] = studentList.get(i).getVoornaam();
            }
            studentTable = new JTable(studentObject, columnNames);
            
            scrollPane.add(studentTable);
            this.add(scrollPane, BorderLayout.CENTER);
        }
        catch(SQLException e)
        {
            label = new JLabel("Error: " + e.getMessage());
        }
        this.add(label, BorderLayout.NORTH);
    }
    
}
