package com.ceep.TruthCheck.business;

import java.util.List;

import com.ceep.TruthCheck.data.txtDatabase.DataAccess;
import com.ceep.TruthCheck.data.txtDatabase.Table;
import com.ceep.TruthCheck.domain.GameObject;
import com.ceep.TruthCheck.domain.GameObjectType;
import com.ceep.TruthCheck.exceptions.*;

public class BusinesManager {

    private final DataAccess data;

    public BusinesManager(DataAccess data) {
        this.data = data;
    }

    /*
	 * 1 - Crear base de datos 2 - A�adir base de datos 2 - Listar bases de datos 3
	 * - Borrar base de datos 4 - Conectar a base de datos 1 - A�adir un elemento 2
	 * - Eliminar un elemento 3 - Modificar un elemento 4 - Ver un elemento
     */

    public void createDatabase(String database) throws WriteException {
        Table[] tables = new Table[GameObjectType.values().length];
        int index = 0;
        for(GameObjectType t : GameObjectType.values()){
            tables[index++] = new Table(t.name());
        }
        data.createDatabase(database, tables);
    }

    public void addDatabase(String database, String... tableNames) throws WriteException, DatabaseNotFoundException {
        Table[] tables = new Table[tableNames.length];
        int index = 0;
        for(String tableName : tableNames){
            tables[index++] = new Table(tableName);
        }
        data.addDatabase(database, tables);
    }

    public void deleteDatabase(String database) throws AccessException {
        data.deleteDatabase(database);
    }

    public void deleteDatabase() throws AccessException {
        data.deleteDatabase();
    }

    public List<String> listDatabases() throws ReadException {
        return data.listDatabases();
    }

    public void conectToDatabase(String database) throws ReadException, DatabaseNotFoundException {
        data.selectDatabase(database);
    }

    public void addEntry(String database, GameObject entry) throws WriteException {
        data.writeData(database, entry);
    }

    public void addEntry(GameObject entry) throws WriteException {
        data.writeData(entry);
    }

    public void deleteEntry(String database, GameObject entry) throws WriteException, ReadException {
        data.removeData(database, entry);
    }

    public void deleteEntry(GameObject entry) throws WriteException, ReadException {
        data.removeData(entry);
    }

    public void modifyEntry(String database, GameObject oldEntry, GameObject newEntry) throws WriteException, ReadException {
        data.modifyData(database, oldEntry, newEntry);
    }

    public void modifyEntry(GameObject oldEntry, GameObject newEntry) throws WriteException, ReadException {
        data.modifyData(oldEntry, newEntry);
    }

    public List<GameObject> searchEntry(String database, String search) throws ReadException {
        List<GameObject> result = null;
        for (GameObjectType type : GameObjectType.values()) {
            try {
                result = data.searchData(database, search, type);
                break;
            } catch (ObjectCreationException e) {
            }
        }
        return result;
    }

    public List<GameObject> searchEntry(String search) throws ReadException {
        return searchEntry(data.getSelectedDatabase(), search);
    }

    public String getConectedDatabase() {
        return data.getSelectedDatabase();
    }

}
