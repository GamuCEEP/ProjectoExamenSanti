package com.ceep.TruthCheck.data.txtDatabase;

import java.util.Arrays;
import java.util.List;

public class Table implements Storable {

    private final String tableName;
    private final List<Column> structure;

    public Table(String tableName, Column... structure) {
        this.tableName = tableName;
        this.structure = Arrays.asList(structure);
    }
    public Table(String tableName, List<Column> structure) {
        this.tableName = tableName;
        this.structure = structure;
    }

    public String getTableName() {
        return tableName;
    }

    public List<Column> getStructure() {
        return structure;
    }
    
    public static Column C(String name, DataType type){
        return new Column(name, type);
    }
    
    public boolean typeCheck(String entry){
        
    }

    public static class Column {

        public String name;
        public DataType type;
        
        private Column(String name, DataType type) {
            this.name = name;
            this.type = type;
        }
    }
}
