
package com.ceep.TruthCheck.exceptions;


public class IntegrityViolationException extends Exception{

    public IntegrityViolationException() {
        super("Violation of integrity");
    }
    public IntegrityViolationException(String error){
        super(error);
    }
    
}
