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
public class PriorityAttributeComboBoxItem extends ComboBoxItem  {
    private Priority priority;
    private int attributePoints = 0;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getAttributePoints() {
        return attributePoints;
    }

    public void setAttributePoints(int attributePoints) {
        this.attributePoints = attributePoints;
    }

    public PriorityAttributeComboBoxItem(Priority prio) {
        this.priority = prio;
        this.attributePoints = prio.getAttributes();
    }
    
    @Override
    public String toString() {
        return this.priority.getName() + " - " + this.attributePoints + "  Attribute Points to spend";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        if(!(obj instanceof PriorityAttributeComboBoxItem)) { return false; }
        final PriorityAttributeComboBoxItem that = (PriorityAttributeComboBoxItem)obj;
        if(that.toString() == null && this.toString() != null) { return false; }
        if(this.toString() == null && that.toString() != null) { return false; }
        
        return this.toString().equals(that.toString());
    }
    
    

}
