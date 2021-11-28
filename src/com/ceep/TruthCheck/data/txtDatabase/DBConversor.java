package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.LinkTable;
import com.ceep.TruthCheck.data.Table;
import com.ceep.TruthCheck.domain.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Traduce objetos a texto y viceversa
 * 
 * @author GamuD
 *
 */
public class DBConversor {
	/**
	 * Crea una tabla a partir de su version en texto
	 * 
	 * @param stringifiedTable la tabla en texto
	 * @return la tabla en objeto
	 */
	public static Table tableFromDBString(String stringifiedTable) {
		String[] tableData = stringifiedTable.split(Storable.FIELD_SEPARATOR);

		String tableName = tableData[0];

		List<Table.Column> columns = columnsFromDBString(tableData[1]);
		List<String> foreignTables = new ArrayList<>();
		try {
			foreignTables = Arrays.asList(tableData[2].split(Storable.LIST_SEPARATOR));
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		return new Table(tableName, foreignTables, columns);
	}

	/**
	 * Crea n columnas a partir de sus versiones en texto
	 * 
	 * @param stringifiedColumns la columnas en texto
	 * @return lista de las columnas en objeto
	 */
	public static List<Table.Column> columnsFromDBString(String stringifiedColumns) {
		String[] columnsData = stringifiedColumns.split(Storable.LIST_SEPARATOR);
		List<Table.Column> columns = new ArrayList<>();
		for (String stringifiedColumn : columnsData) {
			columns.add(columnFromDBString(stringifiedColumn));
		}
		return columns;
	}

	/**
	 * Crea una columna a partir de su version en texto
	 * 
	 * @param stringifiedColumn la columna en texto
	 * @return la coluna en objeto
	 */
	public static Table.Column columnFromDBString(String stringifiedColumn) {
		String[] columnData = stringifiedColumn.split(Storable.PAIR_SEPARATOR);
		String name = columnData[0];
		DataType type = DataType.valueOf(columnData[1]);

		return Table.c(name, type);
	}

	/**
	 * Crea un personaje a partir de su version en texto
	 * 
	 * @param stringifiedChar personaje en texto
	 * @param usableItems     objetos que hay
	 * @return personaje con todas las referencias a sus items
	 */
	public static GameCharacter charFromDBString(String stringifiedChar, HashMap<String, GameItem> usableItems) {

		String[] chardata = stringifiedChar.split(Storable.FIELD_SEPARATOR);

		int id = Integer.parseInt(chardata[0]);
		String name = chardata[1];
		String description = chardata[2];

		List<GameItem> inventory = itemListFromDBString(chardata[3], usableItems);

		List<GameItem> equipment = itemListFromDBString(chardata[4], usableItems);

		return new GameCharacter(id, name, description, inventory, equipment);
	}

	/**
	 * Crea una lista de items a partir de sus ids
	 * 
	 * @param itemIds     ids de los items
	 * @param usableItems lista de items
	 * @return una lista de items
	 */
	public static List<GameItem> itemListFromDBString(String itemIds, HashMap<String, GameItem> usableItems) {
		List<GameItem> list = new ArrayList<>();
		for (String itemdata : itemIds.split(Storable.LIST_SEPARATOR)) {
			list.add(usableItems.get(itemdata));
		}
		return list;
	}

	/**
	 * Crea un item a partir de su version en texto
	 * 
	 * @param stringifiedItem item en forma de texto
	 * @return el item
	 */
	public static GameItem itemFromDBString(String stringifiedItem) {
		String[] itemdata = stringifiedItem.split(Storable.FIELD_SEPARATOR);

		int id = Integer.parseInt(itemdata[0]);
		String nombre = itemdata[1];
		String description = itemdata[2];

		return new GameItem(id, nombre, description);
	}

	public static HashMap<String, GameItem> itemsFromDBString(List<String> stringifiedItems) {
		HashMap<String, GameItem> result = new  HashMap<>();
		for(String s : stringifiedItems) {
			result.put(s.split(Storable.FIELD_SEPARATOR)[1], itemFromDBString(s));
		}
		return result;
	}

	/**
	 * Convierte un Storable en texto para ser guardado
	 * 
	 * @param o objeto a convertir
	 * @return objeto en forma de texto
	 */
	public static String toDBString(Storable o) {
		if (o instanceof Table) {
			return toDBString((Table) o);
		}
		if (o instanceof GameCharacter) {
			return toDBString((GameCharacter) o);
		}
		if (o instanceof GameItem) {
			return toDBString((GameItem) o);
		}
		if (o instanceof LinkTable) {
			return toDBString((LinkTable) o);
		}
		return null;
	}

	/**
	 * Convierte una tabla de enlace en texto
	 * 
	 * @param l
	 * @return
	 */
	public static String toDBString(LinkTable l) {
		return l.getKey1() + Storable.FIELD_SEPARATOR + l.getKey2();
	}

	/**
	 * Convierte una tabla en texto
	 * 
	 * @param t
	 * @return tabla en forma de texto
	 */
	public static String toDBString(Table t) {
		StringBuilder stringifiedTable = new StringBuilder();
		stringifiedTable.append(t.getTableName()).append(Storable.FIELD_SEPARATOR);
		for (Table.Column column : t.getStructure()) {
			stringifiedTable.append(toDBString(column)).append(Storable.LIST_SEPARATOR);
		}
		stringifiedTable.append(Storable.FIELD_SEPARATOR);
		if (t.getReferencedTablesNames() != null) {
			for (String table : t.getReferencedTablesNames()) {
				stringifiedTable.append(table).append(Storable.LIST_SEPARATOR);
			}
		}
		return stringifiedTable.toString();
	}

	/**
	 * Convierte una columna en texto
	 * 
	 * @param c
	 * @return la columna en forma de texto
	 */
	public static String toDBString(Table.Column c) {
		return c.name + Storable.PAIR_SEPARATOR + c.type.toString();
	}

	/**
	 * Convierte un personaje en texto
	 * 
	 * @param c
	 * @return el persnaje en forma de texto
	 */
	public static String toDBString(GameCharacter c) {
		StringBuilder stringifiedChar = new StringBuilder();

		stringifiedChar.append(c.getId()).append(Storable.FIELD_SEPARATOR);
		stringifiedChar.append(c.getName()).append(Storable.FIELD_SEPARATOR);
		stringifiedChar.append(c.getDescription()).append(Storable.FIELD_SEPARATOR);

		for (GameItem item : c.getInventory()) {
			stringifiedChar.append(item.getId()).append(Storable.LIST_SEPARATOR);
		}
		stringifiedChar.append(Storable.FIELD_SEPARATOR);

		for (GameItem item : c.getEquipment()) {
			stringifiedChar.append(item.getId()).append(Storable.LIST_SEPARATOR);
		}
		stringifiedChar.append(Storable.FIELD_SEPARATOR);

		return stringifiedChar.toString();
	}

	/**
	 * Convierte un item en texto
	 * 
	 * @param i
	 * @return el item en forma de texto
	 */
	public static String toDBString(GameItem i) {
		StringBuilder stringifiedItem = new StringBuilder();

		stringifiedItem.append(i.getId()).append(Storable.FIELD_SEPARATOR);
		stringifiedItem.append(i.getName()).append(Storable.FIELD_SEPARATOR);
		stringifiedItem.append(i.getDescription()).append(Storable.FIELD_SEPARATOR);

		return stringifiedItem.toString();
	}

}
