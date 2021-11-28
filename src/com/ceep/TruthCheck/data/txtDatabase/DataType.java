
package com.ceep.TruthCheck.data.txtDatabase;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.ceep.TruthCheck.data.Table;
import com.ceep.TruthCheck.data.Table.Column;

/**
 * Un enum para comprobar el tipo de las tablas
 * soporta referencias a otras tablas
 * @author GamuD
 *
 */
public enum DataType {
	/**
	 * Cualquier trozo de texto
	 */
	STRING(data -> true),
	/**
	 * Un numero entero
	 */
	INT(data -> {
		try {
			Integer.parseInt(data);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}),
	/**
	 * Un numero decimal con '.' como separador
	 */
	DECIMAL(data -> {
		try {
			Double.parseDouble(data);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}),
	/**
	 * Representa una id de una tabla, un elemento al que se puede hacer referencia
	 */
	REFERABLE(data ->{
		try {
			Integer.parseInt(data);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}),
	/**
	 * Un numero entero que hace referencia a un REFERABLE en otra tabla
	 */
	REFERENCE((data, table) -> {
		
		if(table.getReferencedTables() == null) return false;
		if(table.getReferencedTables().isEmpty()) return false;
		
		try {
			String[] ids = data.split(Storable.LIST_SEPARATOR);
			for(String id : ids) {
				Integer.parseInt(id);
				for(Table foreignTable : table.getReferencedTables()) {
					if(foreignTable == null) return false;
					for(Column column : foreignTable.getStructure()) {
						if(column.type == DataType.REFERABLE) return true;
					}
				}
			}			
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	});

	/**
	 * Funcion que se comprueba el tipo
	 */
	private final Predicate<String> pred;
	/**
	 * Funcion que se comprueba el tipo en caso de ser referencial
	 */
	private final BiPredicate<String, Table> refpred;

	/**
	 * Constructor principal de tipos normales
	 * 
	 * @param func
	 */
	private DataType(Predicate<String> func) {
		this.pred = func;
		this.refpred = null;
	}

	/**
	 * Constructor para tipos de referencia
	 */
	private DataType(BiPredicate<String, Table> refpred) {
		this.pred = null;
		this.refpred = refpred;
	}

	/**
	 * Comprueba si el valor pasado es del tipo correcto
	 * 
	 * @param data valor para comprobar
	 * @return true si el valor es del tipo correcto
	 */
	public boolean check(String data) {
		return pred.test(data);
	}

	/**
	 * Comprueba si la referencia es correcta y la tabla extranjera tiene un campo de tipo INT
	 * 
	 * @param data     valor para comprobar
	 * @param reftable tabla a la que referencia
	 * @return true si es una referencia correcta y existe
	 */
	public boolean checkRef(String data, Table reftable) {
		return refpred.test(data, reftable);
	}
}
