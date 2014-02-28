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
public class PriorityResourceComboBoxItem extends PriorityComboBoxItem  {
    private int resources = 0;

    public int getSkillPoints() {
        return resources;
    }

    public void setSkillPoints(int skillPoints) {
        this.resources = skillPoints;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }
    

    public PriorityResourceComboBoxItem(Priority prio) {
        super(prio);
        this.resources = prio.getResources();
    }
    
    @Override
    public String toString() {
        return String.format("%s - %d \u00A5", new Object[] { this.getPriority().getName(), this.resources} );
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        if(!(obj instanceof PriorityResourceComboBoxItem)) { return false; }
        final PriorityResourceComboBoxItem that = (PriorityResourceComboBoxItem)obj;
        if(that.toString() == null && this.toString() != null) { return false; }
        if(this.toString() == null && that.toString() != null) { return false; }
        
        return this.toString().equals(that.toString());
    }
    
    

}
