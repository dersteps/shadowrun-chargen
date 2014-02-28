/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.db;

import de.zombielabs.shadowrun.common.data.DataProvider;
import de.zombielabs.shadowrun.common.data.Priority;
import de.zombielabs.shadowrun.forms.ZombieDialog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Steps
 */
public class PrioritiesEditor extends ZombieDialog {

    private List<Priority> dbPriorities = new ArrayList<Priority>();
    
    private Priority edited = null;
    private boolean update = false;
    
    /**
     * Creates new form PrioritiesEditor
     */
    public PrioritiesEditor(java.awt.Frame parent, boolean modal, DataProvider data) {
        super(parent, modal, data);
        initComponents();
        this.dbPriorities = data.getPriorities();
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(final Priority p : this.dbPriorities) {
            model.addElement(p);
        }
        this.cboPriority.setModel(model);
        
    }
    
    private int checkInt(JCheckBox box) {
        return box.isSelected() ? 1 : 0;
    }

    private void update() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("UPDATE priorities SET ");
        sb.append("name = '").append(this.txtName.getText()).append("', ");
        sb.append("human_possible = ").append(this.checkInt(this.chkHumanPossible)).append(", ");
        sb.append("elf_possible = ").append(this.checkInt(this.chkElfPossible)).append(", ");
        sb.append("orc_possible = ").append(this.checkInt(this.chkOrkPossible)).append(", ");
        sb.append("dwarf_possible = ").append(this.checkInt(this.chkDwarfPossible)).append(", ");
        sb.append("troll_possible = ").append(this.checkInt(this.chkTrollPossible)).append(", ");
        
        sb.append("special_human = ").append(this.txtHumanSpecial.getValue()).append(", ");
        sb.append("special_elf = ").append(this.txtElfSpecial.getValue()).append(", ");
        sb.append("special_ork = ").append(this.txtOrcSpecial.getValue()).append(", ");
        sb.append("special_dwarf = ").append(this.txtDwarfSpecial.getValue()).append(", ");
        sb.append("special_troll = ").append(this.txtTrollSpecial.getValue()).append(", ");
        
        sb.append("attributes = ").append(this.txtAttributes.getValue()).append(", ");
        sb.append("skills = ").append(this.txtSkills.getValue()).append(", ");
        sb.append("skillgroups = ").append(this.txtSkillgroups.getValue()).append(", ");
        sb.append("resources = ").append(this.txtResources.getValue()).append(", ");
        
        sb.append("mage_possible = ").append(this.checkInt(this.chkMagePossible)).append(", ");
        sb.append("adept_possible = ").append(this.checkInt(this.chkAdeptPossible)).append(", ");
        sb.append("mage_adept_possible = ").append(this.checkInt(this.chkMageAdeptPossible)).append(", ");
        sb.append("aspect_mage_possible = ").append(this.checkInt(this.chkAspectMagePossible)).append(", ");
        sb.append("technomancer_possible = ").append(this.checkInt(this.chkTechnomancerPossible)).append(", ");
        
        sb.append("magic_mage = ").append(this.txtMAGMage.getValue()).append(", ");
        sb.append("magic_adept = ").append(this.txtMAGAdept.getValue()).append(", ");
        sb.append("magic_mage_adept = ").append(this.txtMAGMageAdept.getValue()).append(", ");
        sb.append("magic_aspect_mage = ").append(this.txtMAGAspectMage.getValue()).append(", ");
        sb.append("magic_technomancer = ").append(this.txtMAGTechnomancer.getValue()).append(", ");
        
        sb.append("resonance_mage = ").append(this.txtRESMage.getValue()).append(", ");
        sb.append("resonance_adept = ").append(this.txtRESAdept.getValue()).append(", ");
        sb.append("resonance_mage_adept = ").append(this.txtRESMageAdept.getValue()).append(", ");
        sb.append("resonance_aspect_mage = ").append(this.txtRESAspectMage.getValue()).append(", ");
        sb.append("resonance_technomancer = ").append(this.txtRESTechnomancer.getValue()).append(", ");
        
        sb.append("magic_skills_mage = ").append(this.txtMAGSkillsMage.getValue()).append(", ");
        sb.append("magic_skills_adept = ").append(this.txtMAGSkillsAdept.getValue()).append(", ");
        sb.append("magic_skills_mage_adept = ").append(this.txtMAGSkillsMageAdept.getValue()).append(", ");
        sb.append("magic_skills_aspect_mage = ").append(this.txtMAGSkillsAspectMage.getValue()).append(", ");
        sb.append("magic_skills_technomancer = ").append(this.txtMAGSkillsTechnomancer.getValue()).append(", ");
        
        sb.append("rating_magic_skills_mage = ").append(this.txtMAGSkillsRatingMage.getValue()).append(", ");
        sb.append("rating_magic_skills_adept = ").append(this.txtMAGSkillsRatingAdept.getValue()).append(", ");
        sb.append("rating_magic_skills_mage_adept = ").append(this.txtMAGSkillsRatingMageAdept.getValue()).append(", ");
        sb.append("rating_magic_skills_aspect_mage = ").append(this.txtMAGSkillsRatingAspectMage.getValue()).append(", ");
        sb.append("rating_magic_skills_technomancer = ").append(this.txtMAGSkillsRatingTechnomancer.getValue()).append(", ");
        
        sb.append("active_skills_mage = ").append(this.txtSkillsMage.getValue()).append(", ");
        sb.append("active_skills_adept = ").append(this.txtSkillsAdept.getValue()).append(", ");
        sb.append("active_skills_mage_adept = ").append(this.txtSkillsMageAdept.getValue()).append(", ");
        sb.append("active_skills_aspect_mage = ").append(this.txtSkillsAspectMage.getValue()).append(", ");
        sb.append("active_skills_technomancer = ").append(this.txtSkillsTechnomancer.getValue()).append(", ");
        
        sb.append("rating_active_skills_mage = ").append(this.txtSkillRatingMage.getValue()).append(", ");
        sb.append("rating_active_skills_adept = ").append(this.txtSkillRatingAdept.getValue()).append(", ");
        sb.append("rating_active_skills_mage_adept = ").append(this.txtSkillRatingMageAdept.getValue()).append(", ");
        sb.append("rating_active_skills_aspect_mage = ").append(this.txtSkillRatingAspectMage.getValue()).append(", ");
        sb.append("rating_active_skills_technomancer = ").append(this.txtSkillRatingTechnomancer.getValue()).append(", ");
        
        sb.append("magic_skillgroups_mage = ").append(this.txtMAGSkillGroupsMage.getValue()).append(", ");
        sb.append("magic_skillgroups_adept = ").append(this.txtMAGSkillGroupsAdept.getValue()).append(", ");
        sb.append("magic_skillgroups_mage_adept = ").append(this.txtMAGSkillGroupsMageAdept.getValue()).append(", ");
        sb.append("magic_skillgroups_aspect_mage = ").append(this.txtMAGSkillGroupsAspectMage.getValue()).append(", ");
        sb.append("magic_skillgroups_technomancer = ").append(this.txtMAGSkillGroupsTechnomancer.getValue()).append(", ");
        
