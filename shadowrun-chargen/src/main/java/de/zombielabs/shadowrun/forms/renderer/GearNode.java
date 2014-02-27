/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.renderer;

import de.zombielabs.shadowrun.common.data.gear.Gear;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Steps
 */
public class GearNode extends DefaultMutableTreeNode {
    private Gear gear;

    public Gear getGear() {
        return gear;
    }
    
    public GearNode(Gear gear) {
        this.gear = gear;
    }
}
