package com.ceep.TruthCheck.business;

import java.util.Arrays;
import java.util.List;

import com.ceep.TruthCheck.data.txtDatabase.DataAccess;
import com.ceep.TruthCheck.data.txtDatabase.DataType;
import com.ceep.TruthCheck.data.txtDatabase.Table;
import com.ceep.TruthCheck.domain.GameCharacter;
import com.ceep.TruthCheck.domain.GameItem;
import com.ceep.TruthCheck.domain.GameObject;
import com.ceep.TruthCheck.domain.GameObjectType;
import com.ceep.TruthCheck.exceptions.*;
import com.ceep.TruthCheck.exceptions.TXT.NoDatabaseSelectedException;
import com.ceep.TruthCheck.exceptions.TXT.TypeException;

public class BusinesManager {

	private final DataAccess data;

	public BusinesManager(DataAccess data) {
		this.data = data;
	}

	/*
	 * 1 - Crear base de datos 2 - A�adir base de datos 2 - Listar bases de datos
	 * 3 - Borrar base de datos 4 - Conectar a base de datos 1 - A�adir un
	 * elemento 2 - Eliminar un elemento 3 - Modificar un elemento 4 - Ver un
	 * elemento
	 */

	public void createDatabase(String database)
			throws WriteException, ReadException, DatabaseNotFoundException, NoDatabaseSelectedException {

		Table personaje = new Table("Character", Arrays.asList("inventory", "equipment"),
				Table.c("id", DataType.REFERABLE), Table.c("name", DataType.STRING),
				Table.c("inventory", DataType.REFERENCE), Table.c("equipment", DataType.REFERENCE));
		Table item = new Table("Item", null, Table.c("id", DataType.REFERABLE), Table.c("name", DataType.STRING),
				Table.c("description", DataType.STRING));
		Table inventario = new Table("inventory", Arrays.asList("Item"), Table.c("id", DataType.REFERABLE),
				Table.c("item", DataType.REFERENCE));
		Table equipamiento = new Table("equipment", Arrays.asList("Item"), Table.c("id", DataType.REFERABLE),
				Table.c("item", DataType.REFERENCE));

		data.createDatabase(database);
		data.selectDatabase(database);
		data.createTables(personaje, item, inventario, equipamiento);
	}

	public void deleteDatabase() throws AccessException, WriteException, ReadException, NoDatabaseSelectedException  {
		data.deleteDatabase();
	}

	public List<String> listDatabases() throws ReadException {
		return data.listDatabases();
	}

	public void conectToDatabase(String database) throws ReadException, DatabaseNotFoundException {
		data.selectDatabase(database);
	}

	public void addEntry(GameObject entry) throws WriteException, TypeException, NoDatabaseSelectedException {
		if(entry instanceof GameItem) {
			data.addEntry(data.listTables().get("Item"),entry);
		}
		if(entry instanceof GameCharacter) {
			
		}
		
	}

	public void deleteEntry(GameObject entry) throws WriteException, ReadException {
		data.removeData(entry);
	}

	public void modifyEntry(GameObject oldEntry, GameObject newEntry) throws WriteException, ReadException {
		data.modifyEntry(oldEntry, newEntry); //TODO ver como hacer para elegir la tabla correcta
	}

	public List<GameObject> searchEntry(String search) throws ReadException {
		
	}

	public String getConectedDatabase() {
		return data.getSelectedDatabase();
	}

}
