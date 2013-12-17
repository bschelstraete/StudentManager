/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groep13java.UI;

import groep13java.Model.Student;
import groep13java.Model.StudentPrestatie;
import groep13java.main.User;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jellyfish
 */
public class StudentOpvolgPanel extends JPanel{
    private List<StudentPrestatie> prestatieList;
    private JLabel studNaamLabel;
    private Student student;
    private User user;
    
    public StudentOpvolgPanel(Student student, User user)
    {
        this.student = student;
        this.user = user;
        studNaamLabel = new JLabel(student.getVoornaam() + " " + student.getFamilienaam());
        getPrestatiesByStudent(this.student);
    }
    
    private void getPrestatiesByStudent(Student student)
    {
        try
        {
            prestatieList = user.getPrestatieByStudent(student);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
