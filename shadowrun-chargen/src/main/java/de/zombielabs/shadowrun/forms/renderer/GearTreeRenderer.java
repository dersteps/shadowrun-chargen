/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.renderer;

import de.zombielabs.shadowrun.common.data.gear.Gear;
import de.zombielabs.shadowrun.common.data.gear.Toxin;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

/**
 *
 * @author Steps
 */
public class GearTreeRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component comp = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        JLabel label = (JLabel)comp;
        
        if(value instanceof GearNode) {
            GearNode gn = (GearNode)value;
            final Gear g = gn.getGear();
            label.setText(g.toString());
            if(g instanceof Toxin) {
                label.setIcon(new ImageIcon(Class.class.getResource("/de/zombielabs/shadowrun/icons/toxin.png")));
            }
        } else {
            if(value instanceof TreeNode) {
                TreeNode node = (TreeNode)value;
                if(!node.isLeaf()) {
                    label.setIcon(new ImageIcon(Class.class.getResource("/de/zombielabs/shadowrun/icons/folder.png")));
                }
            }
        }
        
        
        return label;
    }
    
}
