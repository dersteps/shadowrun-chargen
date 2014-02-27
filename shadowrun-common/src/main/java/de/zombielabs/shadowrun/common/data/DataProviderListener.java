package de.zombielabs.shadowrun.common.data;

import de.zombielabs.shadowrun.common.data.gear.Gear;

/**
 *
 * @author Steps
 */
public interface DataProviderListener {
    void onAttributeLoaded(Attribute attribute);
    void onLoadingData();
    void onLoadingFinished();
    void onSkillLoaded(Skill skill);
    void onSkillGroupLoaded(Skillgroup group);
    void onMetatypeLoaded(Metatype metatype);
    void onPriorityLoaded(Priority priority);
    void onPerkLoaded(Perk perk);
    void onFlawLoaded(Flaw flaw);
    
    void onLoadingFailed(Exception ex);
    
    void onGearItemLoaded(Gear gear);
}
