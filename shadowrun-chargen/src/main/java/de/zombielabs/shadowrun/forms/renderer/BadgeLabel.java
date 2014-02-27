/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;

/**
 *
 * @author Steps
 */
public class BadgeLabel extends JLabel {

    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);
        
        Dimension dim = this.getSize();
        g2d.setColor(this.getBackground());
        g2d.fillRoundRect(0, 0, dim.width, dim.height, 10, 10);
              
        FontMetrics fm = g2d.getFontMetrics(this.getFont());
        final Rectangle2D rect = fm.getStringBounds(this.getText(), g);
        int l = (int)((dim.width / 2) - (rect.getWidth() / 2));
        int t = (int)((dim.height / 2) - (rect.getHeight() / 2)) + fm.getAscent();
        g2d.setColor(this.getForeground());
        g2d.drawString(this.getText(), l,t);        
    }
 
    
}
