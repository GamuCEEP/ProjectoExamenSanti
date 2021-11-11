
package com.ceep.TruthCheck.domain;


public class Item extends GameObject{
    private final String name;
    private final String description;
    
    public Item(int id, String name, String description){
        super(id);
        this.name = name;
        this.description = description;
    }
    public Item(String stringifiedItem){
        super(stringifiedItem.split(";")[0]);
        String[] itemdata = stringifiedItem.split(";");        
        this.name = itemdata[1];
        this.description = itemdata[2];
    }

    @Override
    public String toDBString() {
        StringBuilder stringifiedItem = new StringBuilder();
        
        stringifiedItem.append(id).append(FIELD_SEPARATOR);
        stringifiedItem.append(name).append(FIELD_SEPARATOR);
        stringifiedItem.append(description).append(FIELD_SEPARATOR);
        
        return stringifiedItem.toString();
    }
    
}
