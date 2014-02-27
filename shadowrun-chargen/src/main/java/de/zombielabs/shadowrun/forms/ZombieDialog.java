/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms;

import de.zombielabs.shadowrun.common.data.DataProvider;
import java.awt.Frame;
import javax.swing.JDialog;

/**
 *
 * @author Steps
 */
public class ZombieDialog extends JDialog {
    
    private DialogResult result = DialogResult.UNKNOWN;
    
    protected DataProvider data;
    
    public DialogResult getResult() {
        return this.result;
    }
    
    public void setResult(DialogResult result) {
        this.result = result;
    }
    
    public ZombieDialog(Frame parent, boolean modal, DataProvider data) {
        super(parent, modal);
        this.data = data;
    }
    
    public void takeControl() {
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }
    
    protected void doClose(DialogResult result) {
        this.result = result;
        this.setVisible(false);
    }
}
