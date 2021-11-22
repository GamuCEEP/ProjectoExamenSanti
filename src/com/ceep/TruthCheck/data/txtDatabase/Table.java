package com.ceep.TruthCheck.data.txtDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representa una tabla de una base de datos
 * 
 * @author GamuD
 *
 */
public class Table implements Storable {
	/**
	 * Nombre de la tabla
	 */
	private final String tableName;
	/**
	 * Estructura de la tabla representada por una lista de columnas
	 */
	private final List<Column> structure;
	/**
	 * Nombres de las tablas a las que hace referencia
	 */
	private final List<String> referencedTablesNames;
	/**
	 * Lista de tablas a las que se hace referencia en orden de uso
	 * Se tiene que cargar despues de que todas las tablas esten creadas
	 */
	private final List<Table> referencedTables;

	/**
	 * Crea una tabla, las referencias se introducen despues
	 * 
	 * @param tableNamenombre de la tabla
	 * @param structure       columnas que tiene la tabla en forma de array or
	 *                        vararg
	 */
	public Table(String tableName, List<String> referencedTableNames, Column... structure) {
		this.tableName = tableName;
		this.structure = Arrays.asList(structure);
		this.referencedTablesNames = referencedTableNames;
		this.referencedTables = new ArrayList<>();
		
	}

	/**
	 * Crea una tabla, las referencias se introducen despues
	 * 
	 * @param tableName nombre de la tabla
	 * @param structure columnas que tiene la tabla en forma de lista de columnas
	 */
	public Table(String tableName,List<String> referencedTableNames, List<Column> structure) {
		this.tableName = tableName;
		this.structure = structure;
		this.referencedTablesNames = referencedTableNames;
		this.referencedTables = new ArrayList<>();
	}

	/**
	 * Guarda la referencia a otra tabla en la lista de tablas ajenas
	 * 
	 * @param refTable
	 */
	public void addReference(Table refTable) {
		this.referencedTables.add(refTable);
	}
	/**
	 * @return la lista de los nombres de las tablasa las que se hace referencia
	 */
	public List<String> getReferencedTablesNames() {
		return referencedTablesNames;
	}

	/**
	 * @return el nombre de la tabla
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @return la estructura de la tabla en forma de lista de columnas
	 */
	public List<Column> getStructure() {
		return structure;
	}

	/**
	 * @return una lista de las tablas a las que esta tabla hace referencia
	 */
	public List<Table> getReferencedTables() {
		return referencedTables;
	}

	/**
	 * Funcion principal para crear columnas
	 * 
	 * @param name nombre de la columna
	 * @param type typo de la columna
	 * @return una columna
	 */
	public static Column c(String name, DataType type) {
		return new Column(name, type);
	}

	/**
	 * Comprueba que el texto pasado tenga los tipos correctoas para la tabla
	 * 
	 * @param entry objeto en forma de texto
	 * @return true si el objeto tiene los tipos correctos
	 */
	public boolean typeCheck(String entry) {

		String[] fields = entry.split(Storable.FIELD_SEPARATOR);

		if (fields.length != structure.size())
			return false;

		boolean isCorrectType = true;
		for (int i = 0; i < structure.size(); i++) {
			Column column = structure.get(i);
			String field = fields[i];

			isCorrectType &= column.type == DataType.REFERENCE ? column.type.checkRef(field, this)
					: column.type.check(field);
		}
		return isCorrectType;
	}

	/**
	 * Representa una columna de una tabla
	 * 
	 * @author GamuD
	 *
	 */
	public static class Column {
		/**
		 * Nombre de la columna
		 */
		public String name;
		/**
		 * Tipo de la columna
		 */
		public DataType type;

		/**
		 * Constructor principal
		 * 
		 * @param name nombre de la columna
		 * @param type tipo de la columna
		 */
		private Column(String name, DataType type) {
			this.name = name;
			this.type = type;
		}
	}
}
