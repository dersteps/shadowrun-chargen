/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Steps
 */
public class CategoryListModel extends DefaultListModel {
    
    private HashMap<Category, List<Object>> map;
    
    public CategoryListModel(String... categories) {
        this.map = new HashMap<Category, List<Object>>();
        
        for(final String category : categories) {
            this.map.put(new Category(category), new ArrayList<Object>());
        }
    }
    
    public CategoryListModel() {
        this(new String[] {});
    }
    
    public void add(Category category, Object value) {
        if(!this.map.containsKey(category)) {
            this.map.put(category, new ArrayList<Object>());
        }
        
        this.map.get(category).add(value);
    }
    
    
    
    
    
}
