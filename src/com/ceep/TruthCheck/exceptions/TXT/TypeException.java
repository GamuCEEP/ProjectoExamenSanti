

package com.ceep.TruthCheck.exceptions.TXT;


public class TypeException extends Exception{

    public TypeException() {
        super("Type violation");
    }
    public TypeException(String error){
        super(error);
    }
    
}
