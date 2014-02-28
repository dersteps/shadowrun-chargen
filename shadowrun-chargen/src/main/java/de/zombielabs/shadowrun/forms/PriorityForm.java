/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms;

import de.zombielabs.shadowrun.common.data.DataProvider;
import de.zombielabs.shadowrun.common.data.Metatype;
import de.zombielabs.shadowrun.common.data.Priority;
import de.zombielabs.shadowrun.forms.data.EmptyComboBoxItem;
import de.zombielabs.shadowrun.forms.data.Mages;
import de.zombielabs.shadowrun.forms.data.PriorityAttributeComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityMagicComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityMetatypeComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityResourcelComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PrioritySkillComboBoxItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Steps
 */
public class PriorityForm extends ZombieDialog {

    private static final String KEY_HUMAN = "HUMAN";
    
    private static final String KEY_ELF = "ELF";
    
    private static final String KEY_ORC = "ORC";
    
    private static final String KEY_DWARF = "DWARF";
    
    private static final String KEY_TROLL = "TROLL";
    
    private List<String> priosTaken = null;
    
    private HashMap<String, Priority> taken = null;
    
    private PriorityAttributeComboBoxItem selectedAttributeItem;
    private PriorityMetatypeComboBoxItem selectedMetaItem;
    private PriorityMagicComboBoxItem selectedMagicItem;
    private PrioritySkillComboBoxItem selectedSkillsItem;
    private PriorityResourcelComboBoxItem selectedResourceItem;

    public PriorityAttributeComboBoxItem getSelectedAttributeItem() {
        return selectedAttributeItem;
    }

    public PriorityMetatypeComboBoxItem getSelectedMetaItem() {
        return selectedMetaItem;
    }

    public PriorityMagicComboBoxItem getSelectedMagicItem() {
        return selectedMagicItem;
    }

    public PrioritySkillComboBoxItem getSelectedSkillsItem() {
        return selectedSkillsItem;
    }

    public PriorityResourcelComboBoxItem getSelectedResourceItem() {
        return selectedResourceItem;
    }

    private EmptyComboBoxItem defaultAttributeItem = new EmptyComboBoxItem("--- Please select a priority for attributes ---");
    private EmptyComboBoxItem defaultMetaItem = new EmptyComboBoxItem("--- Please select a priortiy for the character's metatype ---");
    private EmptyComboBoxItem defaultMagicItem = new EmptyComboBoxItem("--- Please select a priority for magic/resonance ---");
    private EmptyComboBoxItem defaultSkillItem = new EmptyComboBoxItem("--- Please select a priority for the character's skills ---");
    private EmptyComboBoxItem defaultResourceItem = new EmptyComboBoxItem("--- Please select a priority for the character's resources ---");
    
    private HashMap<JComboBox, Integer> selection = null;
    
    public PriorityForm(java.awt.Frame parent, boolean modal, DataProvider data) {
        super(parent, modal, data);
        initComponents();
        
        if(this.taken == null) {
            this.taken = new HashMap<String, Priority>() {{
               put(TAKEN_META, null);
               put(TAKEN_ATTR, null);
               put(TAKEN_MAGIC, null);
               put(TAKEN_SKILLS, null);
               put(TAKEN_RESS, null);
            }};
        }
        
        this.selection = new HashMap<JComboBox, Integer>();
//        this.jComboBox1.setRenderer(new PriorityComboRenderer());
//        this.cboAttributes.setRenderer(new PriorityComboRenderer());
        this.init();
    }
    
    private void loadSkills() {
        DefaultComboBoxModel pm = new DefaultComboBoxModel();
        
        pm.addElement(this.defaultSkillItem);
        for(final Priority prio : this.data.getPriorities()) {
            if(this.isTaken(prio)) {
                pm.addElement(new EmptyComboBoxItem("Priority " + prio.getName() + " is taken!"));
                continue;
            } 
            PrioritySkillComboBoxItem item = new PrioritySkillComboBoxItem(prio);
            pm.addElement(item);
        }
        
        this.cboSkills.setModel(pm);
    }
    
    private void loadResources() {
        DefaultComboBoxModel pm = new DefaultComboBoxModel();
        
        pm.addElement(this.defaultResourceItem);
        for(final Priority prio : this.data.getPriorities()) {
            if(this.isTaken(prio)) {
                pm.addElement(new EmptyComboBoxItem("Priority " + prio.getName() + " is taken!"));
                continue;
            } 
            PriorityResourcelComboBoxItem item = new PriorityResourcelComboBoxItem(prio);
            pm.addElement(item);
        }
        
        this.cboResources.setModel(pm);
    }
    
