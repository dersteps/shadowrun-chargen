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
public class Rules {
    private int defaultKarma;
    
    private float knowledgeMultiplier;
    
    private float languageMultiplier;

    public int getDefaultKarma() {
        return defaultKarma;
    }

    public void setDefaultKarma(int defaultKarma) {
        this.defaultKarma = defaultKarma;
    }

    public float getKnowledgeMultiplier() {
        return knowledgeMultiplier;
    }

    public void setKnowledgeMultiplier(float knowledgeMultiplier) {
        this.knowledgeMultiplier = knowledgeMultiplier;
    }

    public float getLanguageMultiplier() {
        return languageMultiplier;
    }

    public void setLanguageMultiplier(float languageMultiplier) {
        this.languageMultiplier = languageMultiplier;
    }
 
    
    
}
