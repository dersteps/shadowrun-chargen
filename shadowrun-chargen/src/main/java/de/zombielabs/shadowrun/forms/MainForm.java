/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms;

import de.zombielabs.shadowrun.common.data.DataProvider;
import de.zombielabs.shadowrun.common.data.Flaw;
import de.zombielabs.shadowrun.common.data.Perk;
import de.zombielabs.shadowrun.common.data.gear.Drug;
import de.zombielabs.shadowrun.common.data.gear.Toxin;
import de.zombielabs.shadowrun.forms.data.PriorityAttributeComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityMagicComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityMetatypeComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PriorityResourceComboBoxItem;
import de.zombielabs.shadowrun.forms.data.PrioritySkillComboBoxItem;
import de.zombielabs.shadowrun.forms.db.FlawEditor;
import de.zombielabs.shadowrun.forms.db.MetatypesEditor;
import de.zombielabs.shadowrun.forms.db.PerkEditor;
import de.zombielabs.shadowrun.forms.db.PrioritiesEditor;
import de.zombielabs.shadowrun.forms.renderer.Category;
import de.zombielabs.shadowrun.forms.renderer.EdgeFlawListRenderer;
import de.zombielabs.shadowrun.forms.renderer.GearNode;
import de.zombielabs.shadowrun.forms.renderer.GearTreeRenderer;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Steps
 */
public class MainForm extends javax.swing.JFrame {

    private final Splash splash;
    
    private final DataProvider data;
    
    private final List<Perk> characterPerks = new ArrayList<Perk>();
    private final List<Flaw> characterFlaws = new ArrayList<Flaw>();
    
    private static final String PRIO_META = "METATYPE";
    private static final String PRIO_ATTR = "ATTRIBUTES";
    private static final String PRIO_MAGIC = "MAGIC";
    private static final String PRIO_SKILLS = "SKILLS";
    private static final String PRIO_RESS = "RESOURCES";
    
    private final HashMap<String, PriorityComboBoxItem> priorities;
    
    /**
     * Limits as per selected prios
     */
    private int attributePoints = 0;
    
    
    
    
    /**
     * Creates new form MainForm
     * @param splash the loading screen to close after initialization
     * @param data an instance of DataProvider that provides the data
     */
    public MainForm(Splash splash, DataProvider data) {
       initComponents();
       this.disableForm();
       this.splash = splash;
       this.data = data;
       this.priorities = new HashMap<String, PriorityComboBoxItem>();
    }
    
    public void takeControl() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        if(this.splash != null) {
            splash.setVisible(false);
            splash.dispose();
        }
        
        this.loadPerksAndFlaws();
        this.loadGear();
        this.listCharacterEdgesFlaws.setCellRenderer(new EdgeFlawListRenderer());
        this.listCharacterEdgesFlaws.setModel(new DefaultListModel());
        
        this.listCharacterEdgesFlaws.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    DefaultListModel dlm = (DefaultListModel)listCharacterEdgesFlaws.getModel();
                    
                    for(int i=0; i<listCharacterEdgesFlaws.getModel().getSize(); i++) {
                        dlm.setElementAt(dlm.getElementAt(i), i);
                    }
                }
                
                
                if(listFlaws.getSelectedValue() != null) {
                    if(listFlaws.getSelectedValue() instanceof Flaw) {
                        Flaw f = (Flaw)listFlaws.getSelectedValue();
                        lblEdgeFlawKarma.setText("+" + f.getCost() + " Punkte");
                        lblEdgeFlawKarma.setForeground(new Color(0xc0, 0x00, 0x00, 0xff));
                        txtEdgeFlawDescription.setText(f.getDescription());
                    } else if(listFlaws.getSelectedValue() instanceof Perk) {
                        Perk f = (Perk)listPerks.getSelectedValue();
                        lblEdgeFlawKarma.setText("-" + f.getCost() + " Punkte");
                        lblEdgeFlawKarma.setForeground(new Color(0x00, 0xc0, 0x00, 0xff));
                        txtEdgeFlawDescription.setText(f.getDescription());
                    }
                }
                
                
            }
        });
        
        this.listFlaws.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if(listFlaws.getSelectedValue() != null) {
                    Flaw f = (Flaw)listFlaws.getSelectedValue();
                    lblEdgeFlawKarma.setText("+" + f.getCost() + " Punkte");
                    lblEdgeFlawKarma.setForeground(new Color(0xc0, 0x00, 0x00, 0xff));
                    txtEdgeFlawDescription.setText(f.getDescription());
                }
            }
        });
        
        this.listPerks.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if(listPerks.getSelectedValue() != null) {
                    Perk f = (Perk)listPerks.getSelectedValue();
                    lblEdgeFlawKarma.setText("-" + f.getCost() + " Punkte");
                    lblEdgeFlawKarma.setForeground(new Color(0x00, 0xc0, 0x00, 0xff));
                    txtEdgeFlawDescription.setText(f.getDescription());
                }
            }
        });
        
        ///TODO: Make sure the max amount of attribute points is not exceeded
        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                final JSpinner source = (JSpinner)e.getSource();
                attributePoints -= (Integer)txtKON.getValue();
                System.out.println("Attribute points: " + attributePoints);
                
            }
        };

        txtKON.addChangeListener(listener);
        
