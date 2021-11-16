package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.txtDatabase.Storable;
import com.ceep.TruthCheck.domain.Character;
import com.ceep.TruthCheck.domain.GameObject;
import com.ceep.TruthCheck.domain.Item;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConversor {

    public static Character charFromDBString(String stringifiedChar) {

        String[] chardata = stringifiedChar.split(Storable.FIELD_SEPARATOR);
        
        int id = Integer.parseInt(chardata[0]);
        String name = chardata[1];
        String description = chardata[2];

        List<Item> inventory = itemListFromDBString(chardata[3]);

        List<Item> equipment = itemListFromDBString(chardata[4]);
        
        return new Character(id, name, description, inventory, equipment);
    }

    
    public static List<Item> itemListFromDBString(String stringifiedItems){
        List<Item> list = new ArrayList<>();
        for (String itemdata : stringifiedItems.split(Storable.LIST_SEPARATOR)) {
            list.add(itemFromDBString(itemdata));
        }
        return list;
    }
    
    public static Item itemFromDBString(String stringifiedItem){
        String[] itemdata = stringifiedItem.split(Storable.FIELD_SEPARATOR);
        
        int id = Integer.parseInt(itemdata[0]);
        String nombre = itemdata[1];
        String description = itemdata[2];
        
        return new Item(id, nombre, description);
    }
    
    public static String toDBString(GameObject o){
        if(o instanceof com.ceep.TruthCheck.domain.Character)
            return toDBString((com.ceep.TruthCheck.domain.Character)o);
        if(o instanceof Item)
            return toDBString((Item)o);
        
        return null;
    }
    
    public static String toDBString(Character c) {
        StringBuilder stringifiedChar = new StringBuilder();

        stringifiedChar.append(c.getId()).append(Storable.FIELD_SEPARATOR);
        stringifiedChar.append(c.getName()).append(Storable.FIELD_SEPARATOR);
        stringifiedChar.append(c.getDescription()).append(Storable.FIELD_SEPARATOR);

        for (Item item : c.getInventory()) {
            stringifiedChar.append(item.getId()).append(Storable.LIST_SEPARATOR);
        }
        stringifiedChar.append(Storable.FIELD_SEPARATOR);

        for (Item item : c.getEquipment()) {
            stringifiedChar.append(item.getId()).append(Storable.LIST_SEPARATOR);
        }
        stringifiedChar.append(Storable.FIELD_SEPARATOR);

        return stringifiedChar.toString();
        //id;name;description;itemid,itemid,;itemid,itemid,;
    }
     public static String toDBString(Item i){
         StringBuilder stringifiedItem = new StringBuilder();
         
         stringifiedItem.append(i.getId()).append(Storable.FIELD_SEPARATOR);
         stringifiedItem.append(i.getName()).append(Storable.FIELD_SEPARATOR);
         stringifiedItem.append(i.getDescription()).append(Storable.FIELD_SEPARATOR);
         
         return stringifiedItem.toString();
     }
}
