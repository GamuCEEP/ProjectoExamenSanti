
package com.ceep.TruthCheck.domain;

import com.ceep.TruthCheck.domain.Item;
import java.util.ArrayList;


public class Character {
    private final String name;
    private final String description;
    private final ArrayList<Item> inventory;
    private final ArrayList<Item> equipment;
    
    public Character(String name, String description, ArrayList<Item> inventory, ArrayList<Item> equipment){
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.equipment = equipment;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public ArrayList<Item> getEquipment() {
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
