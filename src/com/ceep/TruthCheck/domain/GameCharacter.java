
package com.ceep.TruthCheck.domain;

import java.util.List;


public class GameCharacter extends GameObject{
    
    private int id;
    private String name;
    private String description;
    private List<GameItem> inventory;
    private List<GameItem> equipment;
    
    public GameCharacter(String name, String description, List<GameItem> inventory, List<GameItem> equipment){
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.equipment = equipment;
    }
    public GameCharacter(int id, String name, String description, List<GameItem> inventory, List<GameItem> equipment){
        this.id = id;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.equipment = equipment;
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

    public List<GameItem> getInventory() {
        return inventory;
    }

    public List<GameItem> getEquipment() {
        return equipment;
    }
    
    
    
//    public void addItemToInventory(GameItem item){
//        this.inventory.add(item);
//    }
//    public void equipItem(GameItem item){
//        this.equipment.add(item);
//    }
//    public GameItem popItemFromInventory(GameItem item){
//        return this.inventory.remove(inventory.indexOf(item));
//    }
//    public GameItem unequipItem(GameItem item){
//        return this.inventory.remove(inventory.indexOf(item));
//    }

}
