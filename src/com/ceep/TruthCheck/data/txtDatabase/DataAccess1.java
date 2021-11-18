package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.DataBase;
import com.ceep.TruthCheck.exceptions.TXT.TypeException;
import com.ceep.TruthCheck.exceptions.WriteException;
import java.nio.file.Paths;

public class DataAccess1 {

    /**
     * Carpeta en la que se guardan las bases que se creen
     */
    private static final String ROOT = "BasesDeDatos";
    /**
     * Nombre del archivo de configuracion
     */
    private static final String CONFIG = "config";

    private DataBase DB;
    /**
     * Crea una base de datos en {@value com.ceep.TruthCheck.data.txtDatabase.DataAccess1#ROOT}
     * @param database nombre de la base de datos
     * @throws WriteException
     */
    public void createDatabase(String database) throws WriteException {
        DB.createDatabase(Paths.get(ROOT, database).toString());

        DB.createTable(ROOT, CONFIG);
        DB.writeData(ROOT, CONFIG, database);
    }
    /**
     * Crea una tabla en una base de datos dada
     * @param database nombre de la base de datos
     * @param table tabla que se va a crear
     * @throws WriteException 
     */
    public void createTable(String database, Table table)
            throws WriteException {
        DB.createTable(database, table.getTableName());

        String configPath = Paths.get(ROOT, database).toString();
        DB.createTable(configPath, CONFIG);
        DB.writeData(database, CONFIG, DBConversor.toDBString(table));
    }
    
    public void addEntry(String database, Table table, Storable entry)
            throws TypeException, WriteException {
        if () {
            return;
        }

        DB.writeData(database, table.getTableName(), DBConversor.toDBString(entry));
    }

    private boolean isTypeSafe(Storable entry, Table table) {
        String[] entryData = DBConversor.toDBString(entry).split(Storable.FIELD_SEPARATOR);

        for (DataType type : table.getStructure()) {

        }
    }

}
