package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.txtDatabase.DataType.DataType;
import com.ceep.TruthCheck.exceptions.TXT.TypeException;

public class TypeChecker {
//    STRING, INT, DECIMAL, TABLE_REF, LIST;

    public static boolean checkType(String data, DataType type) {
        
        switch (type) {
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
                return true;
        }
    }

    public static boolean checkInt(String data) {
        try { Integer.parseInt(data);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean checkDecimal(String data){
        try { Double.parseDouble(data);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean checkTable_ref(String data) {
        //#TODO
        return false;
    }

    public static boolean checkList(String data) {
        
    }
}
