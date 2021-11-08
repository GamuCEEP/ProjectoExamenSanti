
package com.ceep.TruthCheck.exceptions;

public class AccessException extends Exception{

    public AccessException() {
        super("Error while accessing data");
    }
    public AccessException(String error){
        super(error);
    }
    
}
