/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Controller.Controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
/**
 *
 * @author Jellyfish
 */
public class StudentListPanel extends JPanel{
    private JScrollPane scrollPane;
    private JLabel label;
    private JList studentTable;
    
    public StudentListPanel(Controller control)
    {
        scrollPane = new JScrollPane();
        this.setLayout(new BorderLayout());
        
        this.setSize(new Dimension(600,600));
        try
        {
            label = new JLabel("Lijst van studenten: ");
            studentTable = new JList(control.getStudenten().toArray());
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
