package com.ceep.TruthCheck.exceptions;

public class WriteException extends Exception {

    public WriteException() {
        super("Error while writing data");
    }
    
    public WriteException(String error){
        super(error);
    }
    
}
