package com.ceep.TruthCheck.exceptions;

public class DatabaseNotFoundException extends Exception {
	public DatabaseNotFoundException() {
		super("Could not find the database");
	}
	public DatabaseNotFoundException(String error) {
		super(error);
	}
}
