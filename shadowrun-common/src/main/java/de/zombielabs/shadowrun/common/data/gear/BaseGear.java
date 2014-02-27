/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.data.gear;

import de.zombielabs.shadowrun.common.data.Availability;
import de.zombielabs.shadowrun.common.data.annotations.SQLiteField;

/**
 *
 * @author Steps
 */
public class BaseGear implements Gear {

    @SQLiteField(maps = "name")
    private String name;
    
    private Availability availability;
    private Float cost;
    private Float weight;
    private Integer rating;
    private Integer id;

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Availability getAvailability() {
        return this.availability;
    }

    @Override
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public Float getCost() {
        return this.cost;
    }

    @Override
    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public Float getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Override
    public Integer getRating() {
        return this.rating;
    }

    @Override
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