//         list.addListSelectionListener(new ListSelectionListener(){  
//      public void valueChanged(ListSelectionEvent lse){  
//        if(lse.getValueIsAdjusting() == false) list.setCellRenderer(new MyRenderer());}});//reset  
//        /*//this block comes from this recent post, and would be better 
//          //http://www.coderanch.com/t/336056/GUI/java/Force-ListCellRenderer-update-GUI 
//          //comment out the above reset line, and uncomment this block 
//        if(lse.getValueIsAdjusting() == false) 
//        { 
//          for(int x = 0; x < listModel.size(); x++) 
//          { 
//            listModel.setElementAt(listModel.getElementAt(x),x); 
//          } 
//        }}}); 
//        */  
//  }  
        
    }
    
    private void loadPerksAndFlaws() {
        DefaultListModel lmPerks = new DefaultListModel();
        for(final Perk p : this.data.getPerks()) {
            lmPerks.addElement(p);
        }
        this.listPerks.setModel(lmPerks);
        
        DefaultListModel lmFlaws = new DefaultListModel();
        for(final Flaw f : this.data.getFlaws()) {
            lmFlaws.addElement(f);
        }
        this.listFlaws.setModel(lmFlaws);
        
    }

    private void loadGear() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Ausrüstung");
        DefaultTreeModel model = new DefaultTreeModel(root);
        this.jTree1.setCellRenderer(new GearTreeRenderer());
        
        // Add Toxins
        DefaultMutableTreeNode toxins = new DefaultMutableTreeNode("Toxine");
        
        for(final Toxin tox : this.data.getToxins()) {
            GearNode node = new GearNode(tox);
            toxins.add(node);
        }
        
        DefaultMutableTreeNode drugs = new DefaultMutableTreeNode("Drogen");
        for(final Drug drug : this.data.getDrugs()) {
            GearNode node = new GearNode(drug);
            drugs.add(node);
        }
        
        root.add(toxins);
        root.add(drugs);
        this.jTree1.setModel(model);
        
        
    }
    
    private void disableForm() {
        this.updateFormEnabled(false, null);
    }
    
    private void enableForm() {
        this.updateFormEnabled(true, null);
    }
    
    private void updateFormEnabled(boolean enable, JComponent comp) {
        if(comp == null) {
            comp = this.splitMain;
        }
        
        comp.setEnabled(enable);
        
        final Component[] subs = comp.getComponents();
        if(subs != null && subs.length > 0) {
            for(final Component sub : comp.getComponents()) {
                sub.setEnabled(false);
                if(sub instanceof JComponent) {
                    final JComponent jsub = (JComponent)sub;
                    updateFormEnabled(enable, jsub);
                }
            }
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
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        splitMain = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPrioMeta = new javax.swing.JLabel();
        lblPrioAttributes = new javax.swing.JLabel();
        lblPrioMagic = new javax.swing.JLabel();
        lblPrioSkills = new javax.swing.JLabel();
        lblPrioResources = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblSelectedMeta = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblKON = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblGES = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblREA = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSTR = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblCHA = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblINT = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblLOG = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblWIL = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblEDG = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblMAG = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblRES = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblAttributes = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblSkills = new javax.swing.JLabel();
        lblSkillgroups = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblResources = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lblKarma = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtCharName = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtCharAlias = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtKON = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtGES = new javax.swing.JSpinner();
        jPanel7 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtREA = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtSTR = new javax.swing.JSpinner();
        jPanel9 = new javax.swing.JPanel();
        Charisma = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtCHA = new javax.swing.JSpinner();
        jPanel10 = new javax.swing.JPanel();
        Intuition = new javax.swing.JLabel();
        lblCharINT = new javax.swing.JLabel();
        txtINT = new javax.swing.JSpinner();
        jPanel11 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtLOG = new javax.swing.JSpinner();
        jPanel12 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtWIL = new javax.swing.JSpinner();
        jPanel13 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtEDG = new javax.swing.JSpinner();
        jPanel14 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtMAG = new javax.swing.JSpinner();
        jPanel15 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtRES = new javax.swing.JSpinner();
        jPanel17 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        tabPerksFlaws = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPerks = new javax.swing.JList();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listFlaws = new javax.swing.JList();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listCharacterEdgesFlaws = new javax.swing.JList();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblEdgeFlawKarma = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtEdgeFlawDescription = new javax.swing.JTextPane();
        jPanel23 = new javax.swing.JPanel();
        jSplitPane5 = new javax.swing.JSplitPane();
        jSplitPane4 = new javax.swing.JSplitPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/zombielabs/shadowrun/forms/MainFormBundle"); // NOI18N
        setTitle(bundle.getString("MainForm.title")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        jButton1.setText(bundle.getString("MainForm.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        splitMain.setDividerLocation(250);

        jLabel1.setText(bundle.getString("MainForm.jLabel1.text")); // NOI18N

        jLabel2.setText(bundle.getString("MainForm.jLabel2.text")); // NOI18N

        jLabel3.setText(bundle.getString("MainForm.jLabel3.text")); // NOI18N

        jLabel4.setText(bundle.getString("MainForm.jLabel4.text")); // NOI18N

        jLabel5.setText(bundle.getString("MainForm.jLabel5.text")); // NOI18N

        lblPrioMeta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrioMeta.setText(bundle.getString("MainForm.lblPrioMeta.text")); // NOI18N

        lblPrioAttributes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrioAttributes.setText(bundle.getString("MainForm.lblPrioAttributes.text")); // NOI18N

        lblPrioMagic.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrioMagic.setText(bundle.getString("MainForm.lblPrioMagic.text")); // NOI18N

        lblPrioSkills.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrioSkills.setText(bundle.getString("MainForm.lblPrioSkills.text")); // NOI18N

        lblPrioResources.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrioResources.setText(bundle.getString("MainForm.lblPrioResources.text")); // NOI18N

        jLabel6.setText(bundle.getString("MainForm.jLabel6.text")); // NOI18N

        lblSelectedMeta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSelectedMeta.setText(bundle.getString("MainForm.lblSelectedMeta.text")); // NOI18N

        jLabel7.setText(bundle.getString("MainForm.jLabel7.text")); // NOI18N

        lblKON.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKON.setText(bundle.getString("MainForm.lblKON.text")); // NOI18N

        jLabel8.setText(bundle.getString("MainForm.jLabel8.text")); // NOI18N

        lblGES.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGES.setText(bundle.getString("MainForm.lblGES.text")); // NOI18N

        jLabel9.setText(bundle.getString("MainForm.jLabel9.text")); // NOI18N

        lblREA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblREA.setText(bundle.getString("MainForm.lblREA.text")); // NOI18N

        jLabel10.setText(bundle.getString("MainForm.jLabel10.text")); // NOI18N

        lblSTR.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSTR.setText(bundle.getString("MainForm.lblSTR.text")); // NOI18N

        jLabel11.setText(bundle.getString("MainForm.jLabel11.text")); // NOI18N

        lblCHA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCHA.setText(bundle.getString("MainForm.lblCHA.text")); // NOI18N

        jLabel12.setText(bundle.getString("MainForm.jLabel12.text")); // NOI18N

        lblINT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblINT.setText(bundle.getString("MainForm.lblINT.text")); // NOI18N

        jLabel13.setText(bundle.getString("MainForm.jLabel13.text")); // NOI18N

        lblLOG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLOG.setText(bundle.getString("MainForm.lblLOG.text")); // NOI18N

        jLabel14.setText(bundle.getString("MainForm.jLabel14.text")); // NOI18N

        lblWIL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblWIL.setText(bundle.getString("MainForm.lblWIL.text")); // NOI18N

        jLabel15.setText(bundle.getString("MainForm.jLabel15.text")); // NOI18N

        lblEDG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEDG.setText(bundle.getString("MainForm.lblEDG.text")); // NOI18N

        jLabel16.setText(bundle.getString("MainForm.jLabel16.text")); // NOI18N

        lblMAG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMAG.setText(bundle.getString("MainForm.lblMAG.text")); // NOI18N

        jLabel17.setText(bundle.getString("MainForm.jLabel17.text")); // NOI18N

        lblRES.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRES.setText(bundle.getString("MainForm.lblRES.text")); // NOI18N

        jLabel18.setText(bundle.getString("MainForm.jLabel18.text")); // NOI18N

        lblAttributes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAttributes.setText(bundle.getString("MainForm.lblAttributes.text")); // NOI18N

        jLabel19.setText(bundle.getString("MainForm.jLabel19.text")); // NOI18N

        lblSkills.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSkills.setText(bundle.getString("MainForm.lblSkills.text")); // NOI18N

        lblSkillgroups.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSkillgroups.setText(bundle.getString("MainForm.lblSkillgroups.text")); // NOI18N

        jLabel20.setText(bundle.getString("MainForm.jLabel20.text")); // NOI18N

        lblResources.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblResources.setText(bundle.getString("MainForm.lblResources.text")); // NOI18N

        jLabel21.setText(bundle.getString("MainForm.jLabel21.text")); // NOI18N

        jLabel50.setText(bundle.getString("MainForm.jLabel50.text")); // NOI18N

        lblKarma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKarma.setText(bundle.getString("MainForm.lblKarma.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrioResources, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrioMagic, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrioSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrioMeta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrioAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblResources, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSkillgroups, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSelectedMeta, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKON, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblGES, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblREA, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSTR, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCHA, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblINT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLOG, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblWIL, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEDG, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMAG, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRES, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblKarma, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblPrioMeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblPrioAttributes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblPrioMagic))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblPrioSkills))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblPrioResources))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblSelectedMeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblKON))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblGES))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblREA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblSTR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblCHA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblINT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblLOG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblWIL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblEDG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblMAG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblRES))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblAttributes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(lblSkills))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblSkillgroups))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblResources))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(lblKarma))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("MainForm.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        splitMain.setLeftComponent(jTabbedPane1);

        jLabel24.setText(bundle.getString("MainForm.jLabel24.text")); // NOI18N

        txtCharName.setText(bundle.getString("MainForm.txtCharName.text")); // NOI18N

        jLabel25.setText(bundle.getString("MainForm.jLabel25.text")); // NOI18N

        txtCharAlias.setText(bundle.getString("MainForm.txtCharAlias.text")); // NOI18N

        jLabel27.setText(bundle.getString("MainForm.jLabel27.text")); // NOI18N

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jFormattedTextField1.setText(bundle.getString("MainForm.jFormattedTextField1.text")); // NOI18N

        jLabel28.setText(bundle.getString("MainForm.jLabel28.text")); // NOI18N

        jRadioButton1.setText(bundle.getString("MainForm.jRadioButton1.text")); // NOI18N

        jRadioButton2.setText(bundle.getString("MainForm.jRadioButton2.text")); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButton1)
                .addComponent(jRadioButton2))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCharAlias)
                    .addComponent(txtCharName)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(465, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtCharName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtCharAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(388, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(bundle.getString("MainForm.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText(bundle.getString("MainForm.jLabel22.text")); // NOI18N

        jLabel23.setText(bundle.getString("MainForm.jLabel23.text")); // NOI18N

        jLabel26.setText(bundle.getString("MainForm.jLabel26.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKON, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel23)
                .addComponent(jLabel26)
                .addComponent(txtKON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel29.setText(bundle.getString("MainForm.jLabel29.text")); // NOI18N

        jLabel30.setText(bundle.getString("MainForm.jLabel30.text")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGES, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel29)
                .addComponent(jLabel30)
                .addComponent(txtGES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel31.setText(bundle.getString("MainForm.jLabel31.text")); // NOI18N

        jLabel32.setText(bundle.getString("MainForm.jLabel32.text")); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtREA, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel31)
                .addComponent(jLabel32)
                .addComponent(txtREA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel33.setText(bundle.getString("MainForm.jLabel33.text")); // NOI18N

        jLabel34.setText(bundle.getString("MainForm.jLabel34.text")); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSTR, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel33)
                .addComponent(jLabel34)
                .addComponent(txtSTR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Charisma.setText(bundle.getString("MainForm.Charisma.text")); // NOI18N

        jLabel36.setText(bundle.getString("MainForm.jLabel36.text")); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(Charisma, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCHA, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Charisma)
                .addComponent(jLabel36)
                .addComponent(txtCHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Intuition.setText(bundle.getString("MainForm.Intuition.text")); // NOI18N

        lblCharINT.setText(bundle.getString("MainForm.lblCharINT.text")); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(Intuition, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCharINT, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtINT, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Intuition)
                .addComponent(lblCharINT)
                .addComponent(txtINT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel39.setText(bundle.getString("MainForm.jLabel39.text")); // NOI18N

        jLabel40.setText(bundle.getString("MainForm.jLabel40.text")); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLOG, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel39)
                .addComponent(jLabel40)
                .addComponent(txtLOG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel41.setText(bundle.getString("MainForm.jLabel41.text")); // NOI18N

        jLabel42.setText(bundle.getString("MainForm.jLabel42.text")); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtWIL, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel41)
                .addComponent(jLabel42)
                .addComponent(txtWIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel43.setText(bundle.getString("MainForm.jLabel43.text")); // NOI18N

        jLabel44.setText(bundle.getString("MainForm.jLabel44.text")); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEDG, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel43)
                .addComponent(jLabel44)
                .addComponent(txtEDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel45.setText(bundle.getString("MainForm.jLabel45.text")); // NOI18N

        jLabel46.setText(bundle.getString("MainForm.jLabel46.text")); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMAG, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel45)
                .addComponent(jLabel46)
                .addComponent(txtMAG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel47.setText(bundle.getString("MainForm.jLabel47.text")); // NOI18N

        jLabel48.setText(bundle.getString("MainForm.jLabel48.text")); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRES, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel47)
                .addComponent(jLabel48)
                .addComponent(txtRES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(185, 185, 185))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(527, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(bundle.getString("MainForm.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jSplitPane3.setDividerLocation(300);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setResizeWeight(1.0);

        jSplitPane2.setDividerLocation(200);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        listPerks.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listPerks);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );

        tabPerksFlaws.addTab(bundle.getString("MainForm.jPanel18.TabConstraints.tabTitle"), jPanel18); // NOI18N

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        listFlaws.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listFlaws);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );

        tabPerksFlaws.addTab(bundle.getString("MainForm.jPanel19.TabConstraints.tabTitle"), jPanel19); // NOI18N

        jSplitPane2.setLeftComponent(tabPerksFlaws);

        jButton2.setText(bundle.getString("MainForm.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(bundle.getString("MainForm.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        listCharacterEdgesFlaws.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listCharacterEdgesFlaws);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel20);

        jSplitPane3.setLeftComponent(jSplitPane2);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText(bundle.getString("MainForm.jLabel35.text")); // NOI18N

        jLabel37.setText(bundle.getString("MainForm.jLabel37.text")); // NOI18N

        lblEdgeFlawKarma.setText(bundle.getString("MainForm.lblEdgeFlawKarma.text")); // NOI18N

        jLabel49.setText(bundle.getString("MainForm.jLabel49.text")); // NOI18N

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setViewportView(txtEdgeFlawDescription);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel24Layout.createSequentialGroup()
                                .addComponent(lblEdgeFlawKarma, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 545, Short.MAX_VALUE))
                            .addComponent(jScrollPane4)))
                    .addComponent(jLabel35))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lblEdgeFlawKarma))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jSplitPane3.setRightComponent(jPanel22);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane2.addTab(bundle.getString("MainForm.jPanel17.TabConstraints.tabTitle"), jPanel17); // NOI18N

        jSplitPane5.setDividerLocation(400);
        jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane5.setResizeWeight(1.0);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane5.setViewportView(jTree1);

        jSplitPane4.setLeftComponent(jScrollPane5);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        jSplitPane4.setRightComponent(jScrollPane6);

        jSplitPane5.setLeftComponent(jSplitPane4);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane7.setViewportView(jTextArea3);

        jSplitPane5.setRightComponent(jScrollPane7);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab(bundle.getString("MainForm.jPanel23.TabConstraints.tabTitle"), jPanel23); // NOI18N

        splitMain.setRightComponent(jTabbedPane2);

        jMenu1.setText(bundle.getString("MainForm.jMenu1.text")); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setText(bundle.getString("MainForm.jMenu2.text")); // NOI18N

        jMenuItem1.setText(bundle.getString("MainForm.jMenuItem1.text")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText(bundle.getString("MainForm.jMenuItem2.text")); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText(bundle.getString("MainForm.jMenuItem3.text")); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText(bundle.getString("MainForm.jMenuItem4.text")); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(splitMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitMain)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PriorityForm p = new PriorityForm(this, true, this.data);
        
        p.setSelectedAttributeItem((PriorityAttributeComboBoxItem)this.priorities.get(PRIO_ATTR));
        p.setSelectedMagicItem((PriorityMagicComboBoxItem)this.priorities.get(PRIO_MAGIC));
        p.setSelectedMetaItem((PriorityMetatypeComboBoxItem)this.priorities.get(PRIO_META));
        p.setSelectedResourceItem((PriorityResourceComboBoxItem)this.priorities.get(PRIO_RESS));
        p.setSelectedSkillsItem((PrioritySkillComboBoxItem)this.priorities.get(PRIO_SKILLS));
        
        p.takeControl();
        
        if(p.getResult().equals(DialogResult.OK)) {
            
            this.priorities.put(PRIO_META, p.getSelectedMetaItem());
            this.priorities.put(PRIO_ATTR, p.getSelectedAttributeItem());
            this.priorities.put(PRIO_MAGIC, p.getSelectedMagicItem());
            this.priorities.put(PRIO_SKILLS, p.getSelectedSkillsItem());
            this.priorities.put(PRIO_RESS, p.getSelectedResourceItem());
            
            this.attributePoints = p.getSelectedAttributeItem().getAttributePoints();
            
            
            this.lblPrioMeta.setText(p.getSelectedMetaItem().getPriority().getName());
            this.lblPrioAttributes.setText(p.getSelectedAttributeItem().getPriority().getName());
            
            this.lblPrioMagic.setText(p.getSelectedMagicItem().getPriority().getName());
            this.lblPrioSkills.setText(p.getSelectedSkillsItem().getPriority().getName());
            this.lblPrioResources.setText(p.getSelectedResourceItem().getPriority().getName());
            
            this.lblSelectedMeta.setText(p.getSelectedMetaItem().getMeta().getName());
            
            this.lblKON.setText(p.getSelectedMetaItem().getMeta().getBodyDefault() + " / " + p.getSelectedMetaItem().getMeta().getBodyMax());
            this.lblGES.setText(p.getSelectedMetaItem().getMeta().getDexterityDefault() + " / " + p.getSelectedMetaItem().getMeta().getDexterityMax());
            this.lblREA.setText(p.getSelectedMetaItem().getMeta().getReactionDefault() + " / " + p.getSelectedMetaItem().getMeta().getReactionMax());
            this.lblSTR.setText(p.getSelectedMetaItem().getMeta().getStrengthDefault() + " / " + p.getSelectedMetaItem().getMeta().getStrengthMax());
            
            this.lblCHA.setText(p.getSelectedMetaItem().getMeta().getCharismaDefault() + " / " + p.getSelectedMetaItem().getMeta().getCharismaMax());
            this.lblINT.setText(p.getSelectedMetaItem().getMeta().getIntuitionDefault() + " / " + p.getSelectedMetaItem().getMeta().getIntuitionMax());
            this.lblLOG.setText(p.getSelectedMetaItem().getMeta().getLogicDefault() + " / " + p.getSelectedMetaItem().getMeta().getLogicMax());
            this.lblWIL.setText(p.getSelectedMetaItem().getMeta().getWillpowerDefault() + " / " + p.getSelectedMetaItem().getMeta().getWillpowerMax());
            
            this.lblEDG.setText(p.getSelectedMetaItem().getMeta().getEdgeDefault() + " / " + p.getSelectedMetaItem().getMeta().getEdgeMax());
            
            this.lblMAG.setText(p.getSelectedMagicItem().getRatingMAG()+"");
            this.lblRES.setText(p.getSelectedMagicItem().getRatingRES()+"");
            
            this.lblAttributes.setText(p.getSelectedAttributeItem().getAttributePoints() + " Punkte");
            this.lblSkills.setText(p.getSelectedSkillsItem().getSkillPoints() + " Punkte");
            this.lblSkillgroups.setText(p.getSelectedSkillsItem().getSkillgroupPoints() + " Punkte");
            this.lblResources.setText(String.format("%d \u00A5", new Object[] { p.getSelectedResourceItem().getResources() }));
            
            this.txtKON.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getBodyDefault(), p.getSelectedMetaItem().getMeta().getBodyDefault(), p.getSelectedMetaItem().getMeta().getBodyMax(), 1));
            this.txtGES.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getDexterityDefault(), p.getSelectedMetaItem().getMeta().getDexterityDefault(), p.getSelectedMetaItem().getMeta().getDexterityMax(), 1));
            this.txtREA.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getReactionDefault(), p.getSelectedMetaItem().getMeta().getReactionDefault(), p.getSelectedMetaItem().getMeta().getReactionMax(), 1));
            this.txtSTR.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getStrengthDefault(), p.getSelectedMetaItem().getMeta().getStrengthDefault(), p.getSelectedMetaItem().getMeta().getStrengthMax(), 1));
            
            this.txtCHA.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getCharismaDefault(), p.getSelectedMetaItem().getMeta().getCharismaDefault(), p.getSelectedMetaItem().getMeta().getCharismaMax(), 1));
            this.txtINT.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getIntuitionDefault(), p.getSelectedMetaItem().getMeta().getIntuitionDefault(), p.getSelectedMetaItem().getMeta().getIntuitionMax(), 1));
            this.txtLOG.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getLogicDefault(), p.getSelectedMetaItem().getMeta().getLogicDefault(), p.getSelectedMetaItem().getMeta().getLogicMax(), 1));
            this.txtWIL.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getWillpowerDefault(), p.getSelectedMetaItem().getMeta().getWillpowerDefault(), p.getSelectedMetaItem().getMeta().getWillpowerMax(), 1));
            
            this.txtEDG.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getEdgeDefault(), p.getSelectedMetaItem().getMeta().getEdgeDefault(), p.getSelectedMetaItem().getMeta().getEdgeMax(), 1));
            this.txtMAG.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getMagicDefault(), p.getSelectedMetaItem().getMeta().getMagicDefault(), p.getSelectedMetaItem().getMeta().getMagicMax(), 1));
            this.txtRES.setModel(new SpinnerNumberModel(p.getSelectedMetaItem().getMeta().getResonanceDefault(), p.getSelectedMetaItem().getMeta().getResonanceDefault(), p.getSelectedMetaItem().getMeta().getResonanceMax(), 1));
            
            this.enableForm();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        PrioritiesEditor e = new PrioritiesEditor(this, true, this.data);
        e.takeControl();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private static final int TAB_PERKS = 0;
    private static final int TAB_FLAWS = 1;
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if(this.tabPerksFlaws.getSelectedIndex() == TAB_PERKS) {
            if(this.listPerks.getSelectedValue() != null) {
                if(!this.characterPerks.contains((Perk)this.listPerks.getSelectedValue())) {
                    this.characterPerks.add((Perk)this.listPerks.getSelectedValue());
                }
            }
        }
        
        if(this.tabPerksFlaws.getSelectedIndex() == TAB_FLAWS) {
            if(this.listFlaws.getSelectedValue() != null) {
                this.characterFlaws.add((Flaw)this.listFlaws.getSelectedValue());
            }
        }
        
        DefaultListModel dlm = new DefaultListModel();
        dlm.addElement(new Category("Vorteile"));
        for(final Perk perk : this.characterPerks) {
            dlm.addElement(perk);
        }
        
        dlm.addElement(new Category("Nachteile"));
        for(final Flaw perk : this.characterFlaws) {
            dlm.addElement(perk);
        }
        this.listCharacterEdgesFlaws.setModel(dlm);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        PerkEditor p = new PerkEditor(this, true, data);
        p.takeControl();
        if(p.getResult().equals(DialogResult.OK)) {
            try {
                this.data.reloadPerks();
                this.loadPerksAndFlaws();
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(this.listCharacterEdgesFlaws.getSelectedValue() != null) {
            DefaultListModel dlm = (DefaultListModel)this.listCharacterEdgesFlaws.getModel();
            final Object sel = this.listCharacterEdgesFlaws.getSelectedValue();
            if(sel instanceof Flaw) {
                Flaw f = (Flaw)sel;
                this.characterFlaws.remove(f);
                dlm.remove(this.listCharacterEdgesFlaws.getSelectedIndex());
            } else if(sel instanceof Perk) {
                Perk f = (Perk)sel;
                this.characterPerks.remove(f);
                dlm.remove(this.listCharacterEdgesFlaws.getSelectedIndex());
            }
            
            this.listCharacterEdgesFlaws.clearSelection();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FlawEditor p = new FlawEditor(this, true, data);
        p.takeControl();
        if(p.getResult().equals(DialogResult.OK)) {
            try {
                this.data.reloadFlaws();
                this.loadPerksAndFlaws();
            } catch (SQLException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        MetatypesEditor edit = new MetatypesEditor(this, true, data);
        edit.takeControl();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Charisma;
    private javax.swing.JLabel Intuition;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lblAttributes;
    private javax.swing.JLabel lblCHA;
    private javax.swing.JLabel lblCharINT;
    private javax.swing.JLabel lblEDG;
    private javax.swing.JLabel lblEdgeFlawKarma;
    private javax.swing.JLabel lblGES;
    private javax.swing.JLabel lblINT;
    private javax.swing.JLabel lblKON;
    private javax.swing.JLabel lblKarma;
    private javax.swing.JLabel lblLOG;
    private javax.swing.JLabel lblMAG;
    private javax.swing.JLabel lblPrioAttributes;
    private javax.swing.JLabel lblPrioMagic;
    private javax.swing.JLabel lblPrioMeta;
    private javax.swing.JLabel lblPrioResources;
    private javax.swing.JLabel lblPrioSkills;
    private javax.swing.JLabel lblREA;
    private javax.swing.JLabel lblRES;
    private javax.swing.JLabel lblResources;
    private javax.swing.JLabel lblSTR;
    private javax.swing.JLabel lblSelectedMeta;
    private javax.swing.JLabel lblSkillgroups;
    private javax.swing.JLabel lblSkills;
    private javax.swing.JLabel lblWIL;
    private javax.swing.JList listCharacterEdgesFlaws;
    private javax.swing.JList listFlaws;
    private javax.swing.JList listPerks;
    private javax.swing.JSplitPane splitMain;
    private javax.swing.JTabbedPane tabPerksFlaws;
    private javax.swing.JSpinner txtCHA;
    private javax.swing.JTextField txtCharAlias;
    private javax.swing.JTextField txtCharName;
    private javax.swing.JSpinner txtEDG;
    private javax.swing.JTextPane txtEdgeFlawDescription;
    private javax.swing.JSpinner txtGES;
    private javax.swing.JSpinner txtINT;
    private javax.swing.JSpinner txtKON;
    private javax.swing.JSpinner txtLOG;
    private javax.swing.JSpinner txtMAG;
    private javax.swing.JSpinner txtREA;
    private javax.swing.JSpinner txtRES;
    private javax.swing.JSpinner txtSTR;
    private javax.swing.JSpinner txtWIL;
    // End of variables declaration//GEN-END:variables
}
