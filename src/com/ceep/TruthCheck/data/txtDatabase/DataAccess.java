package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.DataBase;
import com.ceep.TruthCheck.exceptions.TXT.*;
import com.ceep.TruthCheck.exceptions.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class DataAccess {

	/**
	 * Carpeta en la que se guardan las bases que se creen
	 */
	private static final String ROOT = "BasesDeDatos";
	/**
	 * Nombre del archivo de configuracion
	 */
	private static final String CONFIG = "config";

	private DataBase DB;
	private String selectedDatabase;
	private HashMap<String, Table> tables;

	public DataAccess(DataBase DB) {
		this.DB = DB;
	}

	private String getPath(String end) {
		return Paths.get(ROOT, end).toString();
	}

	/**
	 * Selecciona una base de datos y lo prepara todo para el uso de esta
	 * 
	 * @param database
	 * @throws ReadException
	 * @throws DatabaseNotFoundException
	 */
	public void selectDatabase(String database) throws ReadException, DatabaseNotFoundException {
		if (DB.searchData(ROOT, CONFIG, database).size() > 0) {
			selectedDatabase = database;

			loadTables();

		} else {
			throw new DatabaseNotFoundException("La base de datos no existe");
		}

	}

	/**
	 * Crea una base de datos en
	 * {@value com.ceep.TruthCheck.data.txtDatabase.DataAccess1#ROOT}
	 * 
	 * @param database nombre de la base de datos
	 * @throws WriteException
	 */
	public void createDatabase(String database) throws WriteException {
		DB.createDatabase(Paths.get(ROOT, database).toString());

		DB.createTable(ROOT, CONFIG);
		DB.writeData(ROOT, CONFIG, database);
	}

	/**
	 * Borra una base de datos
	 * 
	 * @throws AccessException
	 * @throws WriteException
	 * @throws ReadException
	 * @throws NoDatabaseSelectedException
	 */
	public void deleteDatabase() throws AccessException, WriteException, ReadException, NoDatabaseSelectedException {

		if (selectedDatabase == null)
			throw new NoDatabaseSelectedException();

		String database = selectedDatabase;

		DB.deleteDatabase(database);

		DB.removeData(ROOT, CONFIG, database);
	}

	/**
	 * Crea una tabla en una base de datos dada
	 * 
	 * @param database nombre de la base de datos
	 * @param table    tabla que se va a crear
	 * @throws WriteException
	 * @throws NoDatabaseSelectedException
	 */
	public void createTable(Table table) throws WriteException, NoDatabaseSelectedException {
		if (selectedDatabase == null)
			throw new NoDatabaseSelectedException();

		String database = selectedDatabase;
		DB.createTable(getPath(database), table.getTableName());

		DB.createTable(getPath(database), CONFIG);
		DB.writeData(getPath(database), CONFIG, DBConversor.toDBString(table));
	}
	/**
	 * Crea varias tablas de golpe
	 * @param tables
	 * @throws WriteException
	 * @throws NoDatabaseSelectedException
	 */
	public void createTables(Table... tables) throws WriteException, NoDatabaseSelectedException {
		for(Table t : tables) {
			createTable(t);
		}
	}

	/**
	 * Borra una tabla de la base de datos
	 * 
	 * @param database nombre de la base de datos
	 * @param table    tabla de la base de datos
	 * @throws AccessException
	 * @throws WriteException
	 * @throws ReadException
	 * @throws NoDatabaseSelectedException
	 */
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

		List<String> stringifiedTables = DB.readData(database, CONFIG);

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

	/**
	 * Crea una nueva entrada en la tabla dada
	 * 
	 * @param table nombre de la tabla
	 * @param entry entrada
	 * @throws TypeException
	 * @throws WriteException
	 * @throws NoDatabaseSelectedException
	 */
	public void addEntry(Table table, Storable entry)
			throws TypeException, WriteException, NoDatabaseSelectedException {
		if (selectedDatabase == null)
			throw new NoDatabaseSelectedException();

		String database = selectedDatabase;
		if (table.typeCheck(DBConversor.toDBString(entry))) {
			throw new TypeException("The type of the entry does not match the structure of the table");
		}

		DB.writeData(getPath(database), table.getTableName(), DBConversor.toDBString(entry));
	}

	/**
	 * Devuelve una lista de todas las bases de datos registradas
	 * 
	 * @return
	 * @throws ReadException
	 */
	public List<String> listDatabases() throws ReadException {
		return DB.readData(ROOT, CONFIG);
	}

	/**
	 * Devuelve una lista de todas las tablas de la base de datos seleccionada
	 * 
	 * @return
	 */
	public HashMap<String,Table> listTables() {
		return tables;
	}

	/**
	 * Devuelve una lista con todas las entradas de una tabla
	 * 
	 * @param table
	 * @return
	 * @throws ReadException
	 */
	public List<String> listEntries(Table table) throws ReadException {
		return DB.readData(selectedDatabase, table.getTableName());
	}

	/**
	 * Sustituye las entradas que coincidan con la busqueda por la dada
	 * 
	 * @param table
	 * @param search
	 * @param newEntry
	 * @throws WriteException
	 * @throws ReadException
	 */
	public void modifyEntry(Table table, String search, String newEntry) throws WriteException, ReadException {
		DB.modifyData(selectedDatabase, table.getTableName(), search, newEntry);
	}

	/**
	 * Borra una entrada en la tabla dada
	 * 
	 * @param table
	 * @param search
	 * @throws WriteException
	 * @throws ReadException
	 */
	public void removeEntry(Table table, String search) throws WriteException, ReadException {
		DB.removeData(selectedDatabase, table.getTableName(), search);
	}

	public String getSelectedDatabase() {
		return selectedDatabase;
	}

}
