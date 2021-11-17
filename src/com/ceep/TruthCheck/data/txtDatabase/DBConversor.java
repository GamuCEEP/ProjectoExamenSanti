package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.txtDatabase.Storable;
import com.ceep.TruthCheck.domain.*;
import com.ceep.TruthCheck.exceptions.ObjectCreationException;
import java.util.ArrayList;
import java.util.List;

public class DBConversor {

    public static GameCharacter charFromDBString(String stringifiedChar) {

        String[] chardata = stringifiedChar.split(Storable.FIELD_SEPARATOR);
        
        int id = Integer.parseInt(chardata[0]);
        String name = chardata[1];
        String description = chardata[2];

        List<GameItem> inventory = itemListFromDBString(chardata[3]);

        List<GameItem> equipment = itemListFromDBString(chardata[4]);
        
        return new GameCharacter(id, name, description, inventory, equipment);
    }

    
    public static List<GameItem> itemListFromDBString(String stringifiedItems){
        List<GameItem> list = new ArrayList<>();
        for (String itemdata : stringifiedItems.split(Storable.LIST_SEPARATOR)) {
            list.add(itemFromDBString(itemdata));
        }
        return list;
    }
    
    public static GameItem itemFromDBString(String stringifiedItem){
        String[] itemdata = stringifiedItem.split(Storable.FIELD_SEPARATOR);
        
        int id = Integer.parseInt(itemdata[0]);
        String nombre = itemdata[1];
        String description = itemdata[2];
        
        return new GameItem(id, nombre, description);
    }
    
    public static GameObject gameObjectFromDBString(String stringifiedGameObject, GameObjectType type) 
            throws ObjectCreationException{
        
        switch (type) {
            case GameCharacter:
                return charFromDBString(stringifiedGameObject);
            case GameItem:
                return itemFromDBString(stringifiedGameObject);
            default:
                throw new ObjectCreationException("Object of type "+type+" cannot be created");
        }
    }
    public static List<GameObject> gameObjectsFromDBString(List<String> stringifiedGameObjects, GameObjectType type) 
            throws ObjectCreationException{
        List<GameObject> result = new ArrayList<>();
        for(String stringifiedGameObject : stringifiedGameObjects){
            gameObjectFromDBString(stringifiedGameObject, type);
        }
        return result;
    }
    
    
    public static String toDBString(Storable o){
        if(o instanceof Table)
            return toDBString((Table)o);
        if(o instanceof GameCharacter)
            return toDBString((GameCharacter)o);
        if(o instanceof GameItem)
            return toDBString((GameItem)o);
        return null;
    }
    public static String toDBString(Table t){
        StringBuilder stringifiedTable = new StringBuilder();
        stringifiedTable.append(t.getTableName()).append(Storable.FIELD_SEPARATOR);
        for (DataType dataType : t.getStructure()) {
            stringifiedTable.append(dataType).append(Storable.LIST_SEPARATOR);
        }
        //tablename;type,type,type,
        return stringifiedTable.toString();
    }

    public static String toDBString(GameCharacter c) {
        StringBuilder stringifiedChar = new StringBuilder();

        stringifiedChar.append(c.getId()).append(Storable.FIELD_SEPARATOR);
        stringifiedChar.append(c.getName()).append(Storable.FIELD_SEPARATOR);
        stringifiedChar.append(c.getDescription()).append(Storable.FIELD_SEPARATOR);

        for (GameItem item : c.getInventory()) {
            stringifiedChar.append(item.getId()).append(Storable.LIST_SEPARATOR);
        }
        stringifiedChar.append(Storable.FIELD_SEPARATOR);

        for (GameItem item : c.getEquipment()) {
            stringifiedChar.append(item.getId()).append(Storable.LIST_SEPARATOR);
        }
        stringifiedChar.append(Storable.FIELD_SEPARATOR);

        return stringifiedChar.toString();
        //id;name;description;itemid,itemid,;itemid,itemid,;
    }
     public static String toDBString(GameItem i){
         StringBuilder stringifiedItem = new StringBuilder();
         
         stringifiedItem.append(i.getId()).append(Storable.FIELD_SEPARATOR);
         stringifiedItem.append(i.getName()).append(Storable.FIELD_SEPARATOR);
         stringifiedItem.append(i.getDescription()).append(Storable.FIELD_SEPARATOR);
         
         return stringifiedItem.toString();
         //id;name;description;
     }
}
