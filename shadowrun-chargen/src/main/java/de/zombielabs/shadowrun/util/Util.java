/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.util;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

/**
 *
 * @author Steps
 */
public class Util {
    
    public static Color blend(Color one, Color two, float blendFactor) {
        final float invBF = 1.0f-blendFactor;
        final int r = (int)(one.getRed() * blendFactor + two.getRed() * invBF);
        final int g = (int)(one.getGreen() * blendFactor + two.getGreen() * invBF);
        final int b = (int)(one.getBlue() * blendFactor + two.getBlue() * invBF);
        final int a = (int)(one.getAlpha() * blendFactor + two.getAlpha() * invBF);
        final Color result = new Color(r,g,b,a);
        return result;
    }
    
    public static Color blend(Color c0, Color c1) {
        double totalAlpha = c0.getAlpha() + c1.getAlpha();
        double weight0 = c0.getAlpha() / totalAlpha;
        double weight1 = c1.getAlpha() / totalAlpha;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
        double a = Math.max(c0.getAlpha(), c1.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);
    }
    
    public static void injectChangeListener(final JSpinner spinner, ChangeListener listener) {
        final JComponent comp = spinner.getEditor();
        final JFormattedTextField field = (JFormattedTextField)comp.getComponent(0);
        final DefaultFormatter formatter = (DefaultFormatter)field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        spinner.addChangeListener(listener);
    }
    
    /*final JSpinner spinner = new JSpinner();
    JComponent comp = spinner.getEditor();
    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
    formatter.setCommitsOnValidEdit(true);
    spinner.addChangeListener(new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent e) {
            LOG.info("value changed: " + spinner.getValue());
        }
    });*/
}
