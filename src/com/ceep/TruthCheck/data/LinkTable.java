package com.ceep.TruthCheck.data;

import com.ceep.TruthCheck.data.txtDatabase.Storable;
/**
 * Representa un registro de una tabla de enlace
 * @author Alumno Mañana
 */
public class LinkTable implements Storable{

	private int key1;
	private int key2;
	
	public LinkTable(int key1, int key2) {
		this.key1 = key1;
		this.key2 = key2;
	}
	public int getKey1() {
		return key1;
	}
	public int getKey2() {
		return key2;
	}
}
