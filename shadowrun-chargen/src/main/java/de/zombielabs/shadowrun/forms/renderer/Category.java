/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.renderer;

/**
 *
 * @author Steps
 */
public class Category {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Category(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() { return this.name; }
}
