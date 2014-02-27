package de.zombielabs.shadowrun.common.character;

import de.zombielabs.shadowrun.common.resources.Keys;
import java.util.ResourceBundle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Steps
 */
public class Attributes {
    
    private Attribute<Integer> body;
    private Attribute<Integer> dexterity;
    private Attribute<Integer> strength;
    private Attribute<Integer> reaction;
    private Attribute<Integer> logic;
    private Attribute<Integer> intuition;
    private Attribute<Integer> charisma;
    private Attribute<Integer> willpower;
    private Attribute<Integer> edge;
    private Attribute<Integer> magic;
    private Attribute<Integer> resonance;
    private Attribute<Float> essence;
    
    private ResourceBundle bundle = ResourceBundle.getBundle("de/zombielabs/shadowrun/common/character/Attributes");

    public Attribute<Integer> getBody() {
        return body;
    }

    public void setBody(Attribute<Integer> body) {
        this.body = body;
    }

    public Attribute<Integer> getDexterity() {
        return dexterity;
    }

    public void setDexterity(Attribute<Integer> dexterity) {
        this.dexterity = dexterity;
    }

    public Attribute<Integer> getStrength() {
        return strength;
    }

    public void setStrength(Attribute<Integer> strength) {
        this.strength = strength;
    }

    public Attribute<Integer> getReaction() {
        return reaction;
    }

    public void setReaction(Attribute<Integer> reaction) {
        this.reaction = reaction;
    }

    public Attribute<Integer> getLogic() {
        return logic;
    }

    public void setLogic(Attribute<Integer> logic) {
        this.logic = logic;
    }

    public Attribute<Integer> getIntuition() {
        return intuition;
    }

    public void setIntuition(Attribute<Integer> intuition) {
        this.intuition = intuition;
    }

    public Attribute<Integer> getCharisma() {
        return charisma;
    }

    public void setCharisma(Attribute<Integer> charisma) {
        this.charisma = charisma;
    }

    public Attribute<Integer> getWillpower() {
        return willpower;
    }

    public void setWillpower(Attribute<Integer> willpower) {
        this.willpower = willpower;
    }

    public Attribute<Integer> getEdge() {
        return edge;
    }

    public void setEdge(Attribute<Integer> edge) {
        this.edge = edge;
    }

    public Attribute<Integer> getMagic() {
        return magic;
    }

    public void setMagic(Attribute<Integer> magic) {
        this.magic = magic;
    }

    public Attribute<Integer> getResonance() {
        return resonance;
    }

    public void setResonance(Attribute<Integer> resonance) {
        this.resonance = resonance;
    }

    public Attribute<Float> getEssence() {
        return essence;
    }

    public void setEssence(Attribute<Float> essence) {
        this.essence = essence;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    
    
    protected Attributes() {
        this.body = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_BODY), 1, 6, 0);
        this.charisma = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_CHARISMA), 1, 6, 0);
        this.dexterity = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_DEXTERITY), 1, 6, 0);
        this.edge = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_EDGE), 1, 6, 0);
        this.essence = new Attribute<Float>(bundle.getString(Keys.KEY_ATTR_ESSENCE), 1.0f, 6.0f, 0f);
        this.intuition = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_INTUITION), 1, 6, 0);
        this.logic = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_LOGIC), 1, 6, 0);
        this.magic = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_MAGIC), 0, 6, 0);
        this.reaction = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_REACTION), 1, 6, 0);
        this.resonance = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_RESONANCE), 0, 6, 0);
        this.strength = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_STRENGTH), 1, 6, 0);
        this.willpower = new Attribute<Integer>(bundle.getString(Keys.KEY_ATTR_WILLPOWER), 1, 6, 0);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(bundle.getString("name.body")).append(": ").append(body).append("\n");
        sb.append(bundle.getString("name.strength")).append(": ").append(strength).append("\n");
        sb.append(bundle.getString("name.reaction")).append(": ").append(reaction).append("\n");
        sb.append(bundle.getString("name.dexterity")).append(": ").append(dexterity).append("\n");
        sb.append(bundle.getString("name.logic")).append(": ").append(logic).append("\n");
        sb.append(bundle.getString("name.intuition")).append(": ").append(intuition).append("\n");
        sb.append(bundle.getString("name.willpower")).append(": ").append(willpower).append("\n");
        sb.append(bundle.getString("name.charisma")).append(": ").append(charisma).append("\n");
        sb.append(bundle.getString("name.edge")).append(": ").append(edge).append("\n");
        sb.append(bundle.getString("name.essence")).append(": ").append(essence).append("\n");
        sb.append(bundle.getString("name.magic")).append(": ").append(magic).append("\n");
        sb.append(bundle.getString("name.resonance")).append(": ").append(resonance).append("\n");
        
        return sb.toString();
    }
}
