/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.zombielabs.shadowrun.forms.data;

import de.zombielabs.shadowrun.common.data.Priority;

/**
 *
 * @author Steps
 */
public class PriorityComboBoxItem extends ComboBoxItem {
    private Priority priority;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority prio) {
        this.priority = prio;
    }
    
    public PriorityComboBoxItem(Priority prio) {
        this.priority = prio;
    }
}
