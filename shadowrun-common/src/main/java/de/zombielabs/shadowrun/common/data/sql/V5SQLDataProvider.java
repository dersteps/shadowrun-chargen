package de.zombielabs.shadowrun.common.data.sql;

import de.zombielabs.shadowrun.common.data.DataProvider;
import de.zombielabs.shadowrun.common.data.Priority;
import de.zombielabs.shadowrun.common.data.Attribute;
import de.zombielabs.shadowrun.common.data.Availability;
import de.zombielabs.shadowrun.common.data.Flaw;
import de.zombielabs.shadowrun.common.data.Metatype;
import de.zombielabs.shadowrun.common.data.Perk;
import de.zombielabs.shadowrun.common.data.Skill;
import de.zombielabs.shadowrun.common.data.Skillgroup;
import de.zombielabs.shadowrun.common.data.gear.Drug;
import de.zombielabs.shadowrun.common.data.gear.Toxin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author Steps
 */
public class V5SQLDataProvider extends DataProvider {
    private File db = null;
    private Connection connection = null;
    
    public V5SQLDataProvider(File f) {
        this.db = f;
    }
    
    @Override
    public void loadAll() {
        // We start
        this.onLoadingData();
        try {
            this.priorities = this.loadPriorities();
            this.attributes = this.loadAttributes();
            this.skills = this.loadSkills();
            this.skillgroups = this.loadSkillgroups();
            this.metatypes = this.loadMetatypes();
            this.perks = this.loadPerks();
            this.flaws = this.loadFlaws();
            this.toxins = this.loadToxins();
            this.drugs = this.loadDrugs();
            this.onLoadingFinished();
        } catch (Exception ex) {
            this.onLoadingFailed(ex);
        }
    }
    