        sb.append("rating_magic_skillgroups_mage = ").append(this.txtMAGSkillGroupsRatingMage.getValue()).append(", ");
        sb.append("rating_magic_skillgroups_adept = ").append(this.txtMAGSkillGroupsRatingAdept.getValue()).append(", ");
        sb.append("rating_magic_skillgroups_mage_adept = ").append(this.txtMAGSkillGroupsRatingMageAdept.getValue()).append(", ");
        sb.append("rating_magic_skillgroups_aspect_mage = ").append(this.txtMAGSkillGroupsRatingAspectMage.getValue()).append(", ");
        sb.append("rating_magic_skillgroups_technomancer = ").append(this.txtMAGSkillGroupsRatingTechnomancer.getValue()).append(", ");
    
        sb.append("resonance_skills_mage = ").append(this.txtRESSkillsMage.getValue()).append(", ");
        sb.append("resonance_skills_adept = ").append(this.txtRESSkillsAdept.getValue()).append(", ");
        sb.append("resonance_skills_mage_adept = ").append(this.txtRESSkillsMageAdept.getValue()).append(", ");
        sb.append("resonance_skills_aspect_mage = ").append(this.txtRESSkillsAspectMage.getValue()).append(", ");
        sb.append("resonance_skills_technomancer = ").append(this.txtRESSkillsTechnomancer.getValue()).append(", ");
        
        sb.append("rating_resonance_skills_mage = ").append(this.txtRESSkillsRatingMage.getValue()).append(", ");
        sb.append("rating_resonance_skills_adept = ").append(this.txtRESSkillsRatingAdept.getValue()).append(", ");
        sb.append("rating_resonance_skills_mage_adept = ").append(this.txtRESSkillsRatingMageAdept.getValue()).append(", ");
        sb.append("rating_resonance_skills_aspect_mage = ").append(this.txtRESSkillsRatingAspectMage.getValue()).append(", ");
        sb.append("rating_resonance_skills_technomancer = ").append(this.txtRESSkillsRatingTechnomancer.getValue()).append(", ");
    
        sb.append("spells_mage = ").append(this.txtSpellsMage.getValue()).append(", ");
        sb.append("spells_adept = ").append(this.txtSpellsAdept.getValue()).append(", ");
        sb.append("spells_mage_adept = ").append(this.txtSpellsMageAdept.getValue()).append(", ");
        sb.append("spells_aspect_mage = ").append(this.txtSpellsAspectMage.getValue()).append(", ");
        sb.append("spells_technomancer = ").append(this.txtSpellsTechnomancer.getValue()).append(", ");
        
        sb.append("forms_mage = ").append(this.txtSpellsMage.getValue()).append(", ");
        sb.append("forms_adept = ").append(this.txtSpellsAdept.getValue()).append(", ");
        sb.append("forms_mage_adept = ").append(this.txtSpellsMageAdept.getValue()).append(", ");
        sb.append("forms_aspect_mage = ").append(this.txtSpellsAspectMage.getValue()).append(", ");
        sb.append("forms_technomancer = ").append(this.txtSpellsTechnomancer.getValue()).append(" ");
        sb.append("WHERE name = '").append(this.edited.getName()).append("'");
        
