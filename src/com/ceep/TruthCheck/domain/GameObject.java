
package com.ceep.TruthCheck.domain;


public class GameObject {
    public final int id;
    public static final char FIELD_SEPARATOR = ';';
    public static final char LIST_ELEMENT_SEPARATOR = ',';

    public GameObject(int id) {
        this.id = id;
    }
    public GameObject(String id){
        this.id = Integer.parseInt(id);
    }
    
    public String toDBString() {
    	return id+";";
    }
}
