/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import groep13java.Model.Student;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Jellyfish
 */
public class StudentOpvolgFrame extends JFrame{
    
    public StudentOpvolgFrame(Student student)
    {
        pack();
        this.setSize(new Dimension(300,300));
        setVisible(true);   
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
