
package com.ceep.TruthCheck.exceptions;


public class ObjectCreationException extends Exception {

    public ObjectCreationException() {
        super("Error while trying to create a object");
    }
    
    public ObjectCreationException(String error){
        super(error);
    }
    
}
