

package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.DataBase;
import com.ceep.TruthCheck.exceptions.TXT.TypeSafetyExtension;
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
    
    public void createDatabase(String database) throws WriteException{
        DB.createDatabase(Paths.get(ROOT, database).toString());
        
        DB.createTable(ROOT, CONFIG);
        DB.writeData(ROOT, CONFIG, database);
    }
    public void createTable(String database, Table table) 
            throws WriteException{
        DB.createTable(database, table.getTableName());
        
        String configPath = Paths.get(ROOT, database).toString();
        DB.createTable(configPath, CONFIG);
        DB.writeData(database, CONFIG, DBConversor.toDBString(table));
    }
    public void addEntry(String database, Table table, Storable entry) 
            throws TypeSafetyExtension, WriteException{
        if(/*typeSafe(entry)*/false) return;
        
        DB.writeData(database, table.getTableName(), DBConversor.toDBString(entry));        
    }
    private boolean isTypeSafe(Storable entry, Table table){
        String[] entryData = DBConversor.toDBString(entry).split(Storable.FIELD_SEPARATOR);
        
        for(DataType type : table.getStructure()){
            
        }
    }
    
}
