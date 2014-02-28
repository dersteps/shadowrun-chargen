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
public class PrioritySkillComboBoxItem extends PriorityComboBoxItem  {
    private int skillPoints = 0;
    private int skillgroupPoints = 0;

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public int getSkillgroupPoints() {
        return skillgroupPoints;
    }

    public void setSkillgroupPoints(int skillgroupPoints) {
        this.skillgroupPoints = skillgroupPoints;
    }
    
    public PrioritySkillComboBoxItem(Priority prio) {
        super(prio);
        this.skillPoints = prio.getSkillpoints();
        this.skillgroupPoints = prio.getSkillgroupPoints();
    }
    
    @Override
    public String toString() {
        return this.getPriority().getName() + " - " + this.skillPoints + "/" + this.skillgroupPoints;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        if(!(obj instanceof PrioritySkillComboBoxItem)) { return false; }
        final PrioritySkillComboBoxItem that = (PrioritySkillComboBoxItem)obj;
        if(that.toString() == null && this.toString() != null) { return false; }
        if(this.toString() == null && that.toString() != null) { return false; }
        
        return this.toString().equals(that.toString());
    }
    
    

}
