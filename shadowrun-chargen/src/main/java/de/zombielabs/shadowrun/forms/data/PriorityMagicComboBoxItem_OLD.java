/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.data;

import de.zombielabs.shadowrun.common.data.Priority;
import java.util.HashMap;

/**
 *
 * @author Steps
 */
public class PriorityMagicComboBoxItem_OLD extends ComboBoxItem  {
    private Priority priority;
    private Mages type = null;
    private int magic = 0;
    private boolean isMagic = true;
    private boolean isMundane = false;
    
    private int skillAmount = 0;
    private int skillLevel = 0;
    private int skillgroupAmount = 0;
    private int skillgroupLevel = 0;

    public int getSkillgroupAmount() {
        return skillgroupAmount;
    }

    public void setSkillgroupAmount(int skillgroupAmount) {
        this.skillgroupAmount = skillgroupAmount;
    }

    public int getSkillgroupLevel() {
        return skillgroupLevel;
    }

    public void setSkillgroupLevel(int skillgroupLevel) {
        this.skillgroupLevel = skillgroupLevel;
    }
    
    public int getSkillAmount() {
        return skillAmount;
    }

    public void setSkillAmount(int skillAmount) {
        this.skillAmount = skillAmount;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public static HashMap<Mages, String> getNames() {
        return names;
    }

    public static void setNames(HashMap<Mages, String> names) {
        PriorityMagicComboBoxItem_OLD.names = names;
    }

    private static HashMap<Mages, String> names = null;
    
    static {
        names = new HashMap<Mages, String>() {{
            put(Mages.MAGE, "Magier");
            put(Mages.ADEPT, "KI-Adept");
            put(Mages.ASPECTMAGE, "Aspektzauberer");
            put(Mages.MAGEADEPT, "Magieradept");
            put(Mages.MUNDANE, "Mundan");
            put(Mages.TECHNOMANCER, "Technomancer");
        }};
        
    }

    public boolean isIsMundane() {
        return isMundane;
    }

    public void setIsMundane(boolean isMundane) {
        this.isMundane = isMundane;
    }
    
    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public boolean isIsMagic() {
        return isMagic;
    }

    public void setIsMagic(boolean isMagic) {
        this.isMagic = isMagic;
    }
    
    public Mages getType() {
        return type;
    }

    public void setType(Mages type) {
        this.type = type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public PriorityMagicComboBoxItem_OLD(Mages type, Priority prio, int magic, int skillAmount, int skillLevel, int skillgroupAmount, int skillgroupLevel) {
        this.priority = prio;
        this.type = type;
        this.magic = magic;
        this.skillAmount = skillAmount;
        this.skillLevel = skillLevel;
        this.skillgroupAmount = skillgroupAmount;
        this.skillgroupLevel = skillgroupLevel;
    }
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        // A - MAGIER 
        sb.append("%s - %s");
        
        if(!this.isMundane) {
            
        
            
            if(this.isMagic) {
                
                
                
                return this.priority.getName() + " - " + names.get(this.type) + " (Magic " + this.magic + ", " + this.skillAmount + " magische Fertigkeiten auf Stufe " + this.skillLevel + ")";
            } else {
                return this.priority.getName() + " - " + names.get(this.type) + " (Resonance " + this.magic + ")";
            }
        } else {
            return this.priority.getName() + " - " + names.get(this.type); 
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) { return false; }
        if(!(obj instanceof PriorityMagicComboBoxItem_OLD)) { return false; }
        final PriorityMagicComboBoxItem_OLD that = (PriorityMagicComboBoxItem_OLD)obj;
        if(that.toString() == null && this.toString() != null) { return false; }
        if(this.toString() == null && that.toString() != null) { return false; }
        
        return this.toString().equals(that.toString());
    }
    
    

}
