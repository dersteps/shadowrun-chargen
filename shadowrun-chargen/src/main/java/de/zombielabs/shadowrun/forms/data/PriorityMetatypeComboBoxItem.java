/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.data;

import de.zombielabs.shadowrun.common.data.Metatype;
import de.zombielabs.shadowrun.common.data.Priority;

/**
 *
 * @author Steps
 */
public class PriorityMetatypeComboBoxItem extends PriorityComboBoxItem {
    private Metatype meta;
    private int specialPoints = 0;

    public Metatype getMeta() {
        return meta;
    }

    public void setMeta(Metatype meta) {
        this.meta = meta;
    }

    public int getSpecialPoints() {
        return specialPoints;
    }

    public void setSpecialPoints(int specialPoints) {
        this.specialPoints = specialPoints;
    }

    public PriorityMetatypeComboBoxItem(Priority priority, Metatype meta, int special, boolean available) {
        super(priority);
        this.meta = meta;
        this.specialPoints = special;
        this.setAvailable(available);
    }
    
    @Override
    public String toString() {
        return this.getPriority().getName() + " - " + this.meta.getName() + " ("  + this.specialPoints + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        if(!(obj instanceof PriorityMetatypeComboBoxItem)) { return false; }
        final PriorityMetatypeComboBoxItem that = (PriorityMetatypeComboBoxItem)obj;
        if(that.toString() == null && this.toString() != null) { return false; }
        if(this.toString() == null && that.toString() != null) { return false; }
        return this.toString().equals(that.toString());
    }
}