        System.out.println(sb.toString());
        try {
            boolean result = this.data.update(sb.toString());
            JOptionPane.showMessageDialog(this, "Result: " + result);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    private void saveAsNew() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO priorities(");
        sb.append("name, ");
        sb.append("human_possible, elf_possible, orc_possible, dwarf_possible, troll_possible, ");
        sb.append("special_human, special_elf, special_ork, special_dwarf, special_troll, ");
        sb.append("attributes, skills, skillgroups, resources, ");
        sb.append("mage_possible, adept_possible, mage_adept_possible, aspect_mage_possible, technomancer_possible, ");
        sb.append("magic_mage, magic_adept, magic_mage_adept, magic_aspect_mage,magic_technomancer, ");
        sb.append("resonance_mage, resonance_adept, resonance_mage_adept, resonance_aspect_mage, resonance_technomancer, ");
        sb.append("magic_skills_mage, magic_skills_adept, magic_skills_mage_adept, magic_skills_aspect_mage, magic_skills_technomancer, ");
        
        sb.append("rating_magic_skills_mage, rating_magic_skills_adept, rating_magic_skills_mage_adept, rating_magic_skills_aspect_mage, rating_magic_skills_technomancer, ");
        sb.append("active_skills_mage,active_skills_adept,active_skills_mage_adept,active_skills_aspect_mage,active_skills_technomancer,");
        sb.append("rating_active_skills_mage,rating_active_skills_adept,rating_active_skills_mage_adept,rating_active_skills_aspect_mage,rating_active_skills_technomancer,");
        sb.append("magic_skillgroups_mage,magic_skillgroups_adept,magic_skillgroups_mage_adept,magic_skillgroups_aspect_mage,magic_skillgroups_technomancer,");        
        sb.append("rating_magic_skillgroups_mage,rating_magic_skillgroups_adept,rating_magic_skillgroups_mage_adept,rating_magic_skillgroups_aspect_mage,rating_magic_skillgroups_technomancer,");
        sb.append("resonance_skills_mage,resonance_skills_adept,resonance_skills_mage_adept,resonance_skills_aspect_mage,resonance_skills_technomancer,");
        sb.append("rating_resonance_skills_mage,rating_resonance_skills_adept,rating_resonance_skills_mage_adept,rating_resonance_skills_aspect_mage,rating_resonance_skills_technomancer,");
        sb.append("spells_mage,spells_adept,spells_mage_adept,spells_aspect_mage,spells_technomancer,");
        sb.append("forms_mage,forms_adept,forms_mage_adept,forms_aspect_mage,forms_technomancer) ");
        sb.append("VALUES ('");
        
        sb.append(this.txtName.getText()).append("', ");
        sb.append(this.checkInt(this.chkHumanPossible)).append(", ");
        sb.append(this.checkInt(this.chkElfPossible)).append(", ");
        sb.append(this.checkInt(this.chkOrkPossible)).append(", ");
        sb.append(this.checkInt(this.chkDwarfPossible)).append(", ");
        sb.append(this.checkInt(this.chkTrollPossible)).append(", ");
        sb.append(this.txtHumanSpecial.getValue()).append(", ");
        sb.append(this.txtElfSpecial.getValue()).append(", ");
        sb.append(this.txtOrcSpecial.getValue()).append(", ");
        sb.append(this.txtDwarfSpecial.getValue()).append(", ");
        sb.append(this.txtTrollSpecial.getValue()).append(", ");
        sb.append(this.txtAttributes.getValue()).append(", ");
        sb.append(this.txtSkills.getValue()).append(", ");
        sb.append(this.txtSkillgroups.getValue()).append(", ");
        sb.append(this.txtResources.getValue()).append(", ");
        sb.append(this.checkInt(this.chkMagePossible)).append(", ");
        sb.append(this.checkInt(this.chkAdeptPossible)).append(", ");
        sb.append(this.checkInt(this.chkMageAdeptPossible)).append(", ");
        sb.append(this.checkInt(this.chkAspectMagePossible)).append(", ");
        sb.append(this.checkInt(this.chkTechnomancerPossible)).append(", ");
        sb.append(this.txtMAGMage.getValue()).append(", ");
        sb.append(this.txtMAGAdept.getValue()).append(", ");
        sb.append(this.txtMAGMageAdept.getValue()).append(", ");
        sb.append(this.txtMAGAspectMage.getValue()).append(", ");
        sb.append(this.txtMAGTechnomancer.getValue()).append(", ");
        sb.append(this.txtRESMage.getValue()).append(", ");
        sb.append(this.txtRESAdept.getValue()).append(", ");
        sb.append(this.txtRESMageAdept.getValue()).append(", ");
        sb.append(this.txtRESAspectMage.getValue()).append(", ");
        sb.append(this.txtRESTechnomancer.getValue()).append(", ");
        sb.append(this.txtMAGSkillsMage.getValue()).append(", ");
        sb.append(this.txtMAGSkillsAdept.getValue()).append(", ");
        sb.append(this.txtMAGSkillsMageAdept.getValue()).append(", ");
        sb.append(this.txtMAGSkillsAspectMage.getValue()).append(", ");
        sb.append(this.txtMAGSkillsTechnomancer.getValue()).append(", ");
        sb.append(this.txtMAGSkillsRatingMage.getValue()).append(", ");
        sb.append(this.txtMAGSkillsRatingAdept.getValue()).append(", ");
        sb.append(this.txtMAGSkillsRatingMageAdept.getValue()).append(", ");
        sb.append(this.txtMAGSkillsRatingAspectMage.getValue()).append(", ");
        sb.append(this.txtMAGSkillsRatingTechnomancer.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsMage.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsAdept.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsMageAdept.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsAspectMage.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsTechnomancer.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsRatingMage.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsRatingAdept.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsRatingMageAdept.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsRatingAspectMage.getValue()).append(", ");
        sb.append(this.txtMAGSkillGroupsRatingTechnomancer.getValue()).append(", ");
        sb.append(this.txtSkillsMage.getValue()).append(", ");
        sb.append(this.txtSkillsAdept.getValue()).append(", ");
        sb.append(this.txtSkillsMageAdept.getValue()).append(", ");
        sb.append(this.txtSkillsAspectMage.getValue()).append(", ");
        sb.append(this.txtSkillsTechnomancer.getValue()).append(", ");
        
        sb.append(this.txtSkillRatingMage.getValue()).append(", ");
        sb.append(this.txtSkillRatingAdept.getValue()).append(", ");
        sb.append(this.txtSkillRatingMageAdept.getValue()).append(", ");
        sb.append(this.txtSkillRatingAspectMage.getValue()).append(", ");
        sb.append(this.txtSkillRatingTechnomancer.getValue()).append(", ");
        
        sb.append(this.txtRESSkillsMage.getValue()).append(", ");
        sb.append(this.txtRESSkillsAdept.getValue()).append(", ");
        sb.append(this.txtRESSkillsMageAdept.getValue()).append(", ");
        sb.append(this.txtRESSkillsAspectMage.getValue()).append(", ");
        sb.append(this.txtRESSkillsTechnomancer.getValue()).append(", ");
        
        sb.append(this.txtRESSkillsRatingMage.getValue()).append(", ");
        sb.append(this.txtRESSkillsRatingAdept.getValue()).append(", ");
        sb.append(this.txtRESSkillsRatingMageAdept.getValue()).append(", ");
        sb.append(this.txtRESSkillsRatingAspectMage.getValue()).append(", ");
        sb.append(this.txtRESSkillsRatingTechnomancer.getValue()).append(", ");
        
        sb.append(this.txtSpellsMage.getValue()).append(", ");
        sb.append(this.txtSpellsAdept.getValue()).append(", ");
        sb.append(this.txtSpellsMageAdept.getValue()).append(", ");
        sb.append(this.txtSpellsAspectMage.getValue()).append(", ");
        sb.append(this.txtSpellsTechnomancer.getValue()).append(", ");
        
        sb.append(this.txtFormsMage.getValue()).append(", ");
        sb.append(this.txtFormsAdept.getValue()).append(", ");
        sb.append(this.txtFormsMageAdept.getValue()).append(", ");
        sb.append(this.txtFormsAspectMage.getValue()).append(", ");
        sb.append(this.txtFormsTechnomancer.getValue()).append(");");
        
        
        
        
        System.out.println(sb.toString());
        try {
            boolean result = this.data.update(sb.toString());
            JOptionPane.showMessageDialog(this, "Result: " + result);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL: " + ex.getMessage());
            ex.printStackTrace(System.err);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        cboPriority = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        chkHumanPossible = new javax.swing.JCheckBox();
        chkElfPossible = new javax.swing.JCheckBox();
        chkOrkPossible = new javax.swing.JCheckBox();
        chkDwarfPossible = new javax.swing.JCheckBox();
        chkTrollPossible = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtHumanSpecial = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        txtElfSpecial = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        txtOrcSpecial = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        txtDwarfSpecial = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        txtTrollSpecial = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        chkMagePossible = new javax.swing.JCheckBox();
        chkAdeptPossible = new javax.swing.JCheckBox();
        chkMageAdeptPossible = new javax.swing.JCheckBox();
        chkAspectMagePossible = new javax.swing.JCheckBox();
        chkTechnomancerPossible = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtAttributes = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        txtSkills = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txtSkillgroups = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        txtResources = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtMAGMage = new javax.swing.JSpinner();
        txtMAGAdept = new javax.swing.JSpinner();
        txtMAGMageAdept = new javax.swing.JSpinner();
        txtMAGAspectMage = new javax.swing.JSpinner();
        txtMAGTechnomancer = new javax.swing.JSpinner();
        jPanel17 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtRESMage = new javax.swing.JSpinner();
        txtRESAdept = new javax.swing.JSpinner();
        txtRESMageAdept = new javax.swing.JSpinner();
        txtRESAspectMage = new javax.swing.JSpinner();
        txtRESTechnomancer = new javax.swing.JSpinner();
        jPanel18 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtMAGSkillsMage = new javax.swing.JSpinner();
        txtMAGSkillsAdept = new javax.swing.JSpinner();
        txtMAGSkillsMageAdept = new javax.swing.JSpinner();
        txtMAGSkillsAspectMage = new javax.swing.JSpinner();
        txtMAGSkillsTechnomancer = new javax.swing.JSpinner();
        jPanel19 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtMAGSkillsRatingMage = new javax.swing.JSpinner();
        txtMAGSkillsRatingAdept = new javax.swing.JSpinner();
        txtMAGSkillsRatingMageAdept = new javax.swing.JSpinner();
        txtMAGSkillsRatingAspectMage = new javax.swing.JSpinner();
        txtMAGSkillsRatingTechnomancer = new javax.swing.JSpinner();
        jPanel20 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        txtMAGSkillGroupsMage = new javax.swing.JSpinner();
        txtMAGSkillGroupsAdept = new javax.swing.JSpinner();
        txtMAGSkillGroupsMageAdept = new javax.swing.JSpinner();
        txtMAGSkillGroupsAspectMage = new javax.swing.JSpinner();
        txtMAGSkillGroupsTechnomancer = new javax.swing.JSpinner();
        jPanel21 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        txtMAGSkillGroupsRatingMage = new javax.swing.JSpinner();
        txtMAGSkillGroupsRatingAdept = new javax.swing.JSpinner();
        txtMAGSkillGroupsRatingMageAdept = new javax.swing.JSpinner();
        txtMAGSkillGroupsRatingAspectMage = new javax.swing.JSpinner();
        txtMAGSkillGroupsRatingTechnomancer = new javax.swing.JSpinner();
        jPanel22 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtSkillsMage = new javax.swing.JSpinner();
        txtSkillsAdept = new javax.swing.JSpinner();
        txtSkillsMageAdept = new javax.swing.JSpinner();
        txtSkillsAspectMage = new javax.swing.JSpinner();
        txtSkillsTechnomancer = new javax.swing.JSpinner();
        jPanel23 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtSkillRatingMage = new javax.swing.JSpinner();
        txtSkillRatingAdept = new javax.swing.JSpinner();
        txtSkillRatingMageAdept = new javax.swing.JSpinner();
        txtSkillRatingAspectMage = new javax.swing.JSpinner();
        txtSkillRatingTechnomancer = new javax.swing.JSpinner();
        jPanel24 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txtRESSkillsMage = new javax.swing.JSpinner();
        txtRESSkillsAdept = new javax.swing.JSpinner();
        txtRESSkillsMageAdept = new javax.swing.JSpinner();
        txtRESSkillsAspectMage = new javax.swing.JSpinner();
        txtRESSkillsTechnomancer = new javax.swing.JSpinner();
        jPanel25 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        txtRESSkillsRatingMage = new javax.swing.JSpinner();
        txtRESSkillsRatingAdept = new javax.swing.JSpinner();
        txtRESSkillsRatingMageAdept = new javax.swing.JSpinner();
        txtRESSkillsRatingAspectMage = new javax.swing.JSpinner();
        txtRESSkillsRatingTechnomancer = new javax.swing.JSpinner();
        jPanel26 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txtSpellsMage = new javax.swing.JSpinner();
        txtSpellsAdept = new javax.swing.JSpinner();
        txtSpellsMageAdept = new javax.swing.JSpinner();
        txtSpellsAspectMage = new javax.swing.JSpinner();
        txtSpellsTechnomancer = new javax.swing.JSpinner();
        jPanel27 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        txtFormsMage = new javax.swing.JSpinner();
        txtFormsAdept = new javax.swing.JSpinner();
        txtFormsMageAdept = new javax.swing.JSpinner();
        txtFormsAspectMage = new javax.swing.JSpinner();
        txtFormsTechnomancer = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        jLabel27.setText("Select existing");

        cboPriority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPriority.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPriorityActionPerformed(evt);
            }
        });

