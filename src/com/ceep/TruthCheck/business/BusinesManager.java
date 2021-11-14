package com.ceep.TruthCheck.business;

import java.util.List;

import com.ceep.TruthCheck.data.DataAccess;
import com.ceep.TruthCheck.domain.GameObject;
import com.ceep.TruthCheck.domain.GameObjectType;
import com.ceep.TruthCheck.exceptions.AccessException;
import com.ceep.TruthCheck.exceptions.DatabaseNotFoundException;
import com.ceep.TruthCheck.exceptions.ObjectCreationException;
import com.ceep.TruthCheck.exceptions.ReadException;
import com.ceep.TruthCheck.exceptions.WriteException;

public class BusinesManager {

	private DataAccess data;

	public BusinesManager(DataAccess data) {
		this.data = data;
	}
	/*
	 * 1 - Crear base de datos 2 - Añadir base de datos 2 - Listar bases de datos 3
	 * - Borrar base de datos 4 - Conectar a base de datos 1 - Añadir un elemento 2
	 * - Eliminar un elemento 3 - Modificar un elemento 4 - Ver un elemento
	 */

	public void createDatabase(String database) throws WriteException {
		data.createDatabase(database);
	}

	public void addDatabase(String database, String ... table) throws WriteException, DatabaseNotFoundException {
		data.addDatabase(database, table);
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
		data.modifyData(database,oldEntry, newEntry);
	}
	public void modifyEntry(GameObject oldEntry, GameObject newEntry) throws WriteException, ReadException {
		data.modifyData(oldEntry, newEntry);
	}

	public List<GameObject> searchEntry(String database, String search) throws ReadException {
		List<GameObject> result = null;
		for(GameObjectType type : GameObjectType.values()) {
			try {
				result = data.searchData(database, search, type);
				break;
			} catch (ObjectCreationException e) {
				continue;
			}
		}
		return result;
	}
	public List<GameObject> searchEntry(String search) throws ReadException {
		return searchEntry(data.getSelectedDatabase(),search);
	}
	public String getConectedDatabase() {
		return data.getSelectedDatabase();
	}

}
