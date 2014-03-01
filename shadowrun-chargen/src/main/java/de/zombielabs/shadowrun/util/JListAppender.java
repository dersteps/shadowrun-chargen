/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.util;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 *
 * @author Steps
 */
public class JListAppender extends AppenderSkeleton {

    private final String defaultlayout = "[%d{ISO8601}] - [%m]";

    private JList list;

    public JListAppender() {
        super();
        this.setLayout(new PatternLayout(this.defaultlayout));
    }
    
    public JList getList() {
        return list;
    }

    public void setList(JList list) {
        this.list = list;
        this.list.setModel(new DefaultListModel());
        this.list.setCellRenderer(new ListLogRenderer());
    }

    
    
    
    @Override
    protected void append(LoggingEvent event) {
        if(this.list != null) {
            DefaultListModel model = (DefaultListModel)this.list.getModel();
            model.addElement(new LayoutComposite(event, this.getLayout()));
            this.list.setModel(model);
        }
    }

    public void close() {
        // Nothing to do here
    }

    public boolean requiresLayout() {

        return true;
    }
    
    public class LayoutComposite {
        private LoggingEvent event;
        private Layout layout;

        public LoggingEvent getEvent() {
            return event;
        }

        public void setEvent(LoggingEvent event) {
            this.event = event;
        }

        public Layout getLayout() {
            return layout;
        }

        public void setLayout(Layout layout) {
            this.layout = layout;
        }
        
        
        
        public LayoutComposite(LoggingEvent event, Layout layout) {
            this.event = event;
            this.layout = layout;
        }
        
        @Override
        public String toString() {
            return this.layout.format(event);
        }
    }
    
}