        jButton1.setText("Add new");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancel");

        cmdDelete.setText("Delete");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        jLabel28.setText("Name:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Possibilities (which metatypes can be chosen in this priority)"));

        chkHumanPossible.setText("Human");

        chkElfPossible.setText("Elf");

        chkOrkPossible.setText("Orc");

        chkDwarfPossible.setText("Dwarf");

        chkTrollPossible.setText("Troll");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkHumanPossible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(chkElfPossible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(chkOrkPossible, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkDwarfPossible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(chkTrollPossible, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkHumanPossible)
                    .addComponent(chkElfPossible)
                    .addComponent(chkOrkPossible)
                    .addComponent(chkDwarfPossible)
                    .addComponent(chkTrollPossible)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Special attribute points"));

        jLabel1.setText("Human:");

        jLabel2.setText("Elf:");

        jLabel3.setText("Orcs:");

        jLabel4.setText("Dwarves:");

        jLabel5.setText("Trolls:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtElfSpecial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHumanSpecial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtOrcSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrollSpecial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDwarfSpecial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHumanSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtElfSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtOrcSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDwarfSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTrollSpecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Magic and Resonance"));

        chkMagePossible.setText("Mage possible");

        chkAdeptPossible.setText("Adept possible");

        chkMageAdeptPossible.setText("Mage Adept possible");

        chkAspectMagePossible.setText("Aspect Mage possible");

        chkTechnomancerPossible.setText("Technomancer possible");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkTechnomancerPossible, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(chkAspectMagePossible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkMageAdeptPossible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkAdeptPossible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkMagePossible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkMagePossible)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAdeptPossible)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkMageAdeptPossible)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkAspectMagePossible)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkTechnomancerPossible)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Attributes, Skills, Resources"));

        jLabel6.setText("Attribute points to spend:");

        jLabel7.setText("Skill points to spend:");

        jLabel8.setText("Skillgroup points to spend:");

        jLabel9.setText("Money to spend:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSkills, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(txtAttributes)
                            .addComponent(txtSkillgroups)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtResources, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSkillgroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtResources, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Magic/Resonance"));

        jLabel11.setText("Mage");

        jLabel12.setText("Adept");

        jLabel13.setText("Mage Adept");

        jLabel14.setText("Aspect Mage");

        jLabel15.setText("Technomancer");

        jLabel30.setText("Magic");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtMAGMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtMAGAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtMAGMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtMAGAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtMAGTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMAGMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel31.setText("Resonance");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtRESMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtRESAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtRESMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtRESAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtRESTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtRESMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel32.setText("Magical skills");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtMAGSkillsMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtMAGSkillsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtMAGSkillsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtMAGSkillsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtMAGSkillsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMAGSkillsMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel33.setText("Rating");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtMAGSkillsRatingMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtMAGSkillsRatingAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtMAGSkillsRatingMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtMAGSkillsRatingAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtMAGSkillsRatingTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMAGSkillsRatingMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillsRatingAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillsRatingMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillsRatingAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillsRatingTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel34.setText("Magical Skillgroups");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtMAGSkillGroupsMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtMAGSkillGroupsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtMAGSkillGroupsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtMAGSkillGroupsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtMAGSkillGroupsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMAGSkillGroupsMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillGroupsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillGroupsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillGroupsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillGroupsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel35.setText("Rating");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtMAGSkillGroupsRatingMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtMAGSkillGroupsRatingAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtMAGSkillGroupsRatingMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtMAGSkillGroupsRatingAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtMAGSkillGroupsRatingTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMAGSkillGroupsRatingMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillGroupsRatingAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillGroupsRatingMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillGroupsRatingAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtMAGSkillGroupsRatingTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel36.setText("Action skills");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtSkillsMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtSkillsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtSkillsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtSkillsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtSkillsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSkillsMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSkillsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSkillsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSkillsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSkillsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel37.setText("Rating");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtSkillRatingMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtSkillRatingAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtSkillRatingMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtSkillRatingAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtSkillRatingTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSkillRatingMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSkillRatingAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSkillRatingMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSkillRatingAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSkillRatingTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel38.setText("Resonance skills");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtRESSkillsMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtRESSkillsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtRESSkillsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtRESSkillsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtRESSkillsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtRESSkillsMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESSkillsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESSkillsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESSkillsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESSkillsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel39.setText("Rating");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtRESSkillsRatingMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtRESSkillsRatingAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtRESSkillsRatingMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtRESSkillsRatingAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtRESSkillsRatingTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtRESSkillsRatingMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESSkillsRatingAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESSkillsRatingMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESSkillsRatingAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtRESSkillsRatingTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel40.setText("Spells");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtSpellsMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtSpellsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtSpellsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtSpellsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtSpellsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtSpellsMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSpellsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSpellsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSpellsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSpellsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel41.setText("Complex Forms");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(txtFormsMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtFormsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtFormsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(txtFormsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(txtFormsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtFormsMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtFormsAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtFormsMageAdept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtFormsAspectMage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtFormsTechnomancer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel12)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel13)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel14)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel15))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(7, 7, 7)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmdDelete)
                                        .addGap(321, 321, 321)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel27)
                                            .addGap(18, 18, 18)
                                            .addComponent(cboPriority, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(5, 5, 5)))
                        .addGap(0, 13, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cboPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(cmdDelete))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Priority newPrio = new Priority();
        newPrio.setName("New Priority");
        DefaultComboBoxModel model = (DefaultComboBoxModel)this.cboPriority.getModel();
        model.addElement(newPrio);
        model.setSelectedItem(newPrio);
        this.edited = newPrio;
        this.update = false;
        this.display();
        
        
        
