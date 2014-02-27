/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.data;

/**
 * @author Steps
 */
public class Priority {
    
    private int id;
    
    private String name;
    
    private boolean humanPossible = false;
    private boolean elfPossible = false;
    private boolean orkPossible = false;
    private boolean dwarfPossible = false;
    private boolean trollPossible = false;
    
    private int specialHuman = 0;
    private int specialElf = 0;
    private int specialOrk = 0;
    private int specialDwarf = 0;
    private int specialTroll = 0;
    
    private int attributes = 0;
    private int skillpoints = 0;
    private int skillgroupPoints = 0;
    private int resources = 0;
    
    private boolean magePossible =false;
    private boolean mageAdeptPossible = false;
    private boolean technomancerPossible = false;
    private boolean adeptPossible = false;
    private boolean aspectMagePossible = false;
    
    private int magicMage = 0;
    private int magicMageAdept = 0;
    private int magicAdept = 0;
    private int magicAspectMage = 0;
    private int magicTechnomancer = 0;
    
    private int resonanceMage = 0;
    private int resonanceAdept = 0;
    private int resonanceMageAdept = 0;
    private int resonanceAspectMage = 0;
    private int resonanceTechnomancer = 0;
        
    private int magicSkillsMage = 0;
    private int magicSkillsMageAdept = 0;
    private int magicSkillsAspectMage = 0;
    private int magicSkillsAdept = 0;
    private int magicSkillsTechnomancer = 0;
     
    private int ratingMagicSkillsMage = 0;
    private int ratingMagicSkillsMageAdept = 0;
    private int ratingMagicSkillsAspectMage = 0;
    private int ratingMagicSkillsAdept = 0;
    private int ratingMagicSkillsTechnomancer = 0;
    
    private int activeSkillsMage = 0;
    private int activeSkillsAdept = 0;
    private int activeSkillsMageAdept = 0;
    private int activeSkillsAspectMage = 0;
    private int activeSkillsTechnomancer = 0;
    
    private int ratingActiveSkillsMage = 0;
    private int ratingActiveSkillsAdept = 0;
    private int ratingActiveSkillsMageAdept = 0;
    private int ratingActiveSkillsAspectMage = 0;
    private int ratingActiveSkillsTechnomancer = 0;
    
    private int magicSkillgroupsMage = 0;
    private int magicSkillgroupsAdept = 0;
    private int magicSkillgroupsMageAdept = 0;
    private int magicSkillgroupsAspectMage = 0;
    private int magicSkillgroupsTechnomancer = 0;
    
    private int ratingMagicSkillgroupsMage = 0;
    private int ratingMagicSkillgroupsAdept = 0;
    private int ratingMagicSkillgroupsMageAdept = 0;
    private int ratingMagicSkillgroupsAspectMage = 0;
    private int ratingMagicSkillgroupsTechnomancer = 0;
    
    private int resonanceSkillsMage = 0;
    private int resonanceSkillsAdept = 0;
    private int resonanceSkillsMageAdept = 0;
    private int resonanceSkillsAspectMage = 0;
    private int resonanceSkillsTechnomancer = 0;
    
    private int ratingResonanceSkillsMage = 0;
    private int ratingResonanceSkillsAdept = 0;
    private int ratingResonanceSkillsMageAdept = 0;
    private int ratingResonanceSkillsAspectMage = 0;
    private int ratingResonanceSkillsTechnomancer = 0;
    
    private int spellsMage = 0;
    private int spellsAdept = 0;
    private int spellsMageAdept = 0;
    private int spellsAspectMage = 0;
    private int spellsTechnomancer = 0;
    
    private int formsMage = 0;
    private int formsAdept = 0;
    private int formsMageAdept = 0;
    private int formsAspectMage = 0;
    private int formsTechnomancer = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHumanPossible() {
        return humanPossible;
    }

    public void setHumanPossible(boolean humanPossible) {
        this.humanPossible = humanPossible;
    }

    public boolean isElfPossible() {
        return elfPossible;
    }

    public void setElfPossible(boolean elfPossible) {
        this.elfPossible = elfPossible;
    }

    public boolean isOrkPossible() {
        return orkPossible;
    }

    public void setOrkPossible(boolean orkPossible) {
        this.orkPossible = orkPossible;
    }

    public boolean isDwarfPossible() {
        return dwarfPossible;
    }

    public void setDwarfPossible(boolean dwarfPossible) {
        this.dwarfPossible = dwarfPossible;
    }

