/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.character;

/**
 *
 * @author Steps
 */
public class Attribute<T> {
    private String name = null;
    private T value = null;
    private T maximum = null;
    private T modifiers = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getMaximum() {
        return maximum;
    }

    public void setMaximum(T maximum) {
        this.maximum = maximum;
    }

    public T getModifiers() {
        return modifiers;
    }

    public void setModifiers(T modifiers) {
        this.modifiers = modifiers;
    }
    
    public Attribute(String name, T value, T maximum, T modifiers) {
        this.name = name;
        this.value = value;
        this.maximum = maximum;
        this.modifiers = modifiers;
    }
    
    public static Attribute empty() {
        return new Attribute(null, null, null, null);
    }
    
    @Override
    public String toString() {
        return this.name + " " + this.value; 
    }
    
}
