/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.renderer;

import de.zombielabs.shadowrun.forms.data.ComboBoxItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JList;

/**
 *
 * @author Steps
 */
public class PriorityComboRenderer extends javax.swing.plaf.basic.BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(!(value instanceof ComboBoxItem)) {
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
        
        final ComboBoxItem cbo = (ComboBoxItem)value;
        
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(!cbo.isAvailable()) {
//            Font temp = c.getFont();
//            Map m = temp.getAttributes();
//            m.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
//            c.setFont(new Font(m));
            c.setBackground(Color.GRAY);
        }
        
        return c;
        
    }
    
}
