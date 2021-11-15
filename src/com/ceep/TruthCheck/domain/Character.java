
package com.ceep.TruthCheck.domain;

import java.util.ArrayList;
import java.util.List;


public class Character extends GameObject{
    private final String name;
    private final String description;
    private final List<Item> inventory;
    private final List<Item> equipment;
    
    public Character(String name, String description, List<Item> inventory, List<Item> equipment){
        super();
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.equipment = equipment;
    }
    public Character(String stringifiedChar){
        super();
        String[] chardata = stringifiedChar.split(";");
        this.name = chardata[1];
        this.description = chardata[2];
        
        this.inventory = new ArrayList<>();
        for(String itemdata : chardata[3].split(",")){
            this.inventory.add(new Item(itemdata));
        }
        
        this.equipment = new ArrayList<>();
        for(String itemdata : chardata[4].split(",")){
            this.equipment.add(new Item(itemdata));
        }
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

    @Override
    public String toDBString() {
        StringBuilder stringifiedChar = new StringBuilder();
        
        stringifiedChar.append(id).append(FIELD_SEPARATOR);
        stringifiedChar.append(name).append(FIELD_SEPARATOR);
        stringifiedChar.append(description).append(FIELD_SEPARATOR);
        
        for(Item item : inventory){
            stringifiedChar.append(item.id).append(LIST_ELEMENT_SEPARATOR);
        }
        stringifiedChar.append(FIELD_SEPARATOR);
        
        for(Item item : equipment){
            stringifiedChar.append(item.id).append(LIST_ELEMENT_SEPARATOR);
        }
        stringifiedChar.append(FIELD_SEPARATOR);
        
        return stringifiedChar.toString();
        //id;name;description;itemid,itemid,;itemid,itemid,;
    }
}
