/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Model.Student;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Jellyfish
 */
public class StudentListPanel extends JPanel{
    private JScrollPane scrollPane;
    
    public StudentListPanel(List<Student> studentList)
    {
        scrollPane = new JScrollPane();
        JTable studentTable = putStudentsInTable(studentList);
        this.setPreferredSize(new Dimension(600,600));
        scrollPane.add(studentTable);
        this.add(scrollPane);
    }
    
    private JTable putStudentsInTable(List<Student> studentList)
    {
        String[] columnNames = {"ID", "Familienaam", "Voornaam"};
        Object[][] data = null;
        
        for(int i = 0; i < studentList.size(); i++)
        {
            for(int y = 0; y < 3; y++)
            {
                if(y == 0)
                {
                    data[i][y] = studentList.get(i).getID();
                }
                else if(y == 1)
                {
                    data[i][y] = studentList.get(i).getFamilienaam();
                }
                else
                {
                    data[i][y] = studentList.get(i).getVoornaam();
                }
            }
        }
        
        return new JTable(data, columnNames);
    }
}
