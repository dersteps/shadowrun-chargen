package de.zombielabs.shadowrun.common.annotations;

import de.zombielabs.shadowrun.common.data.annotations.DomainClass;
import de.zombielabs.shadowrun.common.data.annotations.SQLiteField;
import de.zombielabs.shadowrun.common.data.gear.Drug;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ReflectionTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ReflectionTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( ReflectionTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        // Create an SQL statement from an annotated class
        Drug d = new Drug();
        d.setVector("Example vector");
        
        // Reflect
//        final Field[] fields = d.getClass().getDeclaredFields();
//        final Field[] superfields = d.getClass().getSuperclass().getDeclaredFields();
        final List<Field> fields = new ArrayList<Field>();
        Class clazz = d.getClass();
        while(true) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
            if(clazz.equals(Object.class)) { break; }
        }
        
        assertNotNull(fields);
        
        // Filter for annotated
        final List<Field> annotatedFields = new ArrayList<Field>();
        
        for(final Field f : fields) {
            System.out.println("FIELD: " + f.getName());
            if(f.isAnnotationPresent(SQLiteField.class)) { 
                System.out.println("Annotated field found: " + f.getName());
                annotatedFields.add(f);
            }
        }
        
        assertNotNull(annotatedFields);
        assertFalse(annotatedFields.isEmpty());
        
        StringBuilder sb = new StringBuilder();
        
        assertTrue(d.getClass().isAnnotationPresent(DomainClass.class));
        DomainClass dc = (DomainClass)d.getClass().getAnnotation(DomainClass.class);
        assertNotNull(dc);
        
        final String table = dc.table();
        
        sb.append("INSERT INTO ").append(table).append(" (");
        
        int count = 0;
        for(final Field f : annotatedFields) {
            SQLiteField sqlF = (SQLiteField)f.getAnnotation(SQLiteField.class);
            sb.append(sqlF.maps());
            if(++count < annotatedFields.size()) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        
        Method[] methods = d.getClass().getMethods();
        count = 0;
        for(final Field f : annotatedFields) {
            for(final Method m : methods) {
                if(m.getName().equalsIgnoreCase("get" + f.getName())) {
                    System.out.println("Found method for field " + f.getName() + ": " + m.getName());
                    try {
                        final Object value = m.invoke(d, new Object[] {});
                        SQLiteField sqlF = (SQLiteField)f.getAnnotation(SQLiteField.class);
                        boolean txt = sqlF.isText() || value instanceof String;
                        
                        if(txt) {
                            sb.append("'").append(value).append("'");
                        } else {
                            sb.append(value);
                        }
                        
                        if(++count < annotatedFields.size()) {
                            sb.append(", ");
                        }
                        
                        
                    } catch (Exception ex) {
                        Logger.getLogger(ReflectionTest.class.getName()).log(Level.SEVERE, null, ex);
                        fail("EXCEPTION");
                    }
                }
            }
        }
        
        sb.append(");");
        System.out.println(sb.toString());
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    }
}
