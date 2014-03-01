package de.zombielabs.shadowrun.util;

import de.zombielabs.shadowrun.common.data.Metatype;
import de.zombielabs.shadowrun.forms.data.PriorityAttributeComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityMagicComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityMetatypeComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityResourceComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PrioritySkillComboBoxItem;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Steps
 */
public class PriorityUtil {
    
    private static PriorityAttributeComboBoxItem attributePriority;
    private static PriorityMagicComboBoxItem magicPriority;
    private static PriorityMetatypeComboBoxItem metaPriority;
    private static PrioritySkillComboBoxItem skillPriority;
    private static PriorityResourceComboBoxItem resourcePriority;

    public static PriorityAttributeComboBoxItem getAttributePriority() {
        return attributePriority;
    }

    public static void setAttributePriority(PriorityAttributeComboBoxItem attributePriority) {
        PriorityUtil.attributePriority = attributePriority;
    }

    public static PriorityMagicComboBoxItem getMagicPriority() {
        return magicPriority;
    }

    public static void setMagicPriority(PriorityMagicComboBoxItem magicPriority) {
        PriorityUtil.magicPriority = magicPriority;
    }

    public static PriorityMetatypeComboBoxItem getMetaPriority() {
        return metaPriority;
    }

    public static void setMetaPriority(PriorityMetatypeComboBoxItem metaPriority) {
        PriorityUtil.metaPriority = metaPriority;
    }

    public static PrioritySkillComboBoxItem getSkillPriority() {
        return skillPriority;
    }

    public static void setSkillPriority(PrioritySkillComboBoxItem skillPriority) {
        PriorityUtil.skillPriority = skillPriority;
    }

    public static PriorityResourceComboBoxItem getResourcePriority() {
        return resourcePriority;
    }

    public static void setResourcePriority(PriorityResourceComboBoxItem resourcePriority) {
        PriorityUtil.resourcePriority = resourcePriority;
    }
    
    public static String getMetaPriorityName() {
        return getPriorityName(metaPriority);
    }
    
    public static String getAttributePriorityName() {
        return getPriorityName(attributePriority);
    }
    
    public static String getMagicPriorityName() {
        return getPriorityName(magicPriority);
    }
    
    public static String getSkillPriorityName() {
        return getPriorityName(skillPriority);
    }
    
    public static String getResourcePriorityName() {
        return getPriorityName(resourcePriority);
    }
    
    public static String getMetaName() {
        return metaPriority.getMeta().getName();
    }
    
    public static String getDefaultKONString() {
        return metaPriority.getMeta().getBodyDefault() + " / " + metaPriority.getMeta().getBodyMax();
    }
    
    public static String getDefaultGESString() {
        return metaPriority.getMeta().getDexterityDefault() + " / " + metaPriority.getMeta().getDexterityMax();
    }
    
    public static String getDefaultREAString() {
        return metaPriority.getMeta().getReactionDefault() + " / " + metaPriority.getMeta().getReactionMax();
    }
    
    public static String getDefaultSTRString() {
        return metaPriority.getMeta().getStrengthDefault() + " / " + metaPriority.getMeta().getStrengthMax();
    }
    
    public static String getDefaultCHAString() {
        return metaPriority.getMeta().getCharismaDefault() + " / " + metaPriority.getMeta().getCharismaMax();
    }
    
    public static String getDefaultINTString() {
        return metaPriority.getMeta().getIntuitionDefault() + " / " + metaPriority.getMeta().getIntuitionMax();
    }
    
    public static String getDefaultWILString() {
        return metaPriority.getMeta().getWillpowerDefault() + " / " + metaPriority.getMeta().getWillpowerMax();
    }
    
    public static String getDefaultLOGString() {
        return metaPriority.getMeta().getLogicDefault() + " / " + metaPriority.getMeta().getLogicMax();
    }
    
    public static String getDefaultEDGString() {
        return metaPriority.getMeta().getEdgeDefault() + " / " + metaPriority.getMeta().getEdgeMax();
    }
    
    public static String getRatingMAG() {
        return magicPriority.getRatingMAG()+"";
    }
    
    public static String getRatingRES() {
        return magicPriority.getRatingRES()+"";
    }
    
    
    
    public static String getPriorityName(PriorityComboBoxItem item) {
        try {
            return item.getPriority().getName();
        } catch (Exception ex){
            ///TODO: Log
            return null;
        }
    }
    
    public static int getAttributePoints() {
        try {
            return attributePriority.getAttributePoints();
        } catch (Exception ex) {
            ///TODO: LOG
            return 0;
        }
    }
    
    public static int getSkillPoints() {
        try {
            return skillPriority.getSkillPoints();
        } catch (Exception ex) {
            // TODO: LOG
            return 0;
        }
    }
    
    public static int getSkillGroupPoints() {
        try {
            return skillPriority.getSkillgroupPoints();
        } catch (Exception ex) {
            // TODO: LOG
            return 0;
        }
    }
    
    public static String getResourceString() {
        try {
            return String.format("%d \u00A5", new Object[] { resourcePriority.getResources() });
        } catch (Exception ex) {
            ///TODO: LOG
            return "?";
        }
    }
    
    public static SpinnerNumberModel getKONModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getBodyDefault();
        final int a = m.getBodyMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getGESModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getDexterityDefault();
        final int a = m.getDexterityMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getREAModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getReactionDefault();
        final int a = m.getReactionMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getSTRModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getStrengthDefault();
        final int a = m.getStrengthMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getCHAModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getCharismaDefault();
        final int a = m.getCharismaMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getINTModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getIntuitionDefault();
        final int a = m.getIntuitionMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getWILModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getWillpowerDefault();
        final int a = m.getWillpowerMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getLOGModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getLogicDefault();
        final int a = m.getLogicMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getEDGModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getEdgeDefault();
        final int a = m.getEdgeMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getMAGModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getMagicDefault();
        final int a = m.getMagicMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
    
    public static SpinnerNumberModel getRESModel() {
        Metatype m = metaPriority.getMeta();
        final int v = m.getResonanceDefault();
        final int a = m.getResonanceMax();
        return new SpinnerNumberModel(v,v,a,1);
    }
}
