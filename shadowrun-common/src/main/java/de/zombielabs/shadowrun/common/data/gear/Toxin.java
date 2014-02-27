/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.common.data.gear;

/**
 *
 * @author Steps
 */
public class Toxin extends BaseGear {
    private String vector;
    private String velocity;
    private Integer penetration;
    private Integer force;

    public String getVector() {
        return vector;
    }

    public void setVector(String vector) {
        this.vector = vector;
    }

    public String getVelocity() {
        return velocity;
    }

    public void setVelocity(String velocity) {
        this.velocity = velocity;
    }

    public Integer getPenetration() {
        return penetration;
    }

    public void setPenetration(Integer penetration) {
        this.penetration = penetration;
    }

    public Integer getForce() {
        return force;
    }

    public void setForce(Integer force) {
        this.force = force;
    }
}
