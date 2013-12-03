/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groep13java.UI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Jellyfish
 */
public class MenuBar extends JMenuBar {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    
    public MenuBar()
    {
        addMenuItems();
    }
    
    private void addMenuItems()
    {
        addFirstMenu();
        addSecondMenu();
        addThirdMenu();
        addFourthMenu();
    }
    
    private void addFirstMenu()
    {
        menu = new JMenu("Competentieprofiel");
        menuItem = new JMenuItem("Toevoegen");
        menu.add(menuItem);
        menuItem = new JMenuItem("Aanpassen");
        menu.add(menuItem);
        menuItem = new JMenuItem("Verwijderen");
        menu.add(menuItem);
        this.add(menu);
    }
    
    private void addSecondMenu()
    {
        menuItem = new JMenuItem("Koppelen");
        this.add(menuItem);
    }
    
    private void addThirdMenu()
    {
        menu = new JMenu("Progressie");
        menuItem = new JMenuItem("Invoeren");
        menu.add(menuItem);
        menuItem = new JMenuItem("Overzicht");
        menu.add(menuItem);
        this.add(menu);
    }
    
    private void addFourthMenu()
    {
        menuItem = new JMenuItem("About");
        this.add(menuItem);
    }
}