    private List<Toxin> loadToxins() throws SQLException {
        final String query = "SELECT * FROM toxins";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        
        List<Toxin> list = new ArrayList<Toxin>();
        
        while(rs.next()) {
            Toxin t = new Toxin();
            t.setID(rs.getInt("id"));
            t.setName(rs.getString("name"));
            t.setAvailability(new Availability(rs.getInt("availability"), rs.getString("legality")));
            t.setCost(rs.getFloat("price"));
            t.setForce(rs.getInt("force"));
            t.setPenetration(rs.getInt("penetration"));
            t.setRating(null);
            t.setVector(rs.getString("vector"));
            t.setVelocity(rs.getString("velocity"));
            t.setWeight(null);
            list.add(t);
            this.onGearItemLoaded(t);
        }
        
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    private List<Drug> loadDrugs() throws SQLException {
        final String query = "SELECT * FROM drugs";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        
        List<Drug> list = new ArrayList<Drug>();
        
        while(rs.next()) {
            Drug t = new Drug();
            t.setID(rs.getInt("id"));
            t.setName(rs.getString("name"));
            t.setAvailability(new Availability(rs.getInt("availability"), rs.getString("legality")));
            t.setCost(rs.getFloat("price"));
            t.setAddiction(rs.getString("addiction"));
            t.setDuration(rs.getString("duration"));
            t.setRating(null);
            t.setVector(rs.getString("vector"));
            t.setEffects(rs.getString("effects"));
            t.setWeight(null);
            list.add(t);
            this.onGearItemLoaded(t);
        }
        
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    private List<Flaw> loadFlaws() throws SQLException {
        final String query = "SELECT * FROM flaws";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        
        List<Flaw> list = new ArrayList<Flaw>();
        
        while(rs.next()) {
            Flaw f = new Flaw();
            f.setId(rs.getInt("id"));
            f.setName(rs.getString("name"));
            f.setCost(rs.getInt("cost"));
            f.setDescription(rs.getString("description"));
            list.add(f);
            this.onFlawLoaded(f);
        }
        
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    private List<Perk> loadPerks() throws SQLException {
        final String query = "SELECT * FROM perks";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        
        List<Perk> list = new ArrayList<Perk>();
        
        while(rs.next()) {
            Perk p = new Perk();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setCost(rs.getInt("cost"));
            p.setDescription(rs.getString("description"));
            list.add(p);
            this.onPerkLoaded(p);
        }
        
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    private List<Metatype> loadMetatypes() throws SQLException {
        final String query = "SELECT * FROM metatypes";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        List<Metatype> list = new ArrayList<>();
        
        while(rs.next()) {
            Metatype m = new Metatype();
            m.setId(rs.getInt("id"));
            m.setName(rs.getString("name"));
            m.setBodyDefault(rs.getInt("kon_default"));
            m.setBodyMax(rs.getInt("kon_max"));
            m.setDexterityDefault(rs.getInt("ges_default"));
            m.setDexterityMax(rs.getInt("ges_max"));
            m.setReactionDefault(rs.getInt("rea_default"));
            m.setReactionMax(rs.getInt("rea_max"));
            m.setStrengthDefault(rs.getInt("str_default"));
            m.setStrengthMax(rs.getInt("str_max"));
            m.setWillpowerDefault(rs.getInt("wil_default"));
            m.setWillpowerMax(rs.getInt("wil_max"));
            m.setIntuitionDefault(rs.getInt("int_default"));
            m.setIntuitionMax(rs.getInt("int_max"));
            m.setLogicDefault(rs.getInt("log_default"));
            m.setLogicMax(rs.getInt("log_max"));
            m.setCharismaDefault(rs.getInt("cha_default"));
            m.setCharismaMax(rs.getInt("cha_max"));
            m.setEdgeDefault(rs.getInt("edg_default"));
            m.setEdgeMax(rs.getInt("edg_max"));
            m.setEssenceDefault(rs.getFloat("ess_default"));
            m.setEssenceMax(rs.getFloat("ess_max"));
            m.setMagicDefault(rs.getInt("mag_default"));
            m.setMagicMax(rs.getInt("mag_max"));
            m.setResonanceDefault(rs.getInt("res_default"));
            m.setResonanceMax(rs.getInt("res_max"));
            m.setBonus(rs.getString("bonus"));
            list.add(m);
            this.onMetatypeLoaded(m);
        }
        
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    private List<Skillgroup> loadSkillgroups() throws SQLException {
        final String query = "select skillgroups.id, skillgroups.name as sg_name, skills.name as s_name from skillgroups inner join skills on skills.group_id = skillgroups.id;";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        List<Skillgroup> list = new ArrayList<>();
        HashMap<String, List<String>> temp = new HashMap<String, List<String>>();
        
        while(rs.next()) {
            if(!temp.containsKey(rs.getString("sg_name"))) {
                temp.put(rs.getString("sg_name"), new ArrayList<String>());
            }
            
            List<String> l = temp.get(rs.getString("sg_name"));
            
            l.add(rs.getString("s_name"));
            temp.put(rs.getString("sg_name"), l);
        }
        
        for(final Entry<String, List<String>> entry : temp.entrySet()) {
            Skillgroup sg = new Skillgroup();
            sg.setName(entry.getKey());
            
            for(final String sname : entry.getValue()) {
                for(final Skill sk : this.skills) {
                    if(sk.getName().equalsIgnoreCase(sname)) {
                        sg.addSkill(sk);
                        break;
                    }
                }
            }
            list.add(sg);
            this.onSkillGroupLoaded(sg);
        }
        
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    private List<Skill> loadSkills() throws SQLException {
        final String query = "SELECT * FROM skills ORDER BY name ASC";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        List<Skill> list = new ArrayList<>();
        
        while(rs.next()) {
            Skill sk = new Skill();
            sk.setGroupID(rs.getInt("group_id"));
            sk.setName(rs.getString("name"));
            if(this.attributes != null) {
                for(final Attribute at : this.attributes) {
                    if(at.getId().intValue() == rs.getInt("parent_attribute")) {
                        sk.setParent(at);
                        break;
                    }
                }
            }
            
            list.add(sk);
            this.onSkillLoaded(sk);
        }
        
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    private List<Attribute> loadAttributes() throws SQLException {
        final String query = "SELECT * FROM attributes ORDER BY id ASC";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        List<Attribute> list = new ArrayList<>();
        
        while(rs.next()) {
            Attribute a = new Attribute();
            a.setName(rs.getString("name"));
            a.setShortname(rs.getString("shortname"));
            a.setSpecial(rs.getBoolean("special"));
            a.setCanBuy(rs.getBoolean("canbuy"));
            a.setId(rs.getInt("id"));
            list.add(a);
            this.onAttributeLoaded(a);
        }
                
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    @Override
    public boolean update(String sql) throws SQLException {
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final int result = s.executeUpdate(sql);
        System.out.println("Query result: " + result);
        
        this.connection.close();
        this.connection = null;
        return result != 0;
    }
    
    private List<Priority> loadPriorities() throws SQLException {
        final String query = "SELECT * FROM priorities ORDER BY name ASC";
        this.ensureConnection();
        Statement s = this.connection.createStatement();
        final ResultSet rs = s.executeQuery(query);
        
        List<Priority> list = new ArrayList<>();
        
        while(rs.next()) {
            Priority p = new Priority();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            
            p.setHumanPossible(rs.getBoolean("human_possible"));
            p.setElfPossible(rs.getBoolean("elf_possible"));
            p.setOrkPossible(rs.getBoolean("orc_possible"));
            p.setDwarfPossible(rs.getBoolean("dwarf_possible"));
            p.setTrollPossible(rs.getBoolean("troll_possible"));
            
            p.setSpecialHuman(rs.getInt("special_human"));
            p.setSpecialElf(rs.getInt("special_elf"));
            p.setSpecialOrk(rs.getInt("special_ork"));
            p.setSpecialDwarf(rs.getInt("special_dwarf"));
            p.setSpecialTroll(rs.getInt("special_troll"));
            
            p.setAttributes(rs.getInt("attributes"));
            p.setSkillpoints(rs.getInt("skills"));
            p.setSkillgroupPoints(rs.getInt("skillgroups"));
            p.setResources(rs.getInt("resources"));
            
            
            p.setMagePossible(rs.getBoolean("mage_possible"));
            p.setAdeptPossible(rs.getBoolean("adept_possible"));
            p.setMageAdeptPossible(rs.getBoolean("mage_adept_possible"));
            p.setAspectMagePossible(rs.getBoolean("aspect_mage_possible"));
            p.setTechnomancerPossible(rs.getBoolean("technomancer_possible"));
            
            p.setMagicMage(rs.getInt("magic_mage"));
            p.setMagicAdept(rs.getInt("magic_adept"));
            p.setMagicMageAdept(rs.getInt("magic_mage_adept"));
            p.setMagicAspectMage(rs.getInt("magic_aspect_mage"));
            p.setMagicTechnomancer(rs.getInt("magic_technomancer"));
            
            p.setResonanceMage(rs.getInt("resonance_mage"));
            p.setResonanceAdept(rs.getInt("resonance_adept"));
            p.setResonanceMageAdept(rs.getInt("resonance_mage_adept"));
            p.setResonanceAspectMage(rs.getInt("resonance_aspect_mage"));
            p.setResonanceTechnomancer(rs.getInt("resonance_technomancer"));
            
            p.setMagicSkillsMage(rs.getInt("magic_skills_mage"));
            p.setMagicSkillsAdept(rs.getInt("magic_skills_adept"));
            p.setMagicSkillsMageAdept(rs.getInt("magic_skills_mage_adept"));
            p.setMagicSkillsAspectMage(rs.getInt("magic_skills_aspect_mage"));
            p.setMagicSkillsTechnomancer(rs.getInt("magic_skills_technomancer"));
            
            p.setRatingMagicSkillsMage(rs.getInt("rating_magic_skills_mage"));
            p.setRatingMagicSkillsAdept(rs.getInt("rating_magic_skills_adept"));
            p.setRatingMagicSkillsMageAdept(rs.getInt("rating_magic_skills_mage_adept"));
            p.setRatingMagicSkillsAspectMage(rs.getInt("rating_magic_skills_aspect_mage"));
            p.setRatingMagicSkillsTechnomancer(rs.getInt("rating_magic_skills_technomancer"));
            
            p.setActiveSkillsMage(rs.getInt("active_skills_mage"));
            p.setActiveSkillsAdept(rs.getInt("active_skills_adept"));
            p.setActiveSkillsMageAdept(rs.getInt("active_skills_mage_adept"));
            p.setActiveSkillsAspectMage(rs.getInt("active_skills_aspect_mage"));
            p.setActiveSkillsTechnomancer(rs.getInt("active_skills_technomancer"));
            
            p.setRatingActiveSkillsMage(rs.getInt("rating_active_skills_mage"));
            p.setRatingActiveSkillsAdept(rs.getInt("rating_active_skills_adept"));
            p.setRatingActiveSkillsMageAdept(rs.getInt("rating_active_skills_mage_adept"));
            p.setRatingActiveSkillsAspectMage(rs.getInt("rating_active_skills_aspect_mage"));
            p.setRatingActiveSkillsTechnomancer(rs.getInt("rating_active_skills_technomancer"));
            
            p.setMagicSkillgroupsMage(rs.getInt("magic_skillgroups_mage"));
            p.setMagicSkillgroupsAdept(rs.getInt("magic_skillgroups_adept"));
            p.setMagicSkillgroupsMageAdept(rs.getInt("magic_skillgroups_mage_adept"));
            p.setMagicSkillgroupsAspectMage(rs.getInt("magic_skillgroups_aspect_mage"));
            p.setMagicSkillgroupsTechnomancer(rs.getInt("magic_skillgroups_technomancer"));
            
            p.setRatingMagicSkillgroupsMage(rs.getInt("rating_magic_skillgroups_mage"));
            p.setRatingMagicSkillgroupsAdept(rs.getInt("rating_magic_skillgroups_adept"));
            p.setRatingMagicSkillgroupsMageAdept(rs.getInt("rating_magic_skillgroups_mage_adept"));
            p.setRatingMagicSkillgroupsAspectMage(rs.getInt("rating_magic_skillgroups_aspect_mage"));
            p.setRatingMagicSkillgroupsTechnomancer(rs.getInt("rating_magic_skillgroups_technomancer"));
            
            p.setResonanceSkillsMage(rs.getInt("resonance_skills_mage"));
            p.setResonanceSkillsAdept(rs.getInt("resonance_skills_adept"));
            p.setResonanceSkillsMageAdept(rs.getInt("resonance_skills_mage_adept"));
            p.setResonanceSkillsAspectMage(rs.getInt("resonance_skills_aspect_mage"));
            p.setResonanceSkillsTechnomancer(rs.getInt("resonance_skills_technomancer"));
            
            p.setRatingResonanceSkillsMage(rs.getInt("rating_resonance_skills_mage"));
            p.setRatingResonanceSkillsAdept(rs.getInt("rating_resonance_skills_adept"));
            p.setRatingResonanceSkillsMageAdept(rs.getInt("rating_resonance_skills_mage_adept"));
            p.setRatingResonanceSkillsAspectMage(rs.getInt("rating_resonance_skills_aspect_mage"));
            p.setRatingResonanceSkillsTechnomancer(rs.getInt("rating_resonance_skills_technomancer"));
            
            p.setSpellsMage(rs.getInt("spells_mage"));
            p.setSpellsAdept(rs.getInt("spells_adept"));
            p.setSpellsMageAdept(rs.getInt("spells_mage_adept"));
            p.setSpellsAspectMage(rs.getInt("spells_aspect_mage"));
            p.setSpellsTechnomancer(rs.getInt("spells_technomancer"));
            
            p.setFormsMage(rs.getInt("forms_mage"));
            p.setFormsAdept(rs.getInt("forms_adept"));
            p.setFormsMageAdept(rs.getInt("forms_mage_adept"));
            p.setFormsAspectMage(rs.getInt("forms_aspect_mage"));
            p.setFormsTechnomancer(rs.getInt("forms_technomancer"));
            
            System.out.println("Priority loaded: " + p.getName());
            this.onPriorityLoaded(p);
            list.add(p);
        }
        
        rs.close();
        this.connection.close();
        this.connection = null;
        return list;
    }
    
    private void ensureConnection() throws SQLException {
        if(this.connection == null) {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + db.getAbsolutePath());
        }
    }
    
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.err.println("Unable to access SQLite, exiting");
            System.exit(666);
        }
    }

    @Override
    public void reloadAttributes() throws SQLException {
        this.attributes = this.loadAttributes();
    }

    @Override
    public void reloadFlaws() throws SQLException {
        this.flaws = this.loadFlaws();
    }

    @Override
    public void reloadPerks() throws SQLException {
        this.perks = this.loadPerks();
    }

    @Override
    public void reloadToxins() throws SQLException {
        this.toxins = loadToxins();
    }

    @Override
    public void reloadMetatypes() throws SQLException {
        this.metatypes = this.loadMetatypes();
    }

    @Override
    public void reloadPriorities() throws SQLException {
        this.priorities = this.loadPriorities();
    }

    @Override
    public void reloadSkills() throws SQLException {
        this.skills = this.loadSkills();
    }

    @Override
    public void reloadSkillgroups() throws SQLException {
        this.skillgroups = this.loadSkillgroups();
    }

    @Override
    public void reloadDrugs() throws SQLException {
        this.drugs = this.loadDrugs();
    }

    

    
    
}
