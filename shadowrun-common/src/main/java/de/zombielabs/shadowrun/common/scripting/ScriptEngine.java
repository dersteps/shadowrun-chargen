/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.zombielabs.shadowrun.common.scripting;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

/**
 *
 * @author smatyba
 */
public class ScriptEngine {
    private File workspace;
    private GroovyScriptEngine engine;
    
    public ScriptEngine(File workspace) throws IOException {
        this.workspace = workspace;
        this.engine = new GroovyScriptEngine(workspace.getAbsolutePath());
    }
    
    public ResultMap execute(String scriptname, ParamMap params) throws ResourceException, ScriptException {
        ResultMap map = new ResultMap();
        
        Binding bind = new Binding();
        for(final Entry<String, Object> param : params.entrySet()) {
            bind.setVariable(param.getKey(), param.getValue());
        }

        this.engine.run(scriptname, bind);
        
        for(final Object e : bind.getVariables().entrySet()) {
            if(e instanceof Entry) {
                final Entry<Object, Object> entry = (Entry<Object, Object>)e;
                map.put(entry.getKey()+"", entry.getValue());
            }
        }
        
        return map;
    }
}
