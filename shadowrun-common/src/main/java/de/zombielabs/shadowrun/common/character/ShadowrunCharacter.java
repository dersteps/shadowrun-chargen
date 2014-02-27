package de.zombielabs.shadowrun.common.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Steps
 */
public class ShadowrunCharacter {
    private String name;
    
    private Attributes attributes = new Attributes();
//    private HashMap<Attribute<Integer>, List<Skill>> skills = new HashMap<Attribute<Integer>, List<Skill>>();
    private List<Skill> skills = new ArrayList<Skill>();

    private Metatype metatype = Metatype.HUMAN;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }


    public Metatype getMetatype() {
        return metatype;
    }

    public void setMetatype(Metatype metatype) {
        this.metatype = metatype;
    }
    
    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
    
    public List<Skill> getSkills() {
        return this.skills;
    }
    
    public ShadowrunCharacter(Metatype metatype) {
        this.attributes = new Attributes();
    }
    
}