    public boolean isTrollPossible() {
        return trollPossible;
    }

    public void setTrollPossible(boolean trollProssible) {
        this.trollPossible = trollProssible;
    }

    public int getSpecialHuman() {
        return specialHuman;
    }

    public void setSpecialHuman(int specialHuman) {
        this.specialHuman = specialHuman;
    }

    public int getSpecialElf() {
        return specialElf;
    }

    public void setSpecialElf(int specialElf) {
        this.specialElf = specialElf;
    }

    public int getSpecialOrk() {
        return specialOrk;
    }

    public void setSpecialOrk(int specialOrk) {
        this.specialOrk = specialOrk;
    }

    public int getSpecialDwarf() {
        return specialDwarf;
    }

    public void setSpecialDwarf(int specialDwarf) {
        this.specialDwarf = specialDwarf;
    }

    public int getSpecialTroll() {
        return specialTroll;
    }

    public void setSpecialTroll(int specialTroll) {
        this.specialTroll = specialTroll;
    }

    public int getAttributes() {
        return attributes;
    }

    public void setAttributes(int attributes) {
        this.attributes = attributes;
    }

    public int getSkillpoints() {
        return skillpoints;
    }

    public void setSkillpoints(int skillpoints) {
        this.skillpoints = skillpoints;
    }

    public int getSkillgroupPoints() {
        return skillgroupPoints;
    }

