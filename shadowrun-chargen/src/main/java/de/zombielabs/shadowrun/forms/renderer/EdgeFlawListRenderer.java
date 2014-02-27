/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.renderer;

import de.zombielabs.shadowrun.common.data.Flaw;
import de.zombielabs.shadowrun.common.data.Perk;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Steps
 */
public class EdgeFlawListRenderer extends JPanel implements ListCellRenderer {

    DefaultListCellRenderer baseRenderer = new DefaultListCellRenderer();
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component comp = this.baseRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        
        this.removeAll();
        
        
        if(value instanceof Category) {
            this.setLayout(new BorderLayout(0, 0));
            this.setPreferredSize(new Dimension((int)this.getSize().getWidth(), 24));
            this.setBorder(new EmptyBorder(5, 5, 2, 5));
            
            JLabel lbl = new JLabel(value.toString());
            Font f = new Font(lbl.getFont().getFontName(), Font.BOLD, lbl.getFont().getSize());
            lbl.setFont(f);
            this.add(lbl, BorderLayout.NORTH);
            lbl.setSize(this.getWidth(), 50);
            return this;
        } 
        /*container.add(firstComponent);
Dimension minSize = new Dimension(5, 100);
Dimension prefSize = new Dimension(5, 100);
Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
container.add(new Box.Filler(minSize, prefSize, maxSize));
container.add(secondComponent);*/
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        JLabel lblName = (JLabel)comp;
        this.setPreferredSize(new Dimension((int)this.getSize().getWidth(), 20));
        lblName.setPreferredSize(new Dimension(400, 50));
        this.setBorder(new EmptyBorder(0, 15, 0, 0));
        this.add(lblName);
        
        Dimension minSize = new Dimension(5, 100);
        Dimension prefSize = new Dimension(5, 100);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
        this.add(new Box.Filler(minSize, prefSize, maxSize));
        BadgeLabel karma = new BadgeLabel();
        karma.setHorizontalAlignment(SwingConstants.CENTER);
        karma.setVerticalAlignment(SwingConstants.CENTER);
        if(value instanceof Flaw) {
            karma.setForeground(new Color(0xc0, 0x00, 0x00, 0xff));
            final Flaw f = (Flaw)value;
            lblName.setText(f.getName());
            karma.setText("+" + f.getCost());
        } else if(value instanceof Perk) {
            karma.setForeground(new Color(0x00, 0xc0, 0x00, 0xff));
            final Perk f = (Perk)value;
            lblName.setText(f.getName());
            karma.setText("-" + f.getCost());
        }
        
        karma.setPreferredSize(new Dimension(30, this.getHeight()));
        this.add(karma);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        
        lblName.setVisible(true);
        this.setVisible(true);
        this.setOpaque(false);
        return this;
        
    }
    
}
