/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groep13java.UI;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Jellyfish
 */
public class CellProgressRenderer extends JProgressBar implements TableCellRenderer
{

    public CellProgressRenderer()
    {
        super(0, 100);
        this.setStringPainted(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setValue((Integer)value);
        
        return this;
    }
    
}
