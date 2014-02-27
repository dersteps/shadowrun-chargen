/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.data;

/**
 *
 * @author Steps
 */
public class Availability {
    private Integer availability;
    private String legality;

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getLegality() {
        return legality;
    }

    public void setLegality(String legality) {
        this.legality = legality;
    }
    
    public Availability(int availability, String legality) {
        this.availability = availability;
        this.legality = legality;
    }
    
    @Override
    public String toString() {
        if(this.availability == null || this.legality == null) {
            return "-";
        }
        return this.availability + this.legality;
    }
    
}
