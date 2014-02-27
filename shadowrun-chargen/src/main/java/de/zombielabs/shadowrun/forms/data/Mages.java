/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.data;

/**
 *
 * @author Steps
 */
public enum Mages {
    MAGE,
    ADEPT,
    TECHNOMANCER,
    MAGEADEPT,
    ASPECTMAGE,
    MUNDANE;
    
    public static String getName(Mages mage) {
        switch(mage) {
            case MAGE: { return "Zauberer"; }
            case ADEPT: { return "Adept"; }
            case ASPECTMAGE: { return "Aspektzauberer"; }
            case MAGEADEPT: { return "Magieradept"; }
            case TECHNOMANCER: { return "Technomancer"; }
        }
        return "Mundan";
    }
    
}
