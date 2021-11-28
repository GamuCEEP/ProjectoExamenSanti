package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.DataAccess;
import com.ceep.TruthCheck.data.DataBase;
import com.ceep.TruthCheck.data.Table;
import com.ceep.TruthCheck.exceptions.TXT.*;
import com.ceep.TruthCheck.exceptions.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class TXTDataAccess implements DataAccess{

	/**
	 * Carpeta en la que se guardan las bases que se creen
	 */
	private static final String ROOT = "BasesDeDatos";
	/**
	 * Nombre del archivo de configuracion
	 */
	private static final String CONFIG = "config";

	private DataBase DB;
	private String selectedDatabase = "";
	private HashMap<String, Table> tables = new HashMap<>();

	
	
	public TXTDataAccess() {
		this.DB = new TXTDataBase();
	}

	private String getPath(String end) {
		return Paths.get(ROOT, end).toString();
	}

	
	public void selectDatabase(String database) throws ReadException, DatabaseNotFoundException {
		if (DB.searchData(ROOT, CONFIG, database).size() > 0) {
			selectedDatabase = database;

			loadTables();

		} else {
			throw new DatabaseNotFoundException("La base de datos no existe");
		}

	}

	
	public void createDatabase(String database) throws WriteException {
		DB.createDatabase(Paths.get(ROOT, database).toString());
		selectedDatabase = database;

		if(!DB.exists(Paths.get(ROOT, CONFIG).toString()))
			DB.createTable(ROOT, CONFIG);
		
		DB.writeData(ROOT, CONFIG, database);
		
		DB.createTable(getPath(database), CONFIG);
	}

	
	public void deleteDatabase() throws AccessException, WriteException, ReadException, NoDatabaseSelectedException {

		if (selectedDatabase == null)
			throw new NoDatabaseSelectedException();

		String database = selectedDatabase;

		DB.deleteDatabase(database);

		DB.removeData(ROOT, CONFIG, database);
	}

	
	public void createTable(Table table) throws WriteException, NoDatabaseSelectedException {
		if (selectedDatabase == null)
			throw new NoDatabaseSelectedException();

		String database = selectedDatabase;
		DB.createTable(getPath(database), table.getTableName());

		
		DB.writeData(getPath(database), CONFIG, DBConversor.toDBString(table));
	}

	
	public void createTables(Table... tables) throws WriteException, NoDatabaseSelectedException {
		for (Table t : tables) {
			createTable(t);
		}
	}

	
	public void deleteTable(Table table)
			throws AccessException, WriteException, ReadException, NoDatabaseSelectedException {
		if (selectedDatabase == null)
			throw new NoDatabaseSelectedException();

		String database = selectedDatabase;

		DB.deleteTable(database, table.getTableName());

		DB.removeData(getPath(database), CONFIG, DBConversor.toDBString(table));
	}

	/**
	 * Carga las tablas de una base de datos en memoria
	 * 
	 * @param database de que base de datos se cargan
	 * @throws ReadException
	 * @throws NoDatabaseSelectedException
	 */
	private void loadTables() throws ReadException {
		String database = selectedDatabase;

		List<String> stringifiedTables = DB.readData(getPath(database), CONFIG);

		for (String stringifiedTable : stringifiedTables) {
			Table table = DBConversor.tableFromDBString(stringifiedTable);
			tables.put(table.getTableName(), table);
		}
		for (Table table : tables.values()) {
			for (String refTableName : table.getReferencedTablesNames()) {
				table.addReference(tables.get(refTableName));
			}
		}
	}

	
	public void addEntry(Table table, Storable entry)
			throws TypeException, WriteException, NoDatabaseSelectedException {
		if (selectedDatabase == null)
			throw new NoDatabaseSelectedException();

		String database = selectedDatabase;
		if (!table.typeCheck(DBConversor.toDBString(entry))) {
			throw new TypeException("The type of the entry does not match the structure of the table");
		}

		DB.writeData(getPath(database), table.getTableName(), DBConversor.toDBString(entry));
	}

	
	public List<String> listDatabases() throws ReadException {
		return DB.readData(ROOT, CONFIG);
	}

	
	public HashMap<String, Table> listTables() throws ReadException {

		return tables;
	}

	
	public List<String> listEntries(Table table) throws ReadException {
		return DB.readData(getPath(selectedDatabase), table.getTableName());
	}

	
	public void modifyEntry(Table table, String search, String newEntry) throws WriteException, ReadException {
		DB.modifyData(selectedDatabase, table.getTableName(), search, newEntry);
	}

	
	public void removeEntry(Table table, String search) throws WriteException, ReadException {
		DB.removeData(selectedDatabase, table.getTableName(), search);
	}

	public String getSelectedDatabase() {
		return selectedDatabase;
	}

}
