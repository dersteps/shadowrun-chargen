/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.character;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steps
 */
public class Skill {
    private String name = null;
    private List<String> specializations = new ArrayList<String>();
    private int level = 0;
    private Attribute<Integer> parentAttribute;
    
    public Skill(String name, int level, Attribute<Integer> parent) {
        this.name = name;
        this.level = level;
        this.parentAttribute = parent;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Attribute<Integer> getParentAttribute() {
        return parentAttribute;
    }
    
    
}
