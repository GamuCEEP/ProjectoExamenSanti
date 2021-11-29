package com.ceep.TruthCheck.util;
/**
 * Representa una opcion de un menu
 * @author Alumno Mañana
 */
public class Option{
	
	String key;
	Procedure value;
	
	public Option(String key, Procedure value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public Procedure getValue() {
		return value;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setValue(Procedure value) {
		this.value = value;
	}
	
}
