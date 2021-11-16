

package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.txtDatabase.DataType;
import com.ceep.TruthCheck.data.txtDatabase.Storable;


public class Table implements Storable{

    private String tableName;
    private DataType[] structure;

    public Table(String tableName, DataType... structure) {
        this.tableName = tableName;
        this.structure = structure;
    }

    public String getTableName() {
        return tableName;
    }
    
    public String toDBString() {
        StringBuilder stringifiedTable = new StringBuilder();
        stringifiedTable.append(tableName).append(Storable.FIELD_SEPARATOR);
        for(DataType type : structure){
            stringifiedTable.append(type).append(Storable.LIST_SEPARATOR);
        }
        stringifiedTable.append(Storable.FIELD_SEPARATOR);
        //tablename;type,type,type;
        return stringifiedTable.toString();
    }
    
    
    
    
}
