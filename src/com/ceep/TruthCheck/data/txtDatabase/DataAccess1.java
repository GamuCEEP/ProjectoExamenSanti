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
    
    private String getPath(String end) {
    	return Paths.get(ROOT, end).toString();
    }
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
        DB.createTable(getPath(database), table.getTableName());

        DB.createTable(getPath(database), CONFIG);
        DB.writeData(getPath(database), CONFIG, DBConversor.toDBString(table));
    }
    
    public void addEntry(String database, Table table, Storable entry)
            throws TypeException, WriteException {
        if (table.typeCheck(DBConversor.toDBString(entry))) {
            throw new TypeException("The type of the entry does not match the structure of the table");
        }

        DB.writeData(getPath(database), table.getTableName(), DBConversor.toDBString(entry));
    }
    //Para seguir tengo el esquema cuantico, el txt
    

}