//        StringBuilder sb = new StringBuilder();
//        sb.append("INSERT INTO priorities(");
//        sb.append("name, ");
//        sb.append("human_possible, elf_possible, orc_possible, dwarf_possible, troll_possible, ");
//        sb.append("special_human, special_elf, special_ork, special_dwarf, special_troll, ");
//        sb.append("attributes, skills, skillgroups, resources, ");
//        sb.append("mage_possible, adept_possible, mage_adept_possible, aspect_mage_possible, technomancer_possible, ");
//        sb.append("magic_mage, magic_adept, magic_mage_adept, magic_aspect_mage,magic_technomancer, ");
//        sb.append("resonance_mage, resonance_adept, resonance_mage_adept, resonance_aspect_mage, resonance_technomancer, ");
//        sb.append("magic_skills_mage, magic_skills_adept, magic_skills_mage_adept, magic_skills_aspect_mage, magic_skills_technomancer, ");
//        
//        sb.append("rating_magic_skills_mage, rating_magic_skills_adept, rating_magic_skills_mage_adept, rating_magic_skills_aspect_mage, rating_magic_skills_technomancer, ");
//        sb.append("active_skills_mage,active_skills_adept,active_skills_mage_adept,active_skills_aspect_mage,active_skills_technomancer,");
//        sb.append("rating_active_skills_mage,rating_active_skills_adept,rating_active_skills_mage_adept,rating_active_skills_aspect_mage,rating_active_skills_technomancer,");
//        sb.append("magic_skillgroups_mage,magic_skillgroups_adept,magic_skillgroups_mage_adept,magic_skillgroups_aspect_mage,magic_skillgroups_technomancer,");        
//        sb.append("rating_magic_skillgroups_mage,rating_magic_skillgroups_adept,rating_magic_skillgroups_mage_adept,rating_magic_skillgroups_aspect_mage,rating_magic_skillgroups_technomancer,");
//        sb.append("resonance_skills_mage,resonance_skills_adept,resonance_skills_mage_adept,resonance_skills_aspect_mage,resonance_skills_technomancer,");
//        sb.append("rating_resonance_skills_mage,rating_resonance_skills_adept,rating_resonance_skills_mage_adept,rating_resonance_skills_aspect_mage,rating_resonance_skills_technomancer,");
//        sb.append("spells_mage,spells_adept,spells_mage_adept,spells_aspect_mage,spells_technomancer,");
//        sb.append("forms_mage,forms_adept,forms_mage_adept,forms_aspect_mage,forms_technomancer) ");
//        sb.append("VALUES ('");
//        
//        sb.append(this.txtName.getText()).append("', ");
//        sb.append(this.checkInt(this.chkHumanPossible)).append(", ");
//        sb.append(this.checkInt(this.chkElfPossible)).append(", ");
//        sb.append(this.checkInt(this.chkOrkPossible)).append(", ");
//        sb.append(this.checkInt(this.chkDwarfPossible)).append(", ");
//        sb.append(this.checkInt(this.chkTrollPossible)).append(", ");
//        sb.append(this.txtHumanSpecial.getValue()).append(", ");
//        sb.append(this.txtElfSpecial.getValue()).append(", ");
//        sb.append(this.txtOrcSpecial.getValue()).append(", ");
//        sb.append(this.txtDwarfSpecial.getValue()).append(", ");
//        sb.append(this.txtTrollSpecial.getValue()).append(", ");
//        sb.append(this.txtAttributes.getValue()).append(", ");
//        sb.append(this.txtSkills.getValue()).append(", ");
//        sb.append(this.txtSkillgroups.getValue()).append(", ");
//        sb.append(this.txtResources.getValue()).append(", ");
//        sb.append(this.checkInt(this.chkMagePossible)).append(", ");
//        sb.append(this.checkInt(this.chkAdeptPossible)).append(", ");
//        sb.append(this.checkInt(this.chkMageAdeptPossible)).append(", ");
//        sb.append(this.checkInt(this.chkAspectMagePossible)).append(", ");
//        sb.append(this.checkInt(this.chkTechnomancerPossible)).append(", ");
//        sb.append(this.txtMAGMage.getValue()).append(", ");
//        sb.append(this.txtMAGAdept.getValue()).append(", ");
//        sb.append(this.txtMAGMageAdept.getValue()).append(", ");
//        sb.append(this.txtMAGAspectMage.getValue()).append(", ");
//        sb.append(this.txtMAGTechnomancer.getValue()).append(", ");
//        sb.append(this.txtRESMage.getValue()).append(", ");
//        sb.append(this.txtRESAdept.getValue()).append(", ");
//        sb.append(this.txtRESMageAdept.getValue()).append(", ");
//        sb.append(this.txtRESAspectMage.getValue()).append(", ");
//        sb.append(this.txtRESTechnomancer.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsMage.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsAdept.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsMageAdept.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsAspectMage.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsTechnomancer.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsRatingMage.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsRatingAdept.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsRatingMageAdept.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsRatingAspectMage.getValue()).append(", ");
//        sb.append(this.txtMAGSkillsRatingTechnomancer.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsMage.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsAdept.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsMageAdept.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsAspectMage.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsTechnomancer.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsRatingMage.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsRatingAdept.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsRatingMageAdept.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsRatingAspectMage.getValue()).append(", ");
//        sb.append(this.txtMAGSkillGroupsRatingTechnomancer.getValue()).append(", ");
//        sb.append(this.txtSkillsMage.getValue()).append(", ");
//        sb.append(this.txtSkillsAdept.getValue()).append(", ");
//        sb.append(this.txtSkillsMageAdept.getValue()).append(", ");
//        sb.append(this.txtSkillsAspectMage.getValue()).append(", ");
//        sb.append(this.txtSkillsTechnomancer.getValue()).append(", ");
//        
//        sb.append(this.txtSkillRatingMage.getValue()).append(", ");
//        sb.append(this.txtSkillRatingAdept.getValue()).append(", ");
//        sb.append(this.txtSkillRatingMageAdept.getValue()).append(", ");
//        sb.append(this.txtSkillRatingAspectMage.getValue()).append(", ");
//        sb.append(this.txtSkillRatingTechnomancer.getValue()).append(", ");
//        
//        sb.append(this.txtRESSkillsMage.getValue()).append(", ");
//        sb.append(this.txtRESSkillsAdept.getValue()).append(", ");
//        sb.append(this.txtRESSkillsMageAdept.getValue()).append(", ");
//        sb.append(this.txtRESSkillsAspectMage.getValue()).append(", ");
//        sb.append(this.txtRESSkillsTechnomancer.getValue()).append(", ");
//        
//        sb.append(this.txtRESSkillsRatingMage.getValue()).append(", ");
//        sb.append(this.txtRESSkillsRatingAdept.getValue()).append(", ");
//        sb.append(this.txtRESSkillsRatingMageAdept.getValue()).append(", ");
//        sb.append(this.txtRESSkillsRatingAspectMage.getValue()).append(", ");
//        sb.append(this.txtRESSkillsRatingTechnomancer.getValue()).append(", ");
//        
//        sb.append(this.txtSpellsMage.getValue()).append(", ");
//        sb.append(this.txtSpellsAdept.getValue()).append(", ");
//        sb.append(this.txtSpellsMageAdept.getValue()).append(", ");
//        sb.append(this.txtSpellsAspectMage.getValue()).append(", ");
//        sb.append(this.txtSpellsTechnomancer.getValue()).append(", ");
//        
//        sb.append(this.txtFormsMage.getValue()).append(", ");
//        sb.append(this.txtFormsAdept.getValue()).append(", ");
//        sb.append(this.txtFormsMageAdept.getValue()).append(", ");
//        sb.append(this.txtFormsAspectMage.getValue()).append(", ");
//        sb.append(this.txtFormsTechnomancer.getValue()).append(");");
//        
//        
//        
//        
//        System.out.println(sb.toString());
//        try {
//            boolean result = this.data.update(sb.toString());
//            JOptionPane.showMessageDialog(this, "Result: " + result);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "SQL: " + ex.getMessage());
//            ex.printStackTrace(System.err);
//        } catch (Exception ex) {
//            ex.printStackTrace(System.err);
//        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if(this.update) {
            this.update();
        } else {
            this.saveAsNew();
        }
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2ActionPerformed


    private void display() {
        if(this.edited != null) {
                this.txtName.setText(this.edited.getName());
                
                this.chkHumanPossible.setSelected(this.edited.isHumanPossible());
                this.chkElfPossible.setSelected(this.edited.isElfPossible());
                this.chkOrkPossible.setSelected(this.edited.isOrkPossible());
                this.chkDwarfPossible.setSelected(this.edited.isDwarfPossible());
                this.chkTrollPossible.setSelected(this.edited.isTrollPossible());
                
                this.txtHumanSpecial.setValue(this.edited.getSpecialHuman());
                this.txtElfSpecial.setValue(this.edited.getSpecialElf());
                this.txtOrcSpecial.setValue(this.edited.getSpecialOrk());
                this.txtDwarfSpecial.setValue(this.edited.getSpecialDwarf());
                this.txtTrollSpecial.setValue(this.edited.getSpecialTroll());
                
                this.txtAttributes.setValue(this.edited.getAttributes());
                this.txtSkills.setValue(this.edited.getSkillpoints());
                this.txtSkillgroups.setValue(this.edited.getSkillgroupPoints());
                this.txtResources.setValue(this.edited.getResources());
                
                this.chkMagePossible.setSelected(this.edited.isMagePossible());
                this.chkAdeptPossible.setSelected(this.edited.isAdeptPossible());
                this.chkMageAdeptPossible.setSelected(this.edited.isMageAdeptPossible());
                this.chkAspectMagePossible.setSelected(this.edited.isAspectMagePossible());
                this.chkTechnomancerPossible.setSelected(this.edited.isTechnomancerPossible());
                
                this.txtMAGMage.setValue(this.edited.getMagicMage());
                this.txtMAGAdept.setValue(this.edited.getMagicAdept());
                this.txtMAGMageAdept.setValue(this.edited.getMagicMageAdept());
                this.txtMAGAspectMage.setValue(this.edited.getMagicAspectMage());
                this.txtMAGTechnomancer.setValue(this.edited.getMagicTechnomancer());
                
                this.txtRESMage.setValue(this.edited.getResonanceMage());
                this.txtRESAdept.setValue(this.edited.getResonanceAdept());
                this.txtRESMageAdept.setValue(this.edited.getResonanceMageAdept());
                this.txtRESAspectMage.setValue(this.edited.getResonanceAspectMage());
                this.txtRESTechnomancer.setValue(this.edited.getResonanceTechnomancer());
                
                this.txtMAGSkillsMage.setValue(this.edited.getMagicSkillsMage());
                this.txtMAGSkillsAdept.setValue(this.edited.getMagicSkillsAdept());
                this.txtMAGSkillsMageAdept.setValue(this.edited.getMagicSkillsMageAdept());
                this.txtMAGSkillsAspectMage.setValue(this.edited.getMagicSkillsAspectMage());
                this.txtMAGSkillsTechnomancer.setValue(this.edited.getMagicSkillsTechnomancer());
                
                this.txtMAGSkillsRatingMage.setValue(this.edited.getRatingMagicSkillsMage());
                this.txtMAGSkillsRatingAdept.setValue(this.edited.getRatingMagicSkillsAdept());
                this.txtMAGSkillsRatingMageAdept.setValue(this.edited.getRatingMagicSkillsMageAdept());
                this.txtMAGSkillsRatingAspectMage.setValue(this.edited.getRatingMagicSkillsAspectMage());
                this.txtMAGSkillsRatingTechnomancer.setValue(this.edited.getRatingMagicSkillsTechnomancer());
                
                this.txtMAGSkillGroupsMage.setValue(this.edited.getMagicSkillgroupsMage());
                this.txtMAGSkillGroupsAdept.setValue(this.edited.getMagicSkillgroupsAdept());
                this.txtMAGSkillGroupsMageAdept.setValue(this.edited.getMagicSkillgroupsMageAdept());
                this.txtMAGSkillGroupsAspectMage.setValue(this.edited.getMagicSkillgroupsAspectMage());
                this.txtMAGSkillGroupsTechnomancer.setValue(this.edited.getMagicSkillgroupsTechnomancer());
                
                this.txtMAGSkillGroupsRatingMage.setValue(this.edited.getRatingMagicSkillgroupsMage());
                this.txtMAGSkillGroupsRatingAdept.setValue(this.edited.getRatingMagicSkillgroupsAdept());
                this.txtMAGSkillGroupsRatingMageAdept.setValue(this.edited.getRatingMagicSkillgroupsMageAdept());
                this.txtMAGSkillGroupsRatingAspectMage.setValue(this.edited.getRatingMagicSkillgroupsAspectMage());
                this.txtMAGSkillGroupsRatingTechnomancer.setValue(this.edited.getRatingMagicSkillgroupsTechnomancer());
                
                this.txtSkillsMage.setValue(this.edited.getActiveSkillsMage());
                this.txtSkillsAdept.setValue(this.edited.getActiveSkillsAdept());
                this.txtSkillsMageAdept.setValue(this.edited.getActiveSkillsMageAdept());
                this.txtSkillsAspectMage.setValue(this.edited.getActiveSkillsAspectMage());
                this.txtSkillsTechnomancer.setValue(this.edited.getActiveSkillsTechnomancer());
                
                this.txtSkillRatingMage.setValue(this.edited.getRatingActiveSkillsMage());
                this.txtSkillRatingAdept.setValue(this.edited.getRatingActiveSkillsAdept());
                this.txtSkillRatingMageAdept.setValue(this.edited.getRatingActiveSkillsMageAdept());
                this.txtSkillRatingAspectMage.setValue(this.edited.getRatingActiveSkillsAspectMage());
                this.txtSkillRatingTechnomancer.setValue(this.edited.getRatingActiveSkillsTechnomancer());
                
                this.txtRESSkillsMage.setValue(this.edited.getResonanceSkillsMage());
                this.txtRESSkillsAdept.setValue(this.edited.getResonanceSkillsAdept());
                this.txtRESSkillsMageAdept.setValue(this.edited.getResonanceSkillsMageAdept());
                this.txtRESSkillsAspectMage.setValue(this.edited.getResonanceSkillsAspectMage());
                this.txtRESSkillsTechnomancer.setValue(this.edited.getResonanceSkillsTechnomancer());
                
                this.txtRESSkillsRatingMage.setValue(this.edited.getRatingResonanceSkillsMage());
                this.txtRESSkillsRatingAdept.setValue(this.edited.getRatingResonanceSkillsAdept());
                this.txtRESSkillsRatingMageAdept.setValue(this.edited.getRatingResonanceSkillsMageAdept());
                this.txtRESSkillsRatingAspectMage.setValue(this.edited.getRatingResonanceSkillsAspectMage());
                this.txtRESSkillsRatingTechnomancer.setValue(this.edited.getRatingResonanceSkillsTechnomancer());
                
                this.txtSpellsMage.setValue(this.edited.getSpellsMage());
                this.txtSpellsAdept.setValue(this.edited.getSpellsAdept());
                this.txtSpellsMageAdept.setValue(this.edited.getSpellsMageAdept());
                this.txtSpellsAspectMage.setValue(this.edited.getSpellsAspectMage());
                this.txtSpellsTechnomancer.setValue(this.edited.getSpellsTechnomancer());
                
                this.txtFormsMage.setValue(this.edited.getFormsMage());
                this.txtFormsAdept.setValue(this.edited.getFormsAdept());
                this.txtFormsMageAdept.setValue(this.edited.getFormsMageAdept());
                this.txtFormsAspectMage.setValue(this.edited.getFormsAspectMage());
                this.txtFormsTechnomancer.setValue(this.edited.getFormsTechnomancer());
        }
    }
    
    private void cboPriorityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPriorityActionPerformed
        try {
            this.edited = (Priority)this.cboPriority.getSelectedItem();
            if(this.edited != null) {
                this.txtName.setText(this.edited.getName());
                
                this.chkHumanPossible.setSelected(this.edited.isHumanPossible());
                this.chkElfPossible.setSelected(this.edited.isElfPossible());
                this.chkOrkPossible.setSelected(this.edited.isOrkPossible());
                this.chkDwarfPossible.setSelected(this.edited.isDwarfPossible());
                this.chkTrollPossible.setSelected(this.edited.isTrollPossible());
                
                this.txtHumanSpecial.setValue(this.edited.getSpecialHuman());
                this.txtElfSpecial.setValue(this.edited.getSpecialElf());
                this.txtOrcSpecial.setValue(this.edited.getSpecialOrk());
                this.txtDwarfSpecial.setValue(this.edited.getSpecialDwarf());
                this.txtTrollSpecial.setValue(this.edited.getSpecialTroll());
                
                this.txtAttributes.setValue(this.edited.getAttributes());
                this.txtSkills.setValue(this.edited.getSkillpoints());
                this.txtSkillgroups.setValue(this.edited.getSkillgroupPoints());
                this.txtResources.setValue(this.edited.getResources());
                
                this.chkMagePossible.setSelected(this.edited.isMagePossible());
                this.chkAdeptPossible.setSelected(this.edited.isAdeptPossible());
                this.chkMageAdeptPossible.setSelected(this.edited.isMageAdeptPossible());
                this.chkAspectMagePossible.setSelected(this.edited.isAspectMagePossible());
                this.chkTechnomancerPossible.setSelected(this.edited.isTechnomancerPossible());
                
                this.txtMAGMage.setValue(this.edited.getMagicMage());
                this.txtMAGAdept.setValue(this.edited.getMagicAdept());
                this.txtMAGMageAdept.setValue(this.edited.getMagicMageAdept());
                this.txtMAGAspectMage.setValue(this.edited.getMagicAspectMage());
                this.txtMAGTechnomancer.setValue(this.edited.getMagicTechnomancer());
                
                this.txtRESMage.setValue(this.edited.getResonanceMage());
                this.txtRESAdept.setValue(this.edited.getResonanceAdept());
                this.txtRESMageAdept.setValue(this.edited.getResonanceMageAdept());
                this.txtRESAspectMage.setValue(this.edited.getResonanceAspectMage());
                this.txtRESTechnomancer.setValue(this.edited.getResonanceTechnomancer());
                
                this.txtMAGSkillsMage.setValue(this.edited.getMagicSkillsMage());
                this.txtMAGSkillsAdept.setValue(this.edited.getMagicSkillsAdept());
                this.txtMAGSkillsMageAdept.setValue(this.edited.getMagicSkillsMageAdept());
                this.txtMAGSkillsAspectMage.setValue(this.edited.getMagicSkillsAspectMage());
                this.txtMAGSkillsTechnomancer.setValue(this.edited.getMagicSkillsTechnomancer());
                
                this.txtMAGSkillsRatingMage.setValue(this.edited.getRatingMagicSkillsMage());
                this.txtMAGSkillsRatingAdept.setValue(this.edited.getRatingMagicSkillsAdept());
                this.txtMAGSkillsRatingMageAdept.setValue(this.edited.getRatingMagicSkillsMageAdept());
                this.txtMAGSkillsRatingAspectMage.setValue(this.edited.getRatingMagicSkillsAspectMage());
                this.txtMAGSkillsRatingTechnomancer.setValue(this.edited.getRatingMagicSkillsTechnomancer());
                
                this.txtMAGSkillGroupsMage.setValue(this.edited.getMagicSkillgroupsMage());
                this.txtMAGSkillGroupsAdept.setValue(this.edited.getMagicSkillgroupsAdept());
                this.txtMAGSkillGroupsMageAdept.setValue(this.edited.getMagicSkillgroupsMageAdept());
                this.txtMAGSkillGroupsAspectMage.setValue(this.edited.getMagicSkillgroupsAspectMage());
                this.txtMAGSkillGroupsTechnomancer.setValue(this.edited.getMagicSkillgroupsTechnomancer());
                
                this.txtMAGSkillGroupsRatingMage.setValue(this.edited.getRatingMagicSkillgroupsMage());
                this.txtMAGSkillGroupsRatingAdept.setValue(this.edited.getRatingMagicSkillgroupsAdept());
                this.txtMAGSkillGroupsRatingMageAdept.setValue(this.edited.getRatingMagicSkillgroupsMageAdept());
                this.txtMAGSkillGroupsRatingAspectMage.setValue(this.edited.getRatingMagicSkillgroupsAspectMage());
                this.txtMAGSkillGroupsRatingTechnomancer.setValue(this.edited.getRatingMagicSkillgroupsTechnomancer());
                
                this.txtSkillsMage.setValue(this.edited.getActiveSkillsMage());
                this.txtSkillsAdept.setValue(this.edited.getActiveSkillsAdept());
                this.txtSkillsMageAdept.setValue(this.edited.getActiveSkillsMageAdept());
                this.txtSkillsAspectMage.setValue(this.edited.getActiveSkillsAspectMage());
                this.txtSkillsTechnomancer.setValue(this.edited.getActiveSkillsTechnomancer());
                
                this.txtSkillRatingMage.setValue(this.edited.getRatingActiveSkillsMage());
                this.txtSkillRatingAdept.setValue(this.edited.getRatingActiveSkillsAdept());
                this.txtSkillRatingMageAdept.setValue(this.edited.getRatingActiveSkillsMageAdept());
                this.txtSkillRatingAspectMage.setValue(this.edited.getRatingActiveSkillsAspectMage());
                this.txtSkillRatingTechnomancer.setValue(this.edited.getRatingActiveSkillsTechnomancer());
                
                this.txtRESSkillsMage.setValue(this.edited.getResonanceSkillsMage());
                this.txtRESSkillsAdept.setValue(this.edited.getResonanceSkillsAdept());
                this.txtRESSkillsMageAdept.setValue(this.edited.getResonanceSkillsMageAdept());
                this.txtRESSkillsAspectMage.setValue(this.edited.getResonanceSkillsAspectMage());
                this.txtRESSkillsTechnomancer.setValue(this.edited.getResonanceSkillsTechnomancer());
                
                this.txtRESSkillsRatingMage.setValue(this.edited.getRatingResonanceSkillsMage());
                this.txtRESSkillsRatingAdept.setValue(this.edited.getRatingResonanceSkillsAdept());
                this.txtRESSkillsRatingMageAdept.setValue(this.edited.getRatingResonanceSkillsMageAdept());
                this.txtRESSkillsRatingAspectMage.setValue(this.edited.getRatingResonanceSkillsAspectMage());
                this.txtRESSkillsRatingTechnomancer.setValue(this.edited.getRatingResonanceSkillsTechnomancer());
                
                this.txtSpellsMage.setValue(this.edited.getSpellsMage());
                this.txtSpellsAdept.setValue(this.edited.getSpellsAdept());
                this.txtSpellsMageAdept.setValue(this.edited.getSpellsMageAdept());
                this.txtSpellsAspectMage.setValue(this.edited.getSpellsAspectMage());
                this.txtSpellsTechnomancer.setValue(this.edited.getSpellsTechnomancer());
                
                this.txtFormsMage.setValue(this.edited.getFormsMage());
                this.txtFormsAdept.setValue(this.edited.getFormsAdept());
                this.txtFormsMageAdept.setValue(this.edited.getFormsMageAdept());
                this.txtFormsAspectMage.setValue(this.edited.getFormsAspectMage());
                this.txtFormsTechnomancer.setValue(this.edited.getFormsTechnomancer());
                
                this.update = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        
    }//GEN-LAST:event_cboPriorityActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        // TODO add your handling code here:
        if(this.edited == null) {
            return;
        }
        
        final String sql = "DELETE FROM priorities WHERE name = '" + this.edited.getName() + "';";
        try {
            final boolean result = this.data.update(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PrioritiesEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboPriority;
    private javax.swing.JCheckBox chkAdeptPossible;
    private javax.swing.JCheckBox chkAspectMagePossible;
    private javax.swing.JCheckBox chkDwarfPossible;
    private javax.swing.JCheckBox chkElfPossible;
    private javax.swing.JCheckBox chkHumanPossible;
    private javax.swing.JCheckBox chkMageAdeptPossible;
    private javax.swing.JCheckBox chkMagePossible;
    private javax.swing.JCheckBox chkOrkPossible;
    private javax.swing.JCheckBox chkTechnomancerPossible;
    private javax.swing.JCheckBox chkTrollPossible;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner txtAttributes;
    private javax.swing.JSpinner txtDwarfSpecial;
    private javax.swing.JSpinner txtElfSpecial;
    private javax.swing.JSpinner txtFormsAdept;
    private javax.swing.JSpinner txtFormsAspectMage;
    private javax.swing.JSpinner txtFormsMage;
    private javax.swing.JSpinner txtFormsMageAdept;
    private javax.swing.JSpinner txtFormsTechnomancer;
    private javax.swing.JSpinner txtHumanSpecial;
    private javax.swing.JSpinner txtMAGAdept;
    private javax.swing.JSpinner txtMAGAspectMage;
    private javax.swing.JSpinner txtMAGMage;
    private javax.swing.JSpinner txtMAGMageAdept;
    private javax.swing.JSpinner txtMAGSkillGroupsAdept;
    private javax.swing.JSpinner txtMAGSkillGroupsAspectMage;
    private javax.swing.JSpinner txtMAGSkillGroupsMage;
    private javax.swing.JSpinner txtMAGSkillGroupsMageAdept;
    private javax.swing.JSpinner txtMAGSkillGroupsRatingAdept;
    private javax.swing.JSpinner txtMAGSkillGroupsRatingAspectMage;
    private javax.swing.JSpinner txtMAGSkillGroupsRatingMage;
    private javax.swing.JSpinner txtMAGSkillGroupsRatingMageAdept;
    private javax.swing.JSpinner txtMAGSkillGroupsRatingTechnomancer;
    private javax.swing.JSpinner txtMAGSkillGroupsTechnomancer;
    private javax.swing.JSpinner txtMAGSkillsAdept;
    private javax.swing.JSpinner txtMAGSkillsAspectMage;
    private javax.swing.JSpinner txtMAGSkillsMage;
    private javax.swing.JSpinner txtMAGSkillsMageAdept;
    private javax.swing.JSpinner txtMAGSkillsRatingAdept;
    private javax.swing.JSpinner txtMAGSkillsRatingAspectMage;
    private javax.swing.JSpinner txtMAGSkillsRatingMage;
    private javax.swing.JSpinner txtMAGSkillsRatingMageAdept;
    private javax.swing.JSpinner txtMAGSkillsRatingTechnomancer;
    private javax.swing.JSpinner txtMAGSkillsTechnomancer;
    private javax.swing.JSpinner txtMAGTechnomancer;
    private javax.swing.JTextField txtName;
    private javax.swing.JSpinner txtOrcSpecial;
    private javax.swing.JSpinner txtRESAdept;
    private javax.swing.JSpinner txtRESAspectMage;
    private javax.swing.JSpinner txtRESMage;
    private javax.swing.JSpinner txtRESMageAdept;
    private javax.swing.JSpinner txtRESSkillsAdept;
    private javax.swing.JSpinner txtRESSkillsAspectMage;
    private javax.swing.JSpinner txtRESSkillsMage;
    private javax.swing.JSpinner txtRESSkillsMageAdept;
    private javax.swing.JSpinner txtRESSkillsRatingAdept;
    private javax.swing.JSpinner txtRESSkillsRatingAspectMage;
    private javax.swing.JSpinner txtRESSkillsRatingMage;
    private javax.swing.JSpinner txtRESSkillsRatingMageAdept;
    private javax.swing.JSpinner txtRESSkillsRatingTechnomancer;
    private javax.swing.JSpinner txtRESSkillsTechnomancer;
    private javax.swing.JSpinner txtRESTechnomancer;
    private javax.swing.JSpinner txtResources;
    private javax.swing.JSpinner txtSkillRatingAdept;
    private javax.swing.JSpinner txtSkillRatingAspectMage;
    private javax.swing.JSpinner txtSkillRatingMage;
    private javax.swing.JSpinner txtSkillRatingMageAdept;
    private javax.swing.JSpinner txtSkillRatingTechnomancer;
    private javax.swing.JSpinner txtSkillgroups;
    private javax.swing.JSpinner txtSkills;
    private javax.swing.JSpinner txtSkillsAdept;
    private javax.swing.JSpinner txtSkillsAspectMage;
    private javax.swing.JSpinner txtSkillsMage;
    private javax.swing.JSpinner txtSkillsMageAdept;
    private javax.swing.JSpinner txtSkillsTechnomancer;
    private javax.swing.JSpinner txtSpellsAdept;
    private javax.swing.JSpinner txtSpellsAspectMage;
    private javax.swing.JSpinner txtSpellsMage;
    private javax.swing.JSpinner txtSpellsMageAdept;
    private javax.swing.JSpinner txtSpellsTechnomancer;
    private javax.swing.JSpinner txtTrollSpecial;
    // End of variables declaration//GEN-END:variables
}
