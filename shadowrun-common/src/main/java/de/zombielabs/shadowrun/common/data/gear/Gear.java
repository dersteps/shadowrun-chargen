/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.data.gear;

import de.zombielabs.shadowrun.common.data.Availability;

/**
 *
 * @author Steps
 */
public interface Gear {
    String getName();
    void setName(String name);
    Availability getAvailability();
    void setAvailability(Availability availability);
    Float getCost();
    void setCost(Float cost);
    Float getWeight();
    void setWeight(Float weight);
    Integer getRating();
    void setRating(Integer rating);
    Integer getID();
    void setID(Integer id);
    
}
