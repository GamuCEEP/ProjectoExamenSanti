

package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.txtDatabase.DataType.DataType;


public class Table implements Storable{

    private final String tableName;
    private final DataType[] structure;

    public Table(String tableName, DataType... structure) {
        this.tableName = tableName;
        this.structure = structure;
    }

    public String getTableName() {
        return tableName;
    }

    public DataType[] getStructure() {
        return structure;
    }
    
    
   
    
    
    
    
}
