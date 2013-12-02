/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Jellyfish
 */
public class UICompetentieVerwijderenPanel extends JPanel{
    private JPanel buttonPanel;
    private JPanel listPanel;
    private JButton btnVerwijder;
    private JLabel label;
    private JScrollPane listScroller; 
    
    public UICompetentieVerwijderenPanel(User user)
    {
        this.setLayout(new BorderLayout());
        createListPanel(user);
        createButtonPanel();
        addComponents();
    }
    
    private void addComponents()
    {
        this.add(listPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void createListPanel(User user)
    {
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        label = new JLabel("Competenties verwijderen uit de database:");
        listScroller = new JScrollPane();
        listScroller.setPreferredSize(new Dimension(350, 350));
        listPanel.add(label);
        listPanel.add(listScroller);
    }
    
    private void createButtonPanel()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setPreferredSize(new Dimension(50, 20));
        btnVerwijder = new JButton("Verwijderen");
        buttonPanel.add(btnVerwijder);
    }
}
