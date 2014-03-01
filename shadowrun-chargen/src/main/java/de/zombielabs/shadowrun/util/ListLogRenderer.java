package de.zombielabs.shadowrun.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 *
 * @author Steps
 */
public class ListLogRenderer  extends JLabel implements ListCellRenderer  {

    private DefaultListCellRenderer basicRenderer = new DefaultListCellRenderer();
    private Component base = null;
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        if(!(value instanceof JListAppender.LayoutComposite)) {
            this.setText(value.toString());
            return this;
        }
        
        JListAppender.LayoutComposite e = (JListAppender.LayoutComposite)value;
        
        this.base = this.basicRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        this.setText(e.toString());
        this.setIcon(this.getIcon(e.getEvent()));
        
        this.setForeground(this.getFore(e.getEvent(),isSelected));
        this.setBackground(this.base.getBackground());
        this.setOpaque(true);
        
        return this;
        
    }
    
    private Color getFore(LoggingEvent event, boolean selected) {
        
        if(selected) {
            return this.base.getForeground();
        }
        
        final Level lvl = event.getLevel();
        
        switch(lvl.toInt()) {
            case Level.DEBUG_INT: {
                return Util.blend(this.base.getForeground(), this.base.getBackground(), 0.5f);
            } case Level.ERROR_INT: {
                return Util.blend(this.base.getForeground(), Color.red, 0.25f);
            } case Level.FATAL_INT: {
                return Color.red;
            } case Level.INFO_INT: {
                return this.base.getForeground();
            } case Level.TRACE_INT: {
                return Util.blend(this.base.getForeground(), this.base.getBackground(), 0.5f);
            } case Level.WARN_INT: {
                return new Color(0xff, 0xbf, 0x00, 0xff);
            }
            default: {
                return this.base.getForeground();
            }
        }
    }
    
    private Icon getIcon(LoggingEvent event) {
        final Level lvl = event.getLevel();
        switch(lvl.toInt()) {
            case Level.DEBUG_INT: {
                return new ImageIcon(Class.class.getResource("/de/zombielabs/shadowrun/icons/information.png"));
            } case Level.ERROR_INT: {
                return new ImageIcon(Class.class.getResource("/de/zombielabs/shadowrun/icons/error.png"));
            } case Level.FATAL_INT: {
                return new ImageIcon(Class.class.getResource("/de/zombielabs/shadowrun/icons/error.png"));
            } case Level.INFO_INT: {
                return new ImageIcon(Class.class.getResource("/de/zombielabs/shadowrun/icons/information.png"));
            } case Level.TRACE_INT: {
                return new ImageIcon(Class.class.getResource("/de/zombielabs/shadowrun/icons/information.png"));
            } case Level.WARN_INT: {
                return new ImageIcon(Class.class.getResource("/de/zombielabs/shadowrun/icons/warn.png"));
            }
            default: {
                return null;
            }
        }
    }
    
}
