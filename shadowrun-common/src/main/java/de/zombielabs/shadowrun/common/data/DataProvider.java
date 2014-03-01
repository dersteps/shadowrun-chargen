/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.data;

import de.zombielabs.shadowrun.common.data.gear.Drug;
import de.zombielabs.shadowrun.common.data.gear.Gear;
import de.zombielabs.shadowrun.common.data.gear.Toxin;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steps
 */
public abstract class DataProvider {
    protected List<Priority> priorities = null;
    protected List<Attribute> attributes = null;
    protected List<Skillgroup> skillgroups = null;
    protected List<Skill> skills = null;
    protected List<Metatype> metatypes = null;
    protected List<Perk> perks = null;
    protected List<Flaw> flaws = null;
    protected List<Toxin> toxins = null;
    protected List<Drug> drugs = null;
    
    protected Rules rules = null;
    
    private List<DataProviderListener> listeners = new ArrayList<DataProviderListener>();
    
    public void addListener(DataProviderListener listener) {
        this.listeners.add(listener);
    }
    
    
    
    public List<Drug> getDrugs() {
        return drugs;
    }
    
    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Priority> getPriorities() {
        return priorities;
    }

    public List<Skillgroup> getSkillgroups() {
        return skillgroups;
    }

    public List<Metatype> getMetatypes() {
        return metatypes;
    }
    
    public List<Perk> getPerks() {
        return perks;
    }
    
    public List<Flaw> getFlaws() {
        return flaws;
    }

    public List<Toxin> getToxins() {
        return toxins;
    }

    public Rules getRules() {
        return rules;
    }
    
    
    

    protected void onLoadingFinished() {
        for(final DataProviderListener listener : this.listeners) {
            listener.onLoadingFinished();
        }
    }
    
    protected void onLoadingData() {
        for(final DataProviderListener listener : this.listeners) {
            listener.onLoadingData();
        }
    }
    
    protected void onAttributeLoaded(Attribute attribute) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onAttributeLoaded(attribute);
        }
    }
    
    protected void onSkillLoaded(Skill skill) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onSkillLoaded(skill);
        }
    }
    
    protected void onGearItemLoaded(Gear gear) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onGearItemLoaded(gear);
        }
    }
    
    protected void onSkillGroupLoaded(Skillgroup group) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onSkillGroupLoaded(group);
        }
    }
    
    protected void onMetatypeLoaded(Metatype metatype) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onMetatypeLoaded(metatype);
        }
    }
    
    protected void onPriorityLoaded(Priority priority) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onPriorityLoaded(priority);
        }
    }
    
    protected void onLoadingFailed(Exception ex) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onLoadingFailed(ex);
        }
    }
    
    protected void onFlawLoaded(Flaw flaw) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onFlawLoaded(flaw);
        }
    }
    
    protected void onPerkLoaded(Perk perk) {
        for(final DataProviderListener listener : this.listeners) {
            listener.onPerkLoaded(perk);
        }
    }
    
    public abstract void loadAll();
    
    public abstract void reloadAttributes() throws SQLException;
    public abstract void reloadFlaws() throws SQLException;
    public abstract void reloadPerks() throws SQLException;
    public abstract void reloadToxins() throws SQLException;
    public abstract void reloadDrugs() throws SQLException;
    public abstract void reloadMetatypes() throws SQLException;
    public abstract void reloadPriorities() throws SQLException;
    public abstract void reloadSkills() throws SQLException;
    public abstract void reloadSkillgroups() throws SQLException;
    public abstract void reloadRules() throws SQLException;
    
    public abstract boolean update(String sql) throws SQLException;
    
    
}
