package com.ceep.TruthCheck.exceptions;

public class WriteException extends AccessException {

    public WriteException() {
        super("Error while writing data");
    }
    
    public WriteException(String error){
        super(error);
    }
    
}
