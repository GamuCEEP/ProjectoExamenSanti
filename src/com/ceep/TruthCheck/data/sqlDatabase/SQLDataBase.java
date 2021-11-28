package com.ceep.TruthCheck.data.sqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.ceep.TruthCheck.data.DataBase;
import com.ceep.TruthCheck.exceptions.AccessException;
import com.ceep.TruthCheck.exceptions.ReadException;
import com.ceep.TruthCheck.exceptions.WriteException;

public class SQLDataBase implements DataBase{
	//esto va en el controlador de sql
	private static String CREATE_TABLE_ITEM = 
			"CREATE TABLE item("
			+ "id int auto_increment primary key,"
			+ "name varchar(20),"
			+ "description varchar(50));";
	private static String CREATE_TABLE_INVENTORY =
			"CREATE TABLE inventory("
			+ "charid int,"
			+ "itemid int,"
			+ "primary key(charid, itemid));";
	private static String CREATE_TABLE_EQUIPMENT = 
			"CREATE TABLE equipment("
			+ "charid int,"
			+ "itemid int,"
			+ "primary key(charid, itemid));";
	private static String CREATE_TABLE_CHARACTER =
			"CREATE TABLE character("
			+ "id int auto_increment primary key,"
			+ "name varchar(20),"
			+ "description varchar(50),"
			+ "inventory int,"
			+ "equipment int,"
			+ "foreign key(inventory) references inventory(charid),"
			+ "foreign key(equipment) references equipment(charid));";
	private static String CREATE_DATABASE = 
			"CREATE DATABASE ?;"
			+ "use DATABASE ?";

	@Override
	public void createDatabase(String database_name) throws WriteException {
		Connection conn = getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(CREATE_DATABASE);
		
		stmt.setString(1, database_name);
		stmt.setString(2, database_name);
		
		stmt.execute();
		
		
	}

	@Override
	public void createTable(String database_name, String SQL) throws WriteException {
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(SQL);
		
		stmt.execute();
		
	}

	@Override
	public void deleteDatabase(String database_name) throws AccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTable(String database_name, String table_name) throws AccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeData(String database_name, String table_name, String data) throws WriteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeData(String database_name, String table_name, String data) throws WriteException, ReadException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyData(String database_name, String table_name, String data, String newData)
			throws WriteException, ReadException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> readData(String database_name, String table_name) throws ReadException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> searchData(String database_name, String table_name, String search) throws ReadException {
		// TODO Auto-generated method stub
		return null;
	}
}
