package com.ceep.TruthCheck.data.sqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
	
	private static final String JDBC_ULR = 
			"jdbc:mysql://localhost:3306/base"
			+ "?useSSL=false"
			+ "&useTimezone=true"
			+ "&serverTimezone=UTC"
			+ "&allowPublicKeyRetrieval=true";
	private static String JDBC_USER = "root";
	private static String JDBC_PASSWORD = "";


	public static Connection getConection() throws SQLException {
		return DriverManager.getConnection(JDBC_ULR, JDBC_USER, JDBC_PASSWORD);
	}	
}
