/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steps
 */
public class Skillgroup {
    private String name;
    private List<Skill> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
    
    public void addSkill(Skill skill) {
        if(this.skills == null) {
            this.skills = new ArrayList<>();
        }
        this.skills.add(skill);
    }
    
}
