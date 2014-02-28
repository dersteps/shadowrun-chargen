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
public class PriorityMagicComboBoxItem extends ComboBoxItem  {
    private Priority priority;
    private Mages type = null;
    
    private int ratingMAG = 0;
    private int ratingRES = 0;
    private int skillsMAG = 0;
    private int ratingSkillsMAG = 0;
    private int activeSkills = 0;
    private int ratingActiveSkills = 0;
    private int skillgroupsMAG = 0;
    private int ratingSkillgroupsMAG = 0;
    private int skillsRES = 0;
    private int ratingSkillsRES = 0;
    private int spells = 0;
    private int forms = 0;

    public Priority getPriority() {
        return priority;
    }

    public Mages getType() {
        return type;
    }

    public int getRatingMAG() {
        return ratingMAG;
    }

    public int getRatingRES() {
        return ratingRES;
    }

    public int getSkillsMAG() {
        return skillsMAG;
    }

    public int getRatingSkillsMAG() {
        return ratingSkillsMAG;
    }

    public int getActiveSkills() {
        return activeSkills;
    }

    public int getRatingActiveSkills() {
        return ratingActiveSkills;
    }

    public int getSkillgroupsMAG() {
        return skillgroupsMAG;
    }

    public int getRatingSkillgroupsMAG() {
        return ratingSkillgroupsMAG;
    }

    public int getSkillsRES() {
        return skillsRES;
    }

    public int getRatingSkillsRES() {
        return ratingSkillsRES;
    }

    public int getSpells() {
        return spells;
    }

    public int getForms() {
        return forms;
    }
    
    
    
    private void init() {
        switch(this.type) {
            case MAGE: {
                this.ratingMAG = this.priority.getMagicMage();
                this.ratingRES = this.priority.getResonanceMage();
                this.skillsMAG = this.priority.getMagicSkillsMage();
                this.ratingSkillsMAG = this.priority.getRatingMagicSkillsMage();
                this.activeSkills = this.priority.getActiveSkillsMage();
                this.ratingActiveSkills = this.priority.getRatingActiveSkillsMage();
                this.skillgroupsMAG = this.priority.getMagicSkillgroupsMage();
                this.ratingSkillgroupsMAG = this.priority.getRatingMagicSkillgroupsMage();
                this.skillsRES = this.priority.getResonanceSkillsMage();
                this.ratingSkillsRES = this.priority.getRatingResonanceSkillsMage();
                this.spells = this.priority.getSpellsMage();
                this.forms = this.priority.getFormsMage();
                break;
            } case ADEPT: {
                this.ratingMAG = this.priority.getMagicAdept();
                this.ratingRES = this.priority.getResonanceAdept();
                this.skillsMAG = this.priority.getMagicSkillsAdept();
                this.ratingSkillsMAG = this.priority.getRatingMagicSkillsAdept();
                this.activeSkills = this.priority.getActiveSkillsAdept();
                this.ratingActiveSkills = this.priority.getRatingActiveSkillsAdept();
                this.skillgroupsMAG = this.priority.getMagicSkillgroupsAdept();
                this.ratingSkillgroupsMAG = this.priority.getRatingMagicSkillgroupsAdept();
                this.skillsRES = this.priority.getResonanceSkillsAdept();
                this.ratingSkillsRES = this.priority.getRatingResonanceSkillsAdept();
                this.spells = this.priority.getSpellsAdept();
                this.forms = this.priority.getFormsAdept();
                break;
            } case MAGEADEPT: {
                this.ratingMAG = this.priority.getMagicMageAdept();
                this.ratingRES = this.priority.getResonanceMageAdept();
                this.skillsMAG = this.priority.getMagicSkillsMageAdept();
                this.ratingSkillsMAG = this.priority.getRatingMagicSkillsMageAdept();
                this.activeSkills = this.priority.getActiveSkillsMageAdept();
                this.ratingActiveSkills = this.priority.getRatingActiveSkillsMageAdept();
                this.skillgroupsMAG = this.priority.getMagicSkillgroupsMageAdept();
                this.ratingSkillgroupsMAG = this.priority.getRatingMagicSkillgroupsMageAdept();
                this.skillsRES = this.priority.getResonanceSkillsMageAdept();
                this.ratingSkillsRES = this.priority.getRatingResonanceSkillsMageAdept();
                this.spells = this.priority.getSpellsMageAdept();
                this.forms = this.priority.getFormsMageAdept();
                break;
            } case ASPECTMAGE: {
                this.ratingMAG = this.priority.getMagicAspectMage();
                this.ratingRES = this.priority.getResonanceAspectMage();
                this.skillsMAG = this.priority.getMagicSkillsAspectMage();
                this.ratingSkillsMAG = this.priority.getRatingMagicSkillsAspectMage();
                this.activeSkills = this.priority.getActiveSkillsAspectMage();
                this.ratingActiveSkills = this.priority.getRatingActiveSkillsAspectMage();
                this.skillgroupsMAG = this.priority.getMagicSkillgroupsAspectMage();
                this.ratingSkillgroupsMAG = this.priority.getRatingMagicSkillgroupsAspectMage();
                this.skillsRES = this.priority.getResonanceSkillsAspectMage();
                this.ratingSkillsRES = this.priority.getRatingResonanceSkillsAspectMage();
                this.spells = this.priority.getSpellsAspectMage();
                this.forms = this.priority.getFormsAspectMage();
                break;
            } case TECHNOMANCER: {
                this.ratingMAG = this.priority.getMagicTechnomancer();
                this.ratingRES = this.priority.getResonanceTechnomancer();
                this.skillsMAG = this.priority.getMagicSkillsTechnomancer();
                this.ratingSkillsMAG = this.priority.getRatingMagicSkillsTechnomancer();
                this.activeSkills = this.priority.getActiveSkillsTechnomancer();
                this.ratingActiveSkills = this.priority.getRatingActiveSkillsTechnomancer();
                this.skillgroupsMAG = this.priority.getMagicSkillgroupsTechnomancer();
                this.ratingSkillgroupsMAG = this.priority.getRatingMagicSkillgroupsTechnomancer();
                this.skillsRES = this.priority.getResonanceSkillsTechnomancer();
                this.ratingSkillsRES = this.priority.getRatingResonanceSkillsTechnomancer();
                this.spells = this.priority.getSpellsTechnomancer();
                this.forms = this.priority.getFormsTechnomancer();
                break;
            } default: {
                this.ratingMAG = 0;
                this.ratingRES = 0;
                this.skillsMAG = 0;
                this.ratingSkillsMAG = 0;
                this.activeSkills = 0;
                this.ratingActiveSkills = 0;
                this.skillgroupsMAG = 0;
                this.ratingSkillgroupsMAG = 0;
                this.skillsRES = 0;
                this.ratingSkillsRES = 0;
                this.spells = 0;
                this.forms = 0;
                break;
            }
        }
    }

    public PriorityMagicComboBoxItem(Mages type, Priority prio) {
        this.priority = prio;
        this.type = type;
        this.init();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.priority.getName()).append(" - ");
        sb.append(Mages.getName(type));
        sb.append(" (");
        
        if(this.ratingMAG > 0) {
            sb.append("Magie ").append(this.ratingMAG);
        }
        
        if(this.ratingRES > 0) {
            sb.append("Resonanz ").append(this.ratingRES);
        }
        
        
        
        sb.append(")");
        
        return sb.toString();
    }
}
