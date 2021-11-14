
package com.ceep.TruthCheck.domain;


public class GameObject {
    public final int id;
    private static int ID_COUNTER;
    public static final char FIELD_SEPARATOR = ';';
    public static final char LIST_ELEMENT_SEPARATOR = ',';

    public GameObject() {
        this.id = ID_COUNTER++;
    }
    
    public String toDBString() {
    	return id+";";
    }
}
