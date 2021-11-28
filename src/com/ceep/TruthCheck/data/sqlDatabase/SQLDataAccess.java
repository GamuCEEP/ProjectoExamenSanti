package com.ceep.TruthCheck.data.sqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ceep.TruthCheck.data.*;
import com.ceep.TruthCheck.data.txtDatabase.Storable;
import com.ceep.TruthCheck.exceptions.*;
import com.ceep.TruthCheck.exceptions.TXT.*;

public class SQLDataAccess implements DataAccess {
	
	Connection conn;
	
	public SQLDataAccess() throws SQLException {
		this.conn = Conexion.getConection();
	}

	@Override
	public void selectDatabase(String database) throws ReadException, DatabaseNotFoundException {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("USE "+database+";");
		} catch (SQLException e) {
			throw new ReadException(e.getMessage());
		}
		
		try {
			stmt.execute();
		} catch (SQLException e) {
			throw new DatabaseNotFoundException(e.getMessage());
		}		
	}

	@Override
	public void createDatabase(String database) throws WriteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteDatabase() throws AccessException, WriteException, ReadException, NoDatabaseSelectedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTable(Table table) throws WriteException, NoDatabaseSelectedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTables(Table... tables) throws WriteException, NoDatabaseSelectedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTable(Table table)
			throws AccessException, WriteException, ReadException, NoDatabaseSelectedException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> listDatabases() throws ReadException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Table> listTables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> listEntries(Table table) throws ReadException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyEntry(Table table, String search, String newEntry) throws WriteException, ReadException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeEntry(Table table, String search) throws WriteException, ReadException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSelectedDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEntry(Table table, Storable entry)
			throws TypeException, WriteException, NoDatabaseSelectedException {
		// TODO Auto-generated method stub
		
	}

}
