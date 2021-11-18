

package com.ceep.TruthCheck.data.txtDatabase;

import java.util.function.Predicate;




public enum DataType {
    /**
     * Cualquier trozo de texto
     */
    STRING(t -> true),
    /**
     * Un numero entero
     */
    INT(t -> {
        try{
            Integer.parseInt(t);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }),
    /**
     * Un numero decimal con '.' como separador
     */
    DECIMAL(t -> {
        try{
            Double.parseDouble(t);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }),
    /**
     * Un numero entero que hace referencia a una id en otra tabla
     */
    TABLE_REF(t ->{
        return true;//#TODO
    });
    
    private final Predicate<String> func;
    
    /**
     * Constructor principal, para los tipos implementados
     * @param func 
     */
    private DataType(Predicate<String> func){
        this.func = func;
    }
    /**
     * Constructor por defecto para los tipos no implementados
     */
    private DataType(){
        this.func = (String data) -> false;
    }
    /**
     * Comprueba si el valor pasado es del tipo correcto
     * @param data valor para comprobar
     * @return true si el valor es del tipo correcto
     */
    public boolean check(String data){
        return func.test(data);
    }
}
