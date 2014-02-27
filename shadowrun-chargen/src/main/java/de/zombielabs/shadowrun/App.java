package de.zombielabs.shadowrun;

import de.zombielabs.shadowrun.common.data.Attribute;
import de.zombielabs.shadowrun.common.data.DataProvider;
import de.zombielabs.shadowrun.common.data.Priority;
import de.zombielabs.shadowrun.common.data.DataProviderListener;
import de.zombielabs.shadowrun.common.data.Flaw;
import de.zombielabs.shadowrun.common.data.Metatype;
import de.zombielabs.shadowrun.common.data.Perk;
import de.zombielabs.shadowrun.common.data.Skill;
import de.zombielabs.shadowrun.common.data.Skillgroup;
import de.zombielabs.shadowrun.common.data.gear.Gear;
import de.zombielabs.shadowrun.common.data.sql.V5SQLDataProvider;
import de.zombielabs.shadowrun.forms.MainForm;
import de.zombielabs.shadowrun.forms.Splash;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class App implements DataProviderListener {
    
    public Splash splash = null;
    private DataProvider data;
    
    public void onAttributeLoaded(Attribute attribute) {
        this.splash.display("Loading attribute " + attribute.getName());
        System.out.println("Loaded Attribute " + attribute.getName());
    }

    public void onLoadingFinished() {
        System.out.println("LOADING FINISHED");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainForm form = new MainForm(splash, data);
                form.takeControl();
            }
        });
        
    }

    public void onGearItemLoaded(Gear gear) {
        System.out.println("Gear item loaded: " + gear);
        this.splash.display("Loaded gear item '" + gear + "'");
    }

    public void onLoadingFailed(Exception ex) {
        System.err.println("Data loading failed: " + ex.getMessage());
        ex.printStackTrace(System.err);
    }

    public void onPerkLoaded(Perk perk) {
        this.splash.display("Loaded perk '" + perk.getName() + "'");
        System.out.println("PERK: " + perk);
    }
    
    public void onFlawLoaded(Flaw flaw) {
        this.splash.display("Loaded flaw '" + flaw.getName() + "'");
        System.out.println("FLAW: " + flaw);
    }

    public void onSkillLoaded(Skill skill) {
        this.splash.display("Loading skill " + skill.getName() + " (parent Attribute " + skill.getParent().getName() + ")");
        System.out.println("Loading skill " + skill.getName() + " (parent Attribute " + skill.getParent().getName() + ")");
    }

    public void onSkillGroupLoaded(Skillgroup group) {
        this.splash.display("Loading skill group " + group.getName());
        System.out.println("Loaded skill group " + group.getName());
        for(final Skill s : group.getSkills()) {
            System.out.println("  Skill: " + s.getName());
        }
    }

    public void onLoadingData() {
        System.out.println("Data loading started");
    }

    public void onMetatypeLoaded(Metatype metatype) {
        this.splash.display("Loading metatype " + metatype.getName());
        System.out.println("Loaded metatype: " + metatype.getName());
    }
    
    public void onPriorityLoaded(Priority priority) {
        this.splash.display("Loaded priority " + priority.getName());
    }
    
    
    
    public void loadData() throws SQLException {
        this.data = new V5SQLDataProvider(new File("test.db"));
        this.data.addListener(this);
        this.data.loadAll();
    }
    
    public App() {
        splash = new Splash(null, false);
        splash.setVisible(true);
    }
    
    private static void loadLAF() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public static void main( String[] args ) throws SQLException, IOException {
        loadLAF();
        App app = new App();
        app.loadData();
        
        
    }
}