    private void loadAttributes() {
        DefaultComboBoxModel pm = new DefaultComboBoxModel();
        
        pm.addElement(this.defaultAttributeItem);
        for(final Priority prio : this.data.getPriorities()) {
            if(this.isTaken(prio)) {
                pm.addElement(new EmptyComboBoxItem("Priority " + prio.getName() + " is taken!"));
                continue;
            } 
            PriorityAttributeComboBoxItem item = new PriorityAttributeComboBoxItem(prio);
            pm.addElement(item);
        }
        
        this.cboAttributes.setModel(pm);
    }
    
    private void loadMagic() {
        DefaultComboBoxModel modMagic = new DefaultComboBoxModel();
        
        modMagic.addElement(this.defaultMagicItem);
        
        for(final Priority prio : this.data.getPriorities()) {
            if(prio.isMagePossible()) {  
                modMagic.addElement(new PriorityMagicComboBoxItem(Mages.MAGE, prio));
            }
            
            if(prio.isMageAdeptPossible()) {  
                modMagic.addElement(new PriorityMagicComboBoxItem(Mages.MAGEADEPT, prio));
            }
            
            if(prio.isAdeptPossible()) {  
                modMagic.addElement(new PriorityMagicComboBoxItem(Mages.ADEPT, prio));
            }
            
            if(prio.isAspectMagePossible()) {  
                modMagic.addElement(new PriorityMagicComboBoxItem(Mages.ASPECTMAGE, prio));
            }
            
            if(prio.isTechnomancerPossible()) {  
                modMagic.addElement(new PriorityMagicComboBoxItem(Mages.TECHNOMANCER, prio));
            }
            
            if(!prio.isAdeptPossible() && !prio.isAspectMagePossible() && !prio.isMageAdeptPossible() && !prio.isTechnomancerPossible() && !prio.isMagePossible()) {
                modMagic.addElement(new PriorityMagicComboBoxItem(Mages.MUNDANE, prio));
            }  
        }
        
        this.cboMagic.setModel(modMagic);
    }
    
    private void loadMetatypes() {
        // Load metatypes into combos
        DefaultComboBoxModel modMeta = new DefaultComboBoxModel();
        
        HashMap<String, Metatype> metas = new HashMap<String, Metatype>();
        
        for(final Metatype mt : this.data.getMetatypes()) {
            switch(mt.getId()) {
                case 1: { metas.put(KEY_HUMAN, mt); break; }
                case 2: { metas.put(KEY_ELF, mt); break; }
                case 3: { metas.put(KEY_DWARF, mt); break; }
                case 4: { metas.put(KEY_ORC, mt); break; }
                case 5: { metas.put(KEY_TROLL, mt); break; }
                default: {
                    System.err.println("Unrecognized meta type: " + mt.getName());
                    break;
                }
            }
        }

        modMeta.addElement(this.defaultMetaItem);
        
        for(final Priority prio : this.data.getPriorities()) {
            
            final boolean a = !this.isTaken(prio);
            
            if(prio.isHumanPossible()) {
                modMeta.addElement(new PriorityMetatypeComboBoxItem(prio, metas.get(KEY_HUMAN), prio.getSpecialHuman(), a));
            }
            
            if(prio.isElfPossible()) {
                modMeta.addElement(new PriorityMetatypeComboBoxItem(prio, metas.get(KEY_ELF), prio.getSpecialElf(), a));
            }
            
            if(prio.isOrkPossible()) {
                modMeta.addElement(new PriorityMetatypeComboBoxItem(prio, metas.get(KEY_ORC), prio.getSpecialOrk(), a));
            }
            
            if(prio.isDwarfPossible()) {
                modMeta.addElement(new PriorityMetatypeComboBoxItem(prio, metas.get(KEY_DWARF), prio.getSpecialDwarf(), a));
            }
            
            if(prio.isTrollPossible()) {
                modMeta.addElement(new PriorityMetatypeComboBoxItem(prio, metas.get(KEY_TROLL), prio.getSpecialTroll(), a));
            }
        }

        this.jComboBox1.setModel(modMeta);
    }
    