    public void setSkillgroupPoints(int skillgroupPoints) {
        this.skillgroupPoints = skillgroupPoints;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public boolean isMagePossible() {
        return magePossible;
    }

    public void setMagePossible(boolean magePossible) {
        this.magePossible = magePossible;
    }

    public boolean isMageAdeptPossible() {
        return mageAdeptPossible;
    }

    public void setMageAdeptPossible(boolean mageAdeptPossible) {
        this.mageAdeptPossible = mageAdeptPossible;
    }

    public boolean isTechnomancerPossible() {
        return technomancerPossible;
    }

    public void setTechnomancerPossible(boolean technomancerPossible) {
        this.technomancerPossible = technomancerPossible;
    }

    public boolean isAdeptPossible() {
        return adeptPossible;
    }

    public void setAdeptPossible(boolean adeptPossible) {
        this.adeptPossible = adeptPossible;
    }

    public boolean isAspectMagePossible() {
        return aspectMagePossible;
    }

    public void setAspectMagePossible(boolean aspectMagePossible) {
        this.aspectMagePossible = aspectMagePossible;
    }

    public int getMagicMage() {
        return magicMage;
    }

    public void setMagicMage(int magicMage) {
        this.magicMage = magicMage;
    }

    public int getMagicMageAdept() {
        return magicMageAdept;
    }

    public void setMagicMageAdept(int magicMageAdept) {
        this.magicMageAdept = magicMageAdept;
    }

    public int getMagicAdept() {
        return magicAdept;
    }

    public void setMagicAdept(int magicAdept) {
        this.magicAdept = magicAdept;
    }

    public int getMagicAspectMage() {
        return magicAspectMage;
    }

    public void setMagicAspectMage(int magicAspectMage) {
        this.magicAspectMage = magicAspectMage;
    }

    public int getMagicTechnomancer() {
        return magicTechnomancer;
    }

    public void setMagicTechnomancer(int magicTechnomancer) {
        this.magicTechnomancer = magicTechnomancer;
    }

    public int getResonanceMage() {
        return resonanceMage;
    }

    public void setResonanceMage(int resonanceMage) {
        this.resonanceMage = resonanceMage;
    }

    public int getResonanceAdept() {
        return resonanceAdept;
    }

    public void setResonanceAdept(int resonanceAdept) {
        this.resonanceAdept = resonanceAdept;
    }

    public int getResonanceMageAdept() {
        return resonanceMageAdept;
    }

    public void setResonanceMageAdept(int resonanceMageAdept) {
        this.resonanceMageAdept = resonanceMageAdept;
    }

    public int getResonanceAspectMage() {
        return resonanceAspectMage;
    }

    public void setResonanceAspectMage(int resonanceAspectMage) {
        this.resonanceAspectMage = resonanceAspectMage;
    }

    public int getResonanceTechnomancer() {
        return resonanceTechnomancer;
    }

    public void setResonanceTechnomancer(int resonanceTechnomancer) {
        this.resonanceTechnomancer = resonanceTechnomancer;
    }

    public int getMagicSkillsMage() {
        return magicSkillsMage;
    }

    public void setMagicSkillsMage(int magicSkillsMage) {
        this.magicSkillsMage = magicSkillsMage;
    }

    public int getMagicSkillsMageAdept() {
        return magicSkillsMageAdept;
    }

    public void setMagicSkillsMageAdept(int magicSkillsMageAdept) {
        this.magicSkillsMageAdept = magicSkillsMageAdept;
    }

    public int getMagicSkillsAspectMage() {
        return magicSkillsAspectMage;
    }

    public void setMagicSkillsAspectMage(int magicSkillsAspectMage) {
        this.magicSkillsAspectMage = magicSkillsAspectMage;
    }

    public int getMagicSkillsAdept() {
        return magicSkillsAdept;
    }

    public void setMagicSkillsAdept(int magicSkillsAdept) {
        this.magicSkillsAdept = magicSkillsAdept;
    }

    public int getMagicSkillsTechnomancer() {
        return magicSkillsTechnomancer;
    }

    public void setMagicSkillsTechnomancer(int magicSkillsTechnomancer) {
        this.magicSkillsTechnomancer = magicSkillsTechnomancer;
    }

    public int getRatingMagicSkillsMage() {
        return ratingMagicSkillsMage;
    }

    public void setRatingMagicSkillsMage(int ratingMagicSkillsMage) {
        this.ratingMagicSkillsMage = ratingMagicSkillsMage;
    }

    public int getRatingMagicSkillsMageAdept() {
        return ratingMagicSkillsMageAdept;
    }

    public void setRatingMagicSkillsMageAdept(int ratingMagicSkillsMageAdept) {
        this.ratingMagicSkillsMageAdept = ratingMagicSkillsMageAdept;
    }

    public int getRatingMagicSkillsAspectMage() {
        return ratingMagicSkillsAspectMage;
    }

    public void setRatingMagicSkillsAspectMage(int ratingMagicSkillsAspectMage) {
        this.ratingMagicSkillsAspectMage = ratingMagicSkillsAspectMage;
    }

    public int getRatingMagicSkillsAdept() {
        return ratingMagicSkillsAdept;
    }

    public void setRatingMagicSkillsAdept(int ratingMagicSkillsAdept) {
        this.ratingMagicSkillsAdept = ratingMagicSkillsAdept;
    }

    public int getRatingMagicSkillsTechnomancer() {
        return ratingMagicSkillsTechnomancer;
    }

    public void setRatingMagicSkillsTechnomancer(int ratingMagicSkillsTechnomancer) {
        this.ratingMagicSkillsTechnomancer = ratingMagicSkillsTechnomancer;
    }

    public int getActiveSkillsMage() {
        return activeSkillsMage;
    }

    public void setActiveSkillsMage(int activeSkillsMage) {
        this.activeSkillsMage = activeSkillsMage;
    }

    public int getActiveSkillsAdept() {
        return activeSkillsAdept;
    }

    public void setActiveSkillsAdept(int activeSkillsAdept) {
        this.activeSkillsAdept = activeSkillsAdept;
    }

    public int getActiveSkillsMageAdept() {
        return activeSkillsMageAdept;
    }

    public void setActiveSkillsMageAdept(int activeSkillsMageAdept) {
        this.activeSkillsMageAdept = activeSkillsMageAdept;
    }

    public int getActiveSkillsAspectMage() {
        return activeSkillsAspectMage;
    }

    public void setActiveSkillsAspectMage(int activeSkillsAspectMage) {
        this.activeSkillsAspectMage = activeSkillsAspectMage;
    }

    public int getActiveSkillsTechnomancer() {
        return activeSkillsTechnomancer;
    }

    public void setActiveSkillsTechnomancer(int activeSkillsTechnomancer) {
        this.activeSkillsTechnomancer = activeSkillsTechnomancer;
    }

    public int getRatingActiveSkillsMage() {
        return ratingActiveSkillsMage;
    }

    public void setRatingActiveSkillsMage(int ratingActiveSkillsMage) {
        this.ratingActiveSkillsMage = ratingActiveSkillsMage;
    }

    public int getRatingActiveSkillsAdept() {
        return ratingActiveSkillsAdept;
    }

    public void setRatingActiveSkillsAdept(int ratingActiveSkillsAdept) {
        this.ratingActiveSkillsAdept = ratingActiveSkillsAdept;
    }

    public int getRatingActiveSkillsMageAdept() {
        return ratingActiveSkillsMageAdept;
    }

    public void setRatingActiveSkillsMageAdept(int ratingActiveSkillsMageAdept) {
        this.ratingActiveSkillsMageAdept = ratingActiveSkillsMageAdept;
    }

    public int getRatingActiveSkillsAspectMage() {
        return ratingActiveSkillsAspectMage;
    }

    public void setRatingActiveSkillsAspectMage(int ratingActiveSkillsAspectMage) {
        this.ratingActiveSkillsAspectMage = ratingActiveSkillsAspectMage;
    }

    public int getRatingActiveSkillsTechnomancer() {
        return ratingActiveSkillsTechnomancer;
    }

    public void setRatingActiveSkillsTechnomancer(int ratingActiveSkillsTechnomancer) {
        this.ratingActiveSkillsTechnomancer = ratingActiveSkillsTechnomancer;
    }

    public int getMagicSkillgroupsMage() {
        return magicSkillgroupsMage;
    }

    public void setMagicSkillgroupsMage(int magicSkillgroupsMage) {
        this.magicSkillgroupsMage = magicSkillgroupsMage;
    }

    public int getMagicSkillgroupsAdept() {
        return magicSkillgroupsAdept;
    }

    public void setMagicSkillgroupsAdept(int magicSkillgroupsAdept) {
        this.magicSkillgroupsAdept = magicSkillgroupsAdept;
    }

    public int getMagicSkillgroupsMageAdept() {
        return magicSkillgroupsMageAdept;
    }

    public void setMagicSkillgroupsMageAdept(int magicSkillgroupsMageAdept) {
        this.magicSkillgroupsMageAdept = magicSkillgroupsMageAdept;
    }

    public int getMagicSkillgroupsAspectMage() {
        return magicSkillgroupsAspectMage;
    }

    public void setMagicSkillgroupsAspectMage(int magicSkillgroupsAspectMage) {
        this.magicSkillgroupsAspectMage = magicSkillgroupsAspectMage;
    }

    public int getMagicSkillgroupsTechnomancer() {
        return magicSkillgroupsTechnomancer;
    }

    public void setMagicSkillgroupsTechnomancer(int magicSkillgroupsTechnomancer) {
        this.magicSkillgroupsTechnomancer = magicSkillgroupsTechnomancer;
    }

    public int getRatingMagicSkillgroupsMage() {
        return ratingMagicSkillgroupsMage;
    }

    public void setRatingMagicSkillgroupsMage(int ratingMagicSkillgroupsMage) {
        this.ratingMagicSkillgroupsMage = ratingMagicSkillgroupsMage;
    }

    public int getRatingMagicSkillgroupsAdept() {
        return ratingMagicSkillgroupsAdept;
    }

    public void setRatingMagicSkillgroupsAdept(int ratingMagicSkillgroupsAdept) {
        this.ratingMagicSkillgroupsAdept = ratingMagicSkillgroupsAdept;
    }

    public int getRatingMagicSkillgroupsMageAdept() {
        return ratingMagicSkillgroupsMageAdept;
    }

    public void setRatingMagicSkillgroupsMageAdept(int ratingMagicSkillgroupsMageAdept) {
        this.ratingMagicSkillgroupsMageAdept = ratingMagicSkillgroupsMageAdept;
    }

    public int getRatingMagicSkillgroupsAspectMage() {
        return ratingMagicSkillgroupsAspectMage;
    }

    public void setRatingMagicSkillgroupsAspectMage(int ratingMagicSkillgroupsAspectMage) {
        this.ratingMagicSkillgroupsAspectMage = ratingMagicSkillgroupsAspectMage;
    }

    public int getRatingMagicSkillgroupsTechnomancer() {
        return ratingMagicSkillgroupsTechnomancer;
    }

    public void setRatingMagicSkillgroupsTechnomancer(int ratingMagicSkillgroupsTechnomancer) {
        this.ratingMagicSkillgroupsTechnomancer = ratingMagicSkillgroupsTechnomancer;
    }

    public int getResonanceSkillsMage() {
        return resonanceSkillsMage;
    }

    public void setResonanceSkillsMage(int resonanceSkillsMage) {
        this.resonanceSkillsMage = resonanceSkillsMage;
    }

    public int getResonanceSkillsAdept() {
        return resonanceSkillsAdept;
    }

    public void setResonanceSkillsAdept(int resonanceSkillsAdept) {
        this.resonanceSkillsAdept = resonanceSkillsAdept;
    }

    public int getResonanceSkillsMageAdept() {
        return resonanceSkillsMageAdept;
    }

    public void setResonanceSkillsMageAdept(int resonanceSkillsMageAdept) {
        this.resonanceSkillsMageAdept = resonanceSkillsMageAdept;
    }

    public int getResonanceSkillsAspectMage() {
        return resonanceSkillsAspectMage;
    }

    public void setResonanceSkillsAspectMage(int resonanceSkillsAspectMage) {
        this.resonanceSkillsAspectMage = resonanceSkillsAspectMage;
    }

    public int getResonanceSkillsTechnomancer() {
        return resonanceSkillsTechnomancer;
    }

    public void setResonanceSkillsTechnomancer(int resonanceSkillsTechnomancer) {
        this.resonanceSkillsTechnomancer = resonanceSkillsTechnomancer;
    }

    public int getRatingResonanceSkillsMage() {
        return ratingResonanceSkillsMage;
    }

    public void setRatingResonanceSkillsMage(int ratingResonanceSkillsMage) {
        this.ratingResonanceSkillsMage = ratingResonanceSkillsMage;
    }

    public int getRatingResonanceSkillsAdept() {
        return ratingResonanceSkillsAdept;
    }

    public void setRatingResonanceSkillsAdept(int ratingResonanceSkillsAdept) {
        this.ratingResonanceSkillsAdept = ratingResonanceSkillsAdept;
    }

    public int getRatingResonanceSkillsMageAdept() {
        return ratingResonanceSkillsMageAdept;
    }

    public void setRatingResonanceSkillsMageAdept(int ratingResonanceSkillsMageAdept) {
        this.ratingResonanceSkillsMageAdept = ratingResonanceSkillsMageAdept;
    }

    public int getRatingResonanceSkillsAspectMage() {
        return ratingResonanceSkillsAspectMage;
    }

    public void setRatingResonanceSkillsAspectMage(int ratingResonanceSkillsAspectMage) {
        this.ratingResonanceSkillsAspectMage = ratingResonanceSkillsAspectMage;
    }

    public int getRatingResonanceSkillsTechnomancer() {
        return ratingResonanceSkillsTechnomancer;
    }

    public void setRatingResonanceSkillsTechnomancer(int ratingResonanceSkillsTechnomancer) {
        this.ratingResonanceSkillsTechnomancer = ratingResonanceSkillsTechnomancer;
    }

    public int getSpellsMage() {
        return spellsMage;
    }

    public void setSpellsMage(int spellsMage) {
        this.spellsMage = spellsMage;
    }

    public int getSpellsAdept() {
        return spellsAdept;
    }

    public void setSpellsAdept(int spellsAdept) {
        this.spellsAdept = spellsAdept;
    }

    public int getSpellsMageAdept() {
        return spellsMageAdept;
    }

    public void setSpellsMageAdept(int spellsMageAdept) {
        this.spellsMageAdept = spellsMageAdept;
    }

    public int getSpellsAspectMage() {
        return spellsAspectMage;
    }

    public void setSpellsAspectMage(int spellsAspectMage) {
        this.spellsAspectMage = spellsAspectMage;
    }

    public int getSpellsTechnomancer() {
        return spellsTechnomancer;
    }

    public void setSpellsTechnomancer(int spellsTechnomancer) {
        this.spellsTechnomancer = spellsTechnomancer;
    }

    public int getFormsMage() {
        return formsMage;
    }

    public void setFormsMage(int formsMage) {
        this.formsMage = formsMage;
    }

    public int getFormsAdept() {
        return formsAdept;
    }

    public void setFormsAdept(int formsAdept) {
        this.formsAdept = formsAdept;
    }

    public int getFormsMageAdept() {
        return formsMageAdept;
    }

    public void setFormsMageAdept(int formsMageAdept) {
        this.formsMageAdept = formsMageAdept;
    }

    public int getFormsAspectMage() {
        return formsAspectMage;
    }

    public void setFormsAspectMage(int formsAspectMage) {
        this.formsAspectMage = formsAspectMage;
    }

    public int getFormsTechnomancer() {
        return formsTechnomancer;
    }

    public void setFormsTechnomancer(int formsTechnomancer) {
        this.formsTechnomancer = formsTechnomancer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
