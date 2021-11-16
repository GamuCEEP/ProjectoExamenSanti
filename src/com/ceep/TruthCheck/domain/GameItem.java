
package com.ceep.TruthCheck.domain;


public class GameItem extends GameObject{
    
    private int id;
    private String name;
    private String description;
    
    public GameItem(String name, String description){
        this.name = name;
        this.description = description;
    }
    
    public GameItem(int id, String name, String description){
    	this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    
    
    
}
