
package com.ceep.TruthCheck.domain;

import com.ceep.TruthCheck.data.txtDatabase.Storable;
import java.util.ArrayList;
import java.util.List;


public class Character extends GameObject{
    
    private int id;
    private String name;
    private String description;
    private List<Item> inventory;
    private List<Item> equipment;
    
    public Character(String name, String description, List<Item> inventory, List<Item> equipment){
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.equipment = equipment;
    }
    public Character(int id, String name, String description, List<Item> inventory, List<Item> equipment){
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

    public List<Item> getInventory() {
        return inventory;
    }

    public List<Item> getEquipment() {
        return equipment;
    }
    
    
    
//    public void addItemToInventory(Item item){
//        this.inventory.add(item);
//    }
//    public void equipItem(Item item){
//        this.equipment.add(item);
//    }
//    public Item popItemFromInventory(Item item){
//        return this.inventory.remove(inventory.indexOf(item));
//    }
//    public Item unequipItem(Item item){
//        return this.inventory.remove(inventory.indexOf(item));
//    }

}
