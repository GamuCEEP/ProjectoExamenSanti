package com.ceep.TruthCheck.data;

import java.util.HashMap;
import java.util.List;

import com.ceep.TruthCheck.data.txtDatabase.Storable;
import com.ceep.TruthCheck.exceptions.*;
import com.ceep.TruthCheck.exceptions.TXT.*;

public interface DataAccess {
	/**
	 * Selecciona una base de datos y lo prepara todo para el uso de esta
	 * 
	 * @param database
	 * @throws ReadException
	 * @throws DatabaseNotFoundException
	 */
	public void selectDatabase(String database) throws ReadException, DatabaseNotFoundException;
	/**
	 * Crea una base de datos en
	 * {@value com.ceep.TruthCheck.data.txtDatabase.DataAccess1#ROOT}
	 * 
	 * @param database nombre de la base de datos
	 * @throws WriteException
	 */
	public void createDatabase(String database) throws WriteException;
	/**
	 * Borra una base de datos
	 * 
	 * @throws AccessException
	 * @throws WriteException
	 * @throws ReadException
	 * @throws NoDatabaseSelectedException
	 */
	public void deleteDatabase() throws AccessException, WriteException, ReadException, NoDatabaseSelectedException;
	/**
	 * Crea una tabla en una base de datos dada
	 * 
	 * @param database nombre de la base de datos
	 * @param table    tabla que se va a crear
	 * @throws WriteException
	 * @throws NoDatabaseSelectedException
	 */
	public void createTable(Table table) throws WriteException, NoDatabaseSelectedException;
	/**
	 * Crea varias tablas de golpe
	 * 
	 * @param tables
	 * @throws WriteException
	 * @throws NoDatabaseSelectedException
	 */
	public void createTables(Table... tables) throws WriteException, NoDatabaseSelectedException;
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
			throws AccessException, WriteException, ReadException, NoDatabaseSelectedException;
	/**
	 * Crea una nueva entrada en la tabla dada
	 * 
	 * @param table nombre de la tabla
	 * @param entry entrada
	 * @throws TypeException
	 * @throws WriteException
	 * @throws NoDatabaseSelectedException
	 */
	public void addEntry(Table table, Storable entry) throws TypeException, WriteException, NoDatabaseSelectedException;
	/**
	 * Devuelve una lista de todas las bases de datos registradas
	 * 
	 * @return
	 * @throws ReadException
	 */
	public List<String> listDatabases() throws ReadException;
	/**
	 * Devuelve una lista de todas las tablas de la base de datos seleccionada
	 * 
	 * @return
	 * @throws ReadException 
	 */
	public HashMap<String, Table> listTables() throws ReadException;
	/**
	 * Devuelve una lista con todas las entradas de una tabla
	 * 
	 * @param table
	 * @return
	 * @throws ReadException
	 */
	public List<String> listEntries(Table table) throws ReadException;
	/**
	 * Sustituye las entradas que coincidan con la busqueda por la dada
	 * 
	 * @param table
	 * @param search
	 * @param newEntry
	 * @throws WriteException
	 * @throws ReadException
	 */
	public void modifyEntry(Table table, String search, String newEntry) throws WriteException, ReadException;
	/**
	 * Borra una entrada en la tabla dada
	 * 
	 * @param table
	 * @param search
	 * @throws WriteException
	 * @throws ReadException
	 */
	public void removeEntry(Table table, String search) throws WriteException, ReadException;
	/**
	 * Devuelve la base de datos seleccionada
	 * @return
	 */
	public String getSelectedDatabase();

}
