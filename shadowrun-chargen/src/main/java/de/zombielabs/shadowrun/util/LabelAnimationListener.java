/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Steps
 */
public class LabelAnimationListener implements ActionListener {
    private final Color originalColor;
    private Color highlightColor;
    private final JLabel label;
    private float factor;
    private int steps;
    private Timer timer;
    
    public boolean isFinished() {
        return factor >= 1.0f;
    }
    
    public LabelAnimationListener(Color orig, Color high, JLabel label, int steps, Timer t) {
        this.originalColor = orig;
        this.highlightColor = high;
        this.label = label;
        this.steps = steps;
        this.factor = 1.0f / (float)steps;
        this.timer = t;
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("tick");
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                label.setForeground(blend(highlightColor, originalColor, factor));
            }
            
        });
        
        factor += factor;
        
        if(factor >= 1.0f) {
            this.timer.stop();
        }
    }
    
    private Color blend(Color one, Color two, float blendFactor) {
        final float invBF = 1.0f-blendFactor;
        final int r = (int)(one.getRed() * blendFactor + two.getRed() * invBF);
        final int g = (int)(one.getGreen() * blendFactor + two.getGreen() * invBF);
        final int b = (int)(one.getBlue() * blendFactor + two.getBlue() * invBF);
        final int a = (int)(one.getAlpha() * blendFactor + two.getAlpha() * invBF);
        final Color result = new Color(r,g,b,a);
        return result;
    }
    
    
    
}
