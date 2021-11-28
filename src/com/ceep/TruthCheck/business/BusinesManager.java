package com.ceep.TruthCheck.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.ceep.TruthCheck.data.DataAccess;
import com.ceep.TruthCheck.data.LinkTable;
import com.ceep.TruthCheck.data.Table;
import com.ceep.TruthCheck.data.sqlDatabase.SQLDataAccess;
import com.ceep.TruthCheck.data.txtDatabase.*;
import com.ceep.TruthCheck.domain.*;
import com.ceep.TruthCheck.exceptions.*;
import com.ceep.TruthCheck.exceptions.TXT.*;

public class BusinesManager {

	private DataAccess data;

	public BusinesManager(DataAccess data) {
		this.data = data;
	}

	public void changeMode() throws SQLException {
		if(data instanceof TXTDataAccess) {
			data = new SQLDataAccess();
		}else {
			data = new TXTDataAccess();
		}
	}
	public List<GameObject> searchByField(String search, GameObjectType type, int field)
			throws ReadException, ObjectCreationException {

		List<GameObject> result = new ArrayList<>();

		HashMap<String, GameItem> items = new HashMap<>();

		Table t = data.listTables().get("Item");
		for (String entry : data.listEntries(t)) {
			GameItem item = DBConversor.itemFromDBString(entry);
			items.put(item.getId() + "", item);
		}

		switch (type) {
		default:
		case Item:
			for (String entry : data.listEntries(t)) {
				if (entry.split(Storable.FIELD_SEPARATOR)[field].toLowerCase().contains(search.toLowerCase())) {
					result.add(DBConversor.itemFromDBString(entry));
				}
			}
			if(type == GameObjectType.Item) break;
		case Personaje:
			t = data.listTables().get("Character");
			for (String entry : data.listEntries(t)) {
				if (entry.split(Storable.FIELD_SEPARATOR)[field].toLowerCase().contains(search.toLowerCase())) {
					result.add(DBConversor.charFromDBString(entry, items));
				}
			}
			if(type == GameObjectType.Personaje) break;
		}
		return result;
	}

	public List<GameObject> searchEntry(String search) throws ReadException, ObjectCreationException {
		List<GameObject> result = new ArrayList<>();

		HashMap<String, GameItem> items = new HashMap<>();

		Table t = data.listTables().get("Item");
		for (String entry : data.listEntries(t)) {
			GameItem item = DBConversor.itemFromDBString(entry);
			items.put(item.getId() + "", item);
			if (entry.contains(search)) {
				result.add(item);
			}
		}
		t = data.listTables().get("Character");
		for (String entry : data.listEntries(t)) {
			if (entry.contains(search)) {
				result.add(DBConversor.charFromDBString(entry, items));
			}
		}
		return result;

	}

	public String getConectedDatabase() {
		return data.getSelectedDatabase();
	}

}
