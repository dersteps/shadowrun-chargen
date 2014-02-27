/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.data.gear;

import de.zombielabs.shadowrun.common.data.annotations.DomainClass;
import de.zombielabs.shadowrun.common.data.annotations.SQLiteField;

/**
 *
 * @author Steps
 */
@DomainClass(table = "drugs")
public class Drug extends BaseGear {
    
    @SQLiteField(maps = "vector")
    private String vector;
    private String duration;
    private String addiction;
    private String effects;

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAddiction() {
        return addiction;
    }

    public void setAddiction(String addiction) {
        this.addiction = addiction;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }
    
    
}