    private static final String TAKEN_META = "METATYPE";
    private static final String TAKEN_ATTR = "ATTRIBUTES";
    private static final String TAKEN_MAGIC = "MAGIC";
    private static final String TAKEN_SKILLS = "SKILLS";
    private static final String TAKEN_RESS = "RESOURCES";
    
    private boolean isTaken(Priority p) {
        for(final Entry<String, Priority> entry : this.taken.entrySet()) {
            if(entry == null || entry.getValue() == null) { continue; }
            if(entry.getValue().getName().equalsIgnoreCase(p.getName())) {
                System.out.println("Priority is taken for " + entry.getKey());
                return true;
            }
        }
        
        return false;
    }
    
    
    private void init() {
        
        this.loadMetatypes();
        this.loadAttributes();
        this.loadMagic();
        this.loadSkills();
        this.loadResources();
        
        this.lblSelectedAttributes.setText("<None yet>");
        this.lblSelectedMagic.setText("<None yet>");
        this.lblSelectedMetatype.setText("<None yet>");
        this.lblSelectedResources.setText("<None yet>");
        this.lblSelectedSkills.setText("<None yet>");
    
//        for(final Entry<JComboBox, Integer> entry : this.selection.entrySet()) {
//            if(entry.getKey() != null && entry.getValue() != null) {
//                entry.getKey().setSelectedIndex(entry.getValue());
//            }
//        }

//        this.jComboBox1.setSelectedItem(this.selectedMetaItem);
//        this.cboAttributes.setSelectedItem(this.selectedMetaItem == null ? this.defaultMetaItem : this.selectedMetaItem);
        
        this.displaySelectedMetatype();
        
        
        
        
        
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblKON = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblGES = new javax.swing.JLabel();
        lblSTR = new javax.swing.JLabel();
        lblREA = new javax.swing.JLabel();
        lblCHA = new javax.swing.JLabel();
        lblINT = new javax.swing.JLabel();
        lblLOG = new javax.swing.JLabel();
        lblWIL = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblEDG = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSpecialPoints = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMetaModifiers = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cboAttributes = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cboMagic = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblMagic = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblSkills = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblOther = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        cboSkills = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        cboResources = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblSelectedMetatype = new javax.swing.JLabel();
        lblSelectedAttributes = new javax.swing.JLabel();
        lblSelectedMagic = new javax.swing.JLabel();
        lblSelectedSkills = new javax.swing.JLabel();
        lblSelectedResources = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/zombielabs/shadowrun/forms/PriorityDialogBundle"); // NOI18N
        setTitle(bundle.getString("PriorityForm.title")); // NOI18N
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
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jButton1.setText(bundle.getString("PriorityForm.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(bundle.getString("PriorityForm.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText(bundle.getString("PriorityForm.jLabel2.text")); // NOI18N

        jPanel3.setForeground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(bundle.getString("PriorityForm.jLabel1.text")); // NOI18N

        lblKON.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKON.setText(bundle.getString("PriorityForm.lblKON.text")); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText(bundle.getString("PriorityForm.jLabel18.text")); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText(bundle.getString("PriorityForm.jLabel19.text")); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText(bundle.getString("PriorityForm.jLabel20.text")); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText(bundle.getString("PriorityForm.jLabel21.text")); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText(bundle.getString("PriorityForm.jLabel22.text")); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText(bundle.getString("PriorityForm.jLabel23.text")); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText(bundle.getString("PriorityForm.jLabel24.text")); // NOI18N

        lblGES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGES.setText(bundle.getString("PriorityForm.lblGES.text")); // NOI18N

        lblSTR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSTR.setText(bundle.getString("PriorityForm.lblSTR.text")); // NOI18N

        lblREA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblREA.setText(bundle.getString("PriorityForm.lblREA.text")); // NOI18N

        lblCHA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCHA.setText(bundle.getString("PriorityForm.lblCHA.text")); // NOI18N

        lblINT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblINT.setText(bundle.getString("PriorityForm.lblINT.text")); // NOI18N

        lblLOG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOG.setText(bundle.getString("PriorityForm.lblLOG.text")); // NOI18N

        lblWIL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWIL.setText(bundle.getString("PriorityForm.lblWIL.text")); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText(bundle.getString("PriorityForm.jLabel25.text")); // NOI18N

        lblEDG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEDG.setText(bundle.getString("PriorityForm.lblEDG.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText(bundle.getString("PriorityForm.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText(bundle.getString("PriorityForm.jLabel4.text")); // NOI18N

        lblSpecialPoints.setText(bundle.getString("PriorityForm.lblSpecialPoints.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText(bundle.getString("PriorityForm.jLabel6.text")); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setOpaque(false);

        txtMetaModifiers.setEditable(false);
        txtMetaModifiers.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtMetaModifiers.setText(bundle.getString("PriorityForm.txtMetaModifiers.text")); // NOI18N
        txtMetaModifiers.setOpaque(false);
        jScrollPane1.setViewportView(txtMetaModifiers);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblKON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(lblGES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(lblSTR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(lblREA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(lblCHA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(lblINT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(lblLOG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(lblWIL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblEDG, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSpecialPoints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEDG))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKON)
                            .addComponent(lblGES)
                            .addComponent(lblSTR)
                            .addComponent(lblREA)
                            .addComponent(lblCHA)
                            .addComponent(lblINT)
                            .addComponent(lblLOG)
                            .addComponent(lblWIL))))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSpecialPoints)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("PriorityForm.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText(bundle.getString("PriorityForm.jLabel5.text")); // NOI18N

        cboAttributes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboAttributes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAttributesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboAttributes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("PriorityForm.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText(bundle.getString("PriorityForm.jLabel12.text")); // NOI18N

        cboMagic.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMagic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMagicActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText(bundle.getString("PriorityForm.jLabel13.text")); // NOI18N

        lblMagic.setText(bundle.getString("PriorityForm.lblMagic.text")); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText(bundle.getString("PriorityForm.jLabel15.text")); // NOI18N

        lblSkills.setText(bundle.getString("PriorityForm.lblSkills.text")); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText(bundle.getString("PriorityForm.jLabel17.text")); // NOI18N

        lblOther.setText(bundle.getString("PriorityForm.lblOther.text")); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblSkills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMagic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblOther, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMagic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSkills)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOther)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboMagic, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMagic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(bundle.getString("PriorityForm.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText(bundle.getString("PriorityForm.jLabel27.text")); // NOI18N

        cboSkills.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSkills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSkillsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(cboSkills, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("PriorityForm.jPanel8.TabConstraints.tabTitle"), jPanel8); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText(bundle.getString("PriorityForm.jLabel28.text")); // NOI18N

        cboResources.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboResources.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboResourcesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(cboResources, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboResources, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("PriorityForm.jPanel9.TabConstraints.tabTitle"), jPanel9); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));

        jLabel7.setText(bundle.getString("PriorityForm.jLabel7.text")); // NOI18N

        jLabel8.setText(bundle.getString("PriorityForm.jLabel8.text")); // NOI18N

        jLabel9.setText(bundle.getString("PriorityForm.jLabel9.text")); // NOI18N

        jLabel10.setText(bundle.getString("PriorityForm.jLabel10.text")); // NOI18N

        jLabel11.setText(bundle.getString("PriorityForm.jLabel11.text")); // NOI18N

        lblSelectedMetatype.setText(bundle.getString("PriorityForm.lblSelectedMetatype.text")); // NOI18N

        lblSelectedAttributes.setText(bundle.getString("PriorityForm.lblSelectedAttributes.text")); // NOI18N

        lblSelectedMagic.setText(bundle.getString("PriorityForm.lblSelectedMagic.text")); // NOI18N

        lblSelectedSkills.setText(bundle.getString("PriorityForm.lblSelectedSkills.text")); // NOI18N

        lblSelectedResources.setText(bundle.getString("PriorityForm.lblSelectedResources.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelectedMetatype, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSelectedAttributes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSelectedMagic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSelectedSkills, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSelectedResources, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblSelectedMetatype))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblSelectedAttributes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblSelectedMagic))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblSelectedSkills))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblSelectedResources))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displaySelectedMagic() {
        // Display Resonance/Magic
        if(this.selectedMagicItem == null) {
            this.lblMagic.setText("---");
            this.lblOther.setText("---");
            this.lblSkills.setText("---");
            this.lblSelectedMagic.setText("<None Yet>");
        } else {
            if(this.selectedMagicItem.getRatingMAG() > 0) {
                this.lblMagic.setText("Magie " + this.selectedMagicItem.getRatingMAG());
                if(this.selectedMagicItem.getRatingRES() > 0) {
                    this.lblMagic.setText(this.lblMagic.getText() + ", " + this.selectedMagicItem.getRatingRES());
                }
            } else if(this.selectedMagicItem.getRatingRES() > 0) {
                this.lblMagic.setText("Resonanz: " + this.selectedMagicItem.getRatingRES());
            } else {
                this.lblMagic.setText("---");
            }
            
            this.lblSelectedMagic.setText(KEY_ELF);
            
            StringBuilder sb = new StringBuilder();
            /// TODO
            if(this.selectedMagicItem.getActiveSkills() > 0) {
                sb.append(this.selectedMagicItem.getActiveSkills()).append(" Aktionsfertigkeiten (" + this.selectedMagicItem.getRatingActiveSkills() + ")");
            }
            
            if(this.selectedMagicItem.getSkillsMAG() > 0) {
                if(sb.length() > 0) { sb.append(", "); }
                sb.append(this.selectedMagicItem.getSkillsMAG()).append(" magische Fertigkeiten (").append(this.selectedMagicItem.getRatingMAG()).append(")");
            }
            
            this.lblSelectedMagic.setText(this.selectedMagicItem.getPriority().getName() + " - " + this.lblMagic.getText());
        }
        
    }
    
    private void displaySelectedMetatype() {
        if(this.selectedMetaItem != null) {
            this.lblKON.setText(this.selectedMetaItem.getMeta().getBodyDefault()+"/"+this.selectedMetaItem.getMeta().getBodyMax());
            this.lblGES.setText(this.selectedMetaItem.getMeta().getDexterityDefault()+"/"+this.selectedMetaItem.getMeta().getDexterityMax());
            this.lblSTR.setText(this.selectedMetaItem.getMeta().getStrengthDefault()+"/"+this.selectedMetaItem.getMeta().getStrengthMax());
            this.lblREA.setText(this.selectedMetaItem.getMeta().getReactionDefault()+"/"+this.selectedMetaItem.getMeta().getReactionMax());

            this.lblWIL.setText(this.selectedMetaItem.getMeta().getWillpowerDefault()+"/"+this.selectedMetaItem.getMeta().getWillpowerMax());
            this.lblCHA.setText(this.selectedMetaItem.getMeta().getCharismaDefault()+"/"+this.selectedMetaItem.getMeta().getCharismaMax());
            this.lblINT.setText(this.selectedMetaItem.getMeta().getIntuitionDefault()+"/"+this.selectedMetaItem.getMeta().getIntuitionMax());
            this.lblLOG.setText(this.selectedMetaItem.getMeta().getLogicDefault()+"/"+this.selectedMetaItem.getMeta().getLogicMax());
            this.lblEDG.setText(this.selectedMetaItem.getMeta().getEdgeDefault() + "/" + this.selectedMetaItem.getMeta().getEdgeMax());
            
            this.lblSpecialPoints.setText(String.format("You get %d points to spend for free", this.selectedMetaItem.getSpecialPoints()));
            if(this.selectedMetaItem.getMeta().getBonus() != null) {
                this.txtMetaModifiers.setText(this.selectedMetaItem.getMeta().getBonus().replace(", ", "\n"));
            }
            
            this.lblSelectedMetatype.setText(this.selectedMetaItem.getPriority().getName() + " - " + this.selectedMetaItem.getMeta().getName() + " ("
                + this.selectedMetaItem.getSpecialPoints() + ")");
        } else {
            this.lblKON.setText("-/-");
            this.lblGES.setText("-/-");
            this.lblSTR.setText("-/-");
            this.lblREA.setText("-/-");
            this.lblWIL.setText("-/-");
            this.lblCHA.setText("-/-");
            this.lblINT.setText("-/-");
            this.lblLOG.setText("-/-");
            this.lblEDG.setText("-/-");
            this.lblSpecialPoints.setText("---");
            this.txtMetaModifiers.setText(null);
            this.lblSelectedMetatype.setText("<None yet>");
        }
    }
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        final Object sel = this.jComboBox1.getSelectedItem();
        if(sel == null || !(sel instanceof PriorityMetatypeComboBoxItem)) {
            this.selectedMetaItem = null;
            this.displaySelectedMetatype();
            this.taken.put(TAKEN_META, null);
            return;
        }
        
        final PriorityMetatypeComboBoxItem item = (PriorityMetatypeComboBoxItem)sel;
        this.selection.put(jComboBox1, this.jComboBox1.getSelectedIndex());
        this.selectedMetaItem = item;
        this.displaySelectedMetatype();
        
        this.taken.put(TAKEN_META, item.getPriority());
//        this.init();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.doClose(DialogResult.CANCEL);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboAttributesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAttributesActionPerformed
        final Object sel = this.cboAttributes.getSelectedItem();
        if(sel == null || !(sel instanceof PriorityAttributeComboBoxItem)) {
            this.selectedAttributeItem = null;
            this.taken.put(TAKEN_ATTR, null);
            this.lblSelectedAttributes.setText("<None Yet>");
            return;
        }
        
        final PriorityAttributeComboBoxItem item = (PriorityAttributeComboBoxItem)sel;
        this.selectedAttributeItem = item;
        this.taken.put(TAKEN_ATTR, item.getPriority());
        this.lblSelectedAttributes.setText(item.getPriority().getName() + " - " + item.getPriority().getAttributes());
//        this.init();
    }//GEN-LAST:event_cboAttributesActionPerformed

    private void cboMagicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMagicActionPerformed
        final Object sel = this.cboMagic.getSelectedItem();
        if(sel == null || !(sel instanceof PriorityMagicComboBoxItem)) {
            this.selectedMagicItem = null;
            this.taken.put(TAKEN_MAGIC, null);
            return;
        }
        
        final PriorityMagicComboBoxItem item = (PriorityMagicComboBoxItem)sel;
        this.selectedMagicItem = item;
        this.taken.put(TAKEN_MAGIC, item.getPriority());
        this.displaySelectedMagic();
    }//GEN-LAST:event_cboMagicActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.doClose(DialogResult.OK);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboSkillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSkillsActionPerformed
        // TODO add your handling code here:
        final Object sel = this.cboSkills.getSelectedItem();
        if(sel == null || !(sel instanceof PrioritySkillComboBoxItem)) {
            this.selectedSkillsItem = null;
            this.taken.put(TAKEN_SKILLS, null);
            this.lblSelectedSkills.setText("<None Yet>");
            return;
        }
        
        final PrioritySkillComboBoxItem item = (PrioritySkillComboBoxItem)sel;
        this.selectedSkillsItem = item;
        this.taken.put(TAKEN_SKILLS, item.getPriority());
        this.lblSelectedSkills.setText(item.getPriority().getName() + " - " + item.getSkillPoints() + " / " + item.getSkillgroupPoints() );
    }//GEN-LAST:event_cboSkillsActionPerformed

    private void cboResourcesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboResourcesActionPerformed
        final Object sel = this.cboResources.getSelectedItem();
        if(sel == null || !(sel instanceof PriorityResourcelComboBoxItem)) {
            this.selectedResourceItem = null;
            this.taken.put(TAKEN_RESS, null);
            this.lblSelectedResources.setText("<None Yet>");
            return;
        }
        
        final PriorityResourcelComboBoxItem item = (PriorityResourcelComboBoxItem)sel;
        this.selectedResourceItem = item;
        this.taken.put(TAKEN_RESS, item.getPriority());
        this.lblSelectedResources.setText(String.format("%s - %d \u00A5", new Object[] { item.getPriority().getName(), item.getResources()}));
    }//GEN-LAST:event_cboResourcesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboAttributes;
    private javax.swing.JComboBox cboMagic;
    private javax.swing.JComboBox cboResources;
    private javax.swing.JComboBox cboSkills;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCHA;
    private javax.swing.JLabel lblEDG;
    private javax.swing.JLabel lblGES;
    private javax.swing.JLabel lblINT;
    private javax.swing.JLabel lblKON;
    private javax.swing.JLabel lblLOG;
    private javax.swing.JLabel lblMagic;
    private javax.swing.JLabel lblOther;
    private javax.swing.JLabel lblREA;
    private javax.swing.JLabel lblSTR;
    private javax.swing.JLabel lblSelectedAttributes;
    private javax.swing.JLabel lblSelectedMagic;
    private javax.swing.JLabel lblSelectedMetatype;
    private javax.swing.JLabel lblSelectedResources;
    private javax.swing.JLabel lblSelectedSkills;
    private javax.swing.JLabel lblSkills;
    private javax.swing.JLabel lblSpecialPoints;
    private javax.swing.JLabel lblWIL;
    private javax.swing.JTextPane txtMetaModifiers;
    // End of variables declaration//GEN-END:variables
}
