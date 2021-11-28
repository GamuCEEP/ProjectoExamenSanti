package com.ceep.TruthCheck.business;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.ceep.TruthCheck.data.*;
import com.ceep.TruthCheck.data.sqlDatabase.SQLDataAccess;
import com.ceep.TruthCheck.data.txtDatabase.*;
import com.ceep.TruthCheck.domain.*;
import com.ceep.TruthCheck.exceptions.*;
import com.ceep.TruthCheck.exceptions.TXT.*;

public class DatabaseManager {
	
	private DataAccess data;
	
	public DatabaseManager(DataAccess da) {
		this.data = da;
	}
	
	public void changeMode() throws SQLException {
		if(data instanceof TXTDataAccess) {
			data = new SQLDataAccess();
		}else {
			data = new TXTDataAccess();
		}
	}

	public void createDatabase(String database) throws AccessException {

		Table personaje = new Table("Character", Arrays.asList("inventory", "equipment"),
				Table.c("id", DataType.REFERABLE), 
				Table.c("name", DataType.STRING),
				Table.c("description", DataType.STRING),
				Table.c("inventory", DataType.REFERENCE),
				Table.c("equipment", DataType.REFERENCE));
		Table item = new Table("Item", null, 
				Table.c("id", DataType.REFERABLE), 
				Table.c("name", DataType.STRING),
				Table.c("description", DataType.STRING));
		Table inventario = new Table("inventory", Arrays.asList("Item"), Table.c("id", DataType.REFERABLE),
				Table.c("item", DataType.REFERENCE));
		Table equipamiento = new Table("equipment", Arrays.asList("Item"), Table.c("id", DataType.REFERABLE),
				Table.c("item", DataType.REFERENCE));

		data.createDatabase(database);
		try {
			data.createTables(personaje, item, inventario, equipamiento);
		} catch (NoDatabaseSelectedException e) {
		}
	}

	public void deleteDatabase() throws AccessException, NoDatabaseSelectedException {
		data.deleteDatabase();
	}

	public List<String> listDatabases() throws ReadException {
		return data.listDatabases();
	}

	public void conectToDatabase(String database) throws ReadException, DatabaseNotFoundException {
		data.selectDatabase(database);
	}

	public void addEntry(GameObject entry) throws TypeException, NoDatabaseSelectedException, AccessException {
		if (entry instanceof GameItem) {
			data.addEntry(data.listTables().get("Item"), entry);
		}
		if (entry instanceof GameCharacter) {
			GameCharacter character = (GameCharacter) entry;

			data.addEntry(data.listTables().get("Character"), entry);
			for (GameItem item : character.getInventory()) {
				LinkTable inventory = new LinkTable(character.getId(), item.getId());
				data.addEntry(data.listTables().get("inventory"), inventory);
			}
			for (GameItem item : character.getEquipment()) {
				LinkTable equipment = new LinkTable(character.getId(), item.getId());
				data.addEntry(data.listTables().get("equipment"), equipment);
			}
		}

	}
	public void deleteEntry(GameObject entry) throws WriteException, ReadException {
		if (entry instanceof GameCharacter) {
			GameCharacter character = (GameCharacter) entry;

			data.removeEntry(data.listTables().get("Character"), DBConversor.toDBString(entry));

			data.removeEntry(data.listTables().get("inventory"), character.getId() + ";");
			data.removeEntry(data.listTables().get("equipment"), character.getId() + ";");
		}
		if (entry instanceof GameItem) {
			GameItem item = (GameItem) entry;
			data.removeEntry(data.listTables().get("Item"), DBConversor.toDBString(entry));

			data.removeEntry(data.listTables().get("inventory"), ";" + item.getId());
			data.removeEntry(data.listTables().get("equipment"), ";" + item.getId());
		}

	}
	public void modifyEntry(GameObject oldEntry, GameObject newEntry)
			throws WriteException, ReadException, TypeException, NoDatabaseSelectedException {
		if (oldEntry instanceof GameItem) {
			data.modifyEntry(data.listTables().get("Item"), DBConversor.toDBString(oldEntry),
					DBConversor.toDBString(newEntry));
		}
		if (oldEntry instanceof GameCharacter) {
			GameCharacter oldcharacter = (GameCharacter) oldEntry;
			GameCharacter newcharacter = (GameCharacter) newEntry;

			data.modifyEntry(data.listTables().get("Character"), DBConversor.toDBString(oldEntry),
					DBConversor.toDBString(newEntry));

			for (GameItem item : oldcharacter.getInventory()) {
				data.removeEntry(data.listTables().get("inventory"), oldcharacter.getId() + ";" + item.getId());
			}
			for (GameItem item : newcharacter.getInventory()) {
				LinkTable inventory = new LinkTable(newcharacter.getId(), item.getId());
				data.addEntry(data.listTables().get("inventory"), inventory);
			}

			for (GameItem item : oldcharacter.getEquipment()) {
				data.removeEntry(data.listTables().get("equipment"), oldcharacter.getId() + ";" + item.getId());
			}
			for (GameItem item : newcharacter.getEquipment()) {
				LinkTable equipment = new LinkTable(newcharacter.getId(), item.getId());
				data.addEntry(data.listTables().get("equipment"), equipment);
			}
		}
	}
	
	public boolean exists(String file) {
		try {
			return data.listDatabases().contains(file);
		} catch (ReadException e) {
			return false;
		}
	}

	public HashMap<String, GameItem> getItems() throws ReadException {
		List<String> stringifiedItems = data.listEntries(data.listTables().get("Item"));
		return DBConversor.itemsFromDBString(stringifiedItems);
	}
}
