

package com.ceep.TruthCheck.data.txtDatabase;


public class TypeChecker {
//    STRING, INT, DECIMAL, TABLE_REF, LIST;
    public static boolean checkType(String data, DataType type){
        switch(type){
            case STRING:
                return true;
            case INT:
                return checkInt(data);
            case DECIMAL:
                return checkDecimal(data);
            case TABLE_REF:
                return checkTable_ref(data);
            case LIST:
                return checkList(data);
            default:
        }
    }
    public static boolean checkInt(String data){
        //#TODO
    }
    public static boolean checkDecimal(String data){
        
    }
    public static boolean checkTable_ref(String data){
        
    }
    public static boolean checkList(String data){
        
    }
}
