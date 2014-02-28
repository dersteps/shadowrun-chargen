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
public class PriorityMagicComboBoxItem extends PriorityComboBoxItem  {
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
                this.ratingMAG = this.getPriority().getMagicMage();
                this.ratingRES = this.getPriority().getResonanceMage();
                this.skillsMAG = this.getPriority().getMagicSkillsMage();
                this.ratingSkillsMAG = this.getPriority().getRatingMagicSkillsMage();
                this.activeSkills = this.getPriority().getActiveSkillsMage();
                this.ratingActiveSkills = this.getPriority().getRatingActiveSkillsMage();
                this.skillgroupsMAG = this.getPriority().getMagicSkillgroupsMage();
                this.ratingSkillgroupsMAG = this.getPriority().getRatingMagicSkillgroupsMage();
                this.skillsRES = this.getPriority().getResonanceSkillsMage();
                this.ratingSkillsRES = this.getPriority().getRatingResonanceSkillsMage();
                this.spells = this.getPriority().getSpellsMage();
                this.forms = this.getPriority().getFormsMage();
                break;
            } case ADEPT: {
                this.ratingMAG = this.getPriority().getMagicAdept();
                this.ratingRES = this.getPriority().getResonanceAdept();
                this.skillsMAG = this.getPriority().getMagicSkillsAdept();
                this.ratingSkillsMAG = this.getPriority().getRatingMagicSkillsAdept();
                this.activeSkills = this.getPriority().getActiveSkillsAdept();
                this.ratingActiveSkills = this.getPriority().getRatingActiveSkillsAdept();
                this.skillgroupsMAG = this.getPriority().getMagicSkillgroupsAdept();
                this.ratingSkillgroupsMAG = this.getPriority().getRatingMagicSkillgroupsAdept();
                this.skillsRES = this.getPriority().getResonanceSkillsAdept();
                this.ratingSkillsRES = this.getPriority().getRatingResonanceSkillsAdept();
                this.spells = this.getPriority().getSpellsAdept();
                this.forms = this.getPriority().getFormsAdept();
                break;
            } case MAGEADEPT: {
                this.ratingMAG = this.getPriority().getMagicMageAdept();
                this.ratingRES = this.getPriority().getResonanceMageAdept();
                this.skillsMAG = this.getPriority().getMagicSkillsMageAdept();
                this.ratingSkillsMAG = this.getPriority().getRatingMagicSkillsMageAdept();
                this.activeSkills = this.getPriority().getActiveSkillsMageAdept();
                this.ratingActiveSkills = this.getPriority().getRatingActiveSkillsMageAdept();
                this.skillgroupsMAG = this.getPriority().getMagicSkillgroupsMageAdept();
                this.ratingSkillgroupsMAG = this.getPriority().getRatingMagicSkillgroupsMageAdept();
                this.skillsRES = this.getPriority().getResonanceSkillsMageAdept();
                this.ratingSkillsRES = this.getPriority().getRatingResonanceSkillsMageAdept();
                this.spells = this.getPriority().getSpellsMageAdept();
                this.forms = this.getPriority().getFormsMageAdept();
                break;
            } case ASPECTMAGE: {
                this.ratingMAG = this.getPriority().getMagicAspectMage();
                this.ratingRES = this.getPriority().getResonanceAspectMage();
                this.skillsMAG = this.getPriority().getMagicSkillsAspectMage();
                this.ratingSkillsMAG = this.getPriority().getRatingMagicSkillsAspectMage();
                this.activeSkills = this.getPriority().getActiveSkillsAspectMage();
                this.ratingActiveSkills = this.getPriority().getRatingActiveSkillsAspectMage();
                this.skillgroupsMAG = this.getPriority().getMagicSkillgroupsAspectMage();
                this.ratingSkillgroupsMAG = this.getPriority().getRatingMagicSkillgroupsAspectMage();
                this.skillsRES = this.getPriority().getResonanceSkillsAspectMage();
                this.ratingSkillsRES = this.getPriority().getRatingResonanceSkillsAspectMage();
                this.spells = this.getPriority().getSpellsAspectMage();
                this.forms = this.getPriority().getFormsAspectMage();
                break;
            } case TECHNOMANCER: {
                this.ratingMAG = this.getPriority().getMagicTechnomancer();
                this.ratingRES = this.getPriority().getResonanceTechnomancer();
                this.skillsMAG = this.getPriority().getMagicSkillsTechnomancer();
                this.ratingSkillsMAG = this.getPriority().getRatingMagicSkillsTechnomancer();
                this.activeSkills = this.getPriority().getActiveSkillsTechnomancer();
                this.ratingActiveSkills = this.getPriority().getRatingActiveSkillsTechnomancer();
                this.skillgroupsMAG = this.getPriority().getMagicSkillgroupsTechnomancer();
                this.ratingSkillgroupsMAG = this.getPriority().getRatingMagicSkillgroupsTechnomancer();
                this.skillsRES = this.getPriority().getResonanceSkillsTechnomancer();
                this.ratingSkillsRES = this.getPriority().getRatingResonanceSkillsTechnomancer();
                this.spells = this.getPriority().getSpellsTechnomancer();
                this.forms = this.getPriority().getFormsTechnomancer();
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
        super(prio);
        this.type = type;
        this.init();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.getPriority().getName()).append(" - ");
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
