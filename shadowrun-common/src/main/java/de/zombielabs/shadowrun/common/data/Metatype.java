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
public class Metatype {
    private String name;
    
    private int id;
   
    private int bodyDefault;
    private int bodyMax;
    
    private int dexterityDefault;
    private int dexterityMax;
    
    private int reactionDefault;
    private int reactionMax;
    
    private int strengthDefault;
    private int strengthMax;
    
    private int willpowerDefault;
    private int willpowerMax;
    
    private int logicDefault;
    private int logicMax;
    
    private int intuitionDefault;
    private int intuitionMax;
    
    private int charismaDefault;
    private int charismaMax;
    
    private int edgeDefault;
    private int edgeMax;
    
    private float essenceDefault;
    private float essenceMax;
    
    private int magicDefault;
    private int magicMax;
    
    private int resonanceDefault;
    private int resonanceMax;
    
    private String bonus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBodyDefault() {
        return bodyDefault;
    }

    public void setBodyDefault(int bodyDefault) {
        this.bodyDefault = bodyDefault;
    }

    public int getBodyMax() {
        return bodyMax;
    }

    public void setBodyMax(int bodyMax) {
        this.bodyMax = bodyMax;
    }

    public int getDexterityDefault() {
        return dexterityDefault;
    }

    public void setDexterityDefault(int dexterityDefault) {
        this.dexterityDefault = dexterityDefault;
    }

    public int getDexterityMax() {
        return dexterityMax;
    }

    public void setDexterityMax(int dexterityMax) {
        this.dexterityMax = dexterityMax;
    }

    public int getReactionDefault() {
        return reactionDefault;
    }

    public void setReactionDefault(int reactionDefault) {
        this.reactionDefault = reactionDefault;
    }

    public int getReactionMax() {
        return reactionMax;
    }

    public void setReactionMax(int reactionMax) {
        this.reactionMax = reactionMax;
    }

    public int getStrengthDefault() {
        return strengthDefault;
    }

    public void setStrengthDefault(int strengthDefault) {
        this.strengthDefault = strengthDefault;
    }

    public int getStrengthMax() {
        return strengthMax;
    }

    public void setStrengthMax(int strengthMax) {
        this.strengthMax = strengthMax;
    }

    public int getWillpowerDefault() {
        return willpowerDefault;
    }

    public void setWillpowerDefault(int willpowerDefault) {
        this.willpowerDefault = willpowerDefault;
    }

    public int getWillpowerMax() {
        return willpowerMax;
    }

    public void setWillpowerMax(int willpowerMax) {
        this.willpowerMax = willpowerMax;
    }

    public int getLogicDefault() {
        return logicDefault;
    }

    public void setLogicDefault(int logicDefault) {
        this.logicDefault = logicDefault;
    }

    public int getLogicMax() {
        return logicMax;
    }

    public void setLogicMax(int logicMax) {
        this.logicMax = logicMax;
    }

    public int getIntuitionDefault() {
        return intuitionDefault;
    }

    public void setIntuitionDefault(int intuitionDefault) {
        this.intuitionDefault = intuitionDefault;
    }

    public int getIntuitionMax() {
        return intuitionMax;
    }

    public void setIntuitionMax(int intuitionMax) {
        this.intuitionMax = intuitionMax;
    }

    public int getCharismaDefault() {
        return charismaDefault;
    }

    public void setCharismaDefault(int charismaDefault) {
        this.charismaDefault = charismaDefault;
    }

    public int getCharismaMax() {
        return charismaMax;
    }

    public void setCharismaMax(int charismaMax) {
        this.charismaMax = charismaMax;
    }

    public int getEdgeDefault() {
        return edgeDefault;
    }

    public void setEdgeDefault(int edgeDefault) {
        this.edgeDefault = edgeDefault;
    }

    public int getEdgeMax() {
        return edgeMax;
    }

    public void setEdgeMax(int edgeMax) {
        this.edgeMax = edgeMax;
    }

    public float getEssenceDefault() {
        return essenceDefault;
    }

    public void setEssenceDefault(float essenceDefault) {
        this.essenceDefault = essenceDefault;
    }

    public float getEssenceMax() {
        return essenceMax;
    }

    public void setEssenceMax(float essenceMax) {
        this.essenceMax = essenceMax;
    }

    public int getMagicDefault() {
        return magicDefault;
    }

    public void setMagicDefault(int magicDefault) {
        this.magicDefault = magicDefault;
    }

    public int getMagicMax() {
        return magicMax;
    }

    public void setMagicMax(int magicMax) {
        this.magicMax = magicMax;
    }

    public int getResonanceDefault() {
        return resonanceDefault;
    }

    public void setResonanceDefault(int resonanceDefault) {
        this.resonanceDefault = resonanceDefault;
    }

    public int getResonanceMax() {
        return resonanceMax;
    }

    public void setResonanceMax(int resonanceMax) {
        this.resonanceMax = resonanceMax;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
